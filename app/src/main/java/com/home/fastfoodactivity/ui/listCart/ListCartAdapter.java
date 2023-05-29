package com.home.fastfoodactivity.ui.listCart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.home.fastfoodactivity.R;
import com.home.fastfoodactivity.data.model.ItemPedido;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ListCartAdapter extends RecyclerView.Adapter<ListCartAdapter.ListCartViewHolder>{

    private List<ItemPedido> itensPedidos;

    public ListCartAdapter() {
        this.itensPedidos = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_cart, parent, false);
        return new ListCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCartViewHolder holder, int position) {
        holder.bind(itensPedidos.get(position));
    }

    @Override
    public int getItemCount() {
        return (itensPedidos != null && itensPedidos.size()>0) ? itensPedidos.size() : 0;
    }

    public static  class ListCartViewHolder extends RecyclerView.ViewHolder {

        private TextView nameFoodCart;
        private ImageView imageFoodCart;
        private TextView priceFoodCart;
        private TextView quantityFoodCart;

        public ListCartViewHolder(@NonNull View itemView) {
            super(itemView);
            nameFoodCart = itemView.findViewById(R.id.name_cart);
            priceFoodCart = itemView.findViewById(R.id.price_cart);
            quantityFoodCart = itemView.findViewById(R.id.quantity_product_cart);
            imageFoodCart = itemView.findViewById(R.id.image_cart);
        }

        public void bind(ItemPedido itemPedido){
            nameFoodCart.setText(itemPedido.getProduct().getName());

            //Price total do item
            double priceItens = itemPedido.getQuantity() * itemPedido.getProduct().getPrice();
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String priceString = decimalFormat.format(priceItens);
            priceFoodCart.setText("$"+priceString);

            quantityFoodCart.setText("Quant: "+Integer.toString(itemPedido.getQuantity()));
            Picasso.get().load(itemPedido.getProduct().getImage()).into(imageFoodCart);

        }



    }

    public void setListCart(List<ItemPedido> itensPedidos){
        this.itensPedidos = itensPedidos;
        notifyDataSetChanged();
    }
}
