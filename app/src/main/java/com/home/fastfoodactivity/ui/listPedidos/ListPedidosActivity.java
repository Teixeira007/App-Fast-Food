package com.home.fastfoodactivity.ui.listPedidos;

import static com.home.fastfoodactivity.ui.DetailsPedido.DetailsPedidoActivity.EXTRA_DETAILS_PEDIDO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.home.fastfoodactivity.R;
import com.home.fastfoodactivity.data.model.Pedido;
import com.home.fastfoodactivity.ui.DetailsPedido.DetailsPedidoActivity;

import java.lang.reflect.Type;
import java.util.List;

public class ListPedidosActivity extends AppCompatActivity implements ListPedidosAdapter.ItemClicado {

    public static final String EXTRA_PEDIDOS = "EXTRA_PEDIDOS";
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ListPedidosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pedidos);

        Gson gson = new Gson();
        String listPedidosJson = getIntent().getStringExtra(EXTRA_PEDIDOS);

        Type type = new TypeToken<List<Pedido>>() {}.getType();
        List<Pedido> listPedido = gson.fromJson(listPedidosJson, type);

        configToolBar();
        congigAdapter(listPedido);
    }

    public void configToolBar(){
        toolbar = findViewById(R.id.my_toolbar_list_pedidos);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Drawable backIcon = getResources().getDrawable(R.drawable.baseline_arrow_back_24);
        getSupportActionBar().setHomeAsUpIndicator(backIcon);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void congigAdapter(List<Pedido> pedidos){
        recyclerView = findViewById(R.id.my_recycler_view_list_pedidos);

        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);

        adapter = new ListPedidosAdapter(this);

        adapter.setPedidos(pedidos);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClickItem(Pedido pedido) {
        Intent intent = new Intent(this, DetailsPedidoActivity.class);
        intent.putExtra(EXTRA_DETAILS_PEDIDO, pedido);
        startActivity(intent);
    }
}