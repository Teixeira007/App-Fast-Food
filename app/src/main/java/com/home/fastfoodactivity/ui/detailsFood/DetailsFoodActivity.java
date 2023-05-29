package com.home.fastfoodactivity.ui.detailsFood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.home.fastfoodactivity.R;
import com.home.fastfoodactivity.data.model.Food;
import com.home.fastfoodactivity.data.model.ItemPedido;
import com.home.fastfoodactivity.ui.listCart.CartData;
import com.home.fastfoodactivity.ui.listCart.ListCartAdapter;
import com.home.fastfoodactivity.ui.listFood.ListFoodActivity;
import com.squareup.picasso.Picasso;

public class DetailsFoodActivity extends AppCompatActivity {

    public static final String EXTRA_FOOD = "EXTRA_FOOD";

    private Toolbar toolbar;
    private EditText quantity;
    private Button removeQuantity;
    private Button addQuantity;
//    private ListCartAdapter adapter;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_food);


        configToolBar();

//        adapter = new ListCartAdapter();
        setValores();

    }

    public void configToolBar(){
        toolbar = findViewById(R.id.my_toolbar_details);
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



    public void setValores(){
        ImageView imageProduct;
        TextView nameProduct;
        TextView priceProduct;
        TextView rateProduct;
        TextView descriptionProduct;

        imageProduct = findViewById(R.id.image_product);
        nameProduct = findViewById(R.id.name_product);
        priceProduct = findViewById(R.id.price_product);
        rateProduct = findViewById(R.id.rate_product);
        descriptionProduct = findViewById(R.id.description_product);
        quantity = findViewById(R.id.quantity_product);
        removeQuantity = findViewById(R.id.button_remove);
        addQuantity = findViewById(R.id.button_add);

        Food food = (Food) getIntent().getSerializableExtra(EXTRA_FOOD);
        nameProduct.setText(food.getName());
        priceProduct.setText("$"+Double.toString(food.getPrice()));
        rateProduct.setText(Integer.toString(food.getRate()));
        descriptionProduct.setText(food.getDescription());
        Picasso.get().load(food.getImage()).into(imageProduct);

        String quantityValue = quantity.getText().toString();
        int quantity = Integer.parseInt(quantityValue);



    }

    public void setAddQuantity(View view){
        String quantityValue = quantity.getText().toString();
        int quantityInt = Integer.parseInt(quantityValue);
        quantityInt += 1;
        quantity.setText(Integer.toString(quantityInt));
    }

    public void setRemoveQuantity(View view){
        String quantityValue = quantity.getText().toString();
        int quantityInt = Integer.parseInt(quantityValue);
        if(quantityInt > 1){
            quantityInt -= 1;
            quantity.setText(Integer.toString(quantityInt));
        }
    }

    public void addItemPedido(View view){
        Food food = (Food) getIntent().getSerializableExtra(EXTRA_FOOD);

        String quantityValue = quantity.getText().toString();
        int quantityInt = Integer.parseInt(quantityValue);

        ItemPedido itemPedido = new ItemPedido(food, quantityInt);
        CartData.addToCart(itemPedido);

//        adapter.setListCart(itemPedido);

//        Intent intent = new Intent(this, ListFoodActivity.class);
//        intent.putExtra(ListFoodActivity.EXTRA_CART, itemPedido);
//        startActivity(intent);

        if(itemPedido != null){
            Toast.makeText(this, itemPedido.getQuantity()+" "+itemPedido.getProduct().getName()+" adicionado com sucesso", Toast.LENGTH_LONG).show();
        }
    }
}