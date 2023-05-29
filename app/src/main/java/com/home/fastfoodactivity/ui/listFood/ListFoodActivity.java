package com.home.fastfoodactivity.ui.listFood;

import static com.home.fastfoodactivity.ui.detailsFood.DetailsFoodActivity.EXTRA_FOOD;
import static com.home.fastfoodactivity.ui.listPedidos.ListPedidosActivity.EXTRA_PEDIDOS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.home.fastfoodactivity.R;
import com.home.fastfoodactivity.data.model.Food;
import com.home.fastfoodactivity.data.model.ItemPedido;
import com.home.fastfoodactivity.data.model.Pedido;
import com.home.fastfoodactivity.data.room.AppDatabase;
import com.home.fastfoodactivity.ui.detailsFood.DetailsFoodActivity;
import com.home.fastfoodactivity.ui.listCart.CartData;
import com.home.fastfoodactivity.ui.listCart.ListCartAdapter;
import com.home.fastfoodactivity.ui.listPedidos.ListPedidosActivity;

import java.util.List;

public class ListFoodActivity extends AppCompatActivity implements ListFoodContract.view, ListFoodAdapter.ClickItem {

    private RecyclerView recyclerView;
    private RecyclerView recyclerViewCart;
    private ListFoodAdapter adapter;
    private ListFoodPresenter presenter;
    private ListCartAdapter listCartAdapter;
    private TextView total;
    private Toolbar toolbar;

    private Button finalizarPedido;
    private AppDatabase db;


    public static String EXTRA_CART = "EXTRA_CART";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        configToolBar();
        configAdapter();
        configCategory();
        searchFood();


        presenter = new ListFoodPresenter(this);
        presenter.getFoods();

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my-database-pedidos").build();

    }

    public void configAdapter(){
        recyclerView = findViewById(R.id.my_recycler_view);

//        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(this);
        RecyclerView.LayoutManager gridLayout = new GridLayoutManager(this, 2);
        adapter = new ListFoodAdapter(this);



        recyclerView.setLayoutManager(gridLayout);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showFoods(List<Food> foods) {
        adapter.setFoods(foods);
    }

    @Override
    public void showMessageError() {
        Toast.makeText(this, "Erro ao carregar o cardápio", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroyView();
    }

    @Override
    public void onItemClicked(Food food) {
        Intent intent = new Intent(this, DetailsFoodActivity.class);
        intent.putExtra(EXTRA_FOOD, food);
        startActivity(intent);
    }


    //Configurações da ToolBar

    public void configToolBar(){
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tool_bar, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        //click em cart
        if(itemId == R.id.menu_cart_item){
            showPopupCart(item.getActionView());
            return true;
        }

        //click em list
        if(itemId == R.id.menu_list_pedidos){
            showListPedidos(item.getActionView());
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    //Configurando Cart

    @SuppressLint("MissingInflatedId")
    public void showPopupCart(View view){

        //criar o dialog view
        View dialogView = createDialogView();

        recyclerViewCart = dialogView.findViewById(R.id.my_recycler_view_cart);
        total = dialogView.findViewById(R.id.total_cart);

        //calcula valor total do pedido
        double totalValue = calculateTotal();
        total.setText("Total: $"+Double.toString(totalValue));

        //Butão finalizar Pedido
        finalizarPedido = dialogView.findViewById(R.id.finalizar_pedido);


        //configurar o adapter
        configAdapterCart(recyclerViewCart);

        configFinalizarPedido();

    }

    public View createDialogView(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_cart, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.show();

        return dialogView;
    }

    public void configAdapterCart(RecyclerView recyclerViewCart){
        listCartAdapter = new ListCartAdapter();

        ItemPedido itemPedido = (ItemPedido) getIntent().getSerializableExtra(EXTRA_CART);

        listCartAdapter.setListCart(CartData.getCartItems());

        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(this);

        recyclerViewCart.setLayoutManager(linearLayout);
        recyclerViewCart.setAdapter(listCartAdapter);
    }

    public double calculateTotal(){
        double total = 0;
        List<ItemPedido> itensCart = CartData.getCartItems();
        for(ItemPedido itens: itensCart){
            total += itens.getProduct().getPrice() * itens.getQuantity();
        }

        return total;
    }

    public void cleanCart(View view){
        CartData.cleanCart();
        listCartAdapter.setListCart(CartData.getCartItems());
        total.setText("Total: $0");
        Toast.makeText(this, "Carrinho de produtos limpo", Toast.LENGTH_SHORT).show();
    }

    //Finalizar Pedido

    public void configFinalizarPedido(){

        finalizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Pedido pedido = new Pedido(CartData.getCartItems(), calculateTotal());

                        if(CartData.getCartItems().size()>0){
                            db.pedidoDao().insertPedido(pedido);
                        }


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(CartData.getCartItems().size()>0){
                                    Toast.makeText(ListFoodActivity.this, "Pedido finalizado", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(ListFoodActivity.this, "Adicione itens antes de finalizar", Toast.LENGTH_SHORT).show();
                                }
                                CartData.cleanCart();
                                listCartAdapter.setListCart(CartData.getCartItems());
                                total.setText("Total: $0");
                            }
                        });
                    }
                }).start();
            }
        });
    }


    //Listar Pedidos

    public void showListPedidos(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Pedido> pedidos = db.pedidoDao().getAll();

                Gson gson = new Gson();
                String listPedidosJson = gson.toJson(pedidos);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(ListFoodActivity.this, ListPedidosActivity.class);
                        intent.putExtra(EXTRA_PEDIDOS, listPedidosJson);
                        startActivity(intent);
                    }
                });
            }
        }).start();
    }

    public void configCategory(){
        ImageView iconCategory = findViewById(R.id.menu_item);

        iconCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });
    }

    public void showPopupMenu(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.menu_category);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.category_burgers){
                    presenter.getBurgers();
//                    Toast.makeText(ListFoodActivity.this, "Categoria Burgers ", Toast.LENGTH_SHORT).show();
                }else if(id == R.id.category_sandwiches){
                    presenter.getSandwiches();
                } else if (id == R.id.category_drinks) {
                    presenter.getDrinks();
                } else if (id == R.id.category_ice_cream) {
                    presenter.getIceCream();
                } else if (id == R.id.category_pizzas) {
                    presenter.getPizzas();
                } else if (id == R.id.category_all) {
                    presenter.getAll();
                } else if (id == R.id.category_best_foods) {
                    presenter.getFoods();
                }
                return false;
            }
        });
        popupMenu.show();
    }

    public void searchFood(){
        SearchView searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(ListFoodActivity.this, "Pesquisado: "+s, Toast.LENGTH_SHORT).show();
                if(s != null && s!=""){
                    presenter.getAll(s);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }
}