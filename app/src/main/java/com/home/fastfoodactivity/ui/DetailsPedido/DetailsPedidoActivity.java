package com.home.fastfoodactivity.ui.DetailsPedido;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.home.fastfoodactivity.R;
import com.home.fastfoodactivity.data.model.Pedido;

public class DetailsPedidoActivity extends AppCompatActivity {

    public static final String EXTRA_DETAILS_PEDIDO = "EXTRA_DETAILS_PEDIDO";
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DetailsPedidoAdapter adapter;
    private TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_pedido);

        Pedido pedido = (Pedido) getIntent().getSerializableExtra(EXTRA_DETAILS_PEDIDO);


        configToolBar();
        configAdapter(pedido);
    }

    public void configToolBar(){
        toolbar = findViewById(R.id.my_toolbar_details_pedido);
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

    public void configAdapter(Pedido pedido){
        recyclerView = findViewById(R.id.my_recycler_view_details_pedido);

        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);

        adapter = new DetailsPedidoAdapter();

        adapter.setItemPedidos(pedido.getListItens());

        total = findViewById(R.id.total_details);
        total.setText("Total $"+Double.toString(pedido.getTotal()));

        recyclerView.setAdapter(adapter);
    }
}