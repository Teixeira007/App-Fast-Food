package com.home.fastfoodactivity.ui.DetailsPedido;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.home.fastfoodactivity.R;
import com.home.fastfoodactivity.data.model.Food;
import com.home.fastfoodactivity.data.model.ItemPedido;
import com.home.fastfoodactivity.data.model.Pedido;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailsPedidoAdapter extends RecyclerView.Adapter<DetailsPedidoAdapter.DetailsPedidoViewHolder> {

    private List<ItemPedido> itemPedidos;

    public DetailsPedidoAdapter() {
        this.itemPedidos = new ArrayList<>();
    }

    @NonNull
    @Override
    public DetailsPedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_cart, parent, false);
        return new DetailsPedidoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsPedidoViewHolder holder, int position) {
        holder.bind(itemPedidos.get(position));
    }

    @Override
    public int getItemCount() {
        return (itemPedidos != null && itemPedidos.size()>0) ? itemPedidos.size() : 0;
    }

    public static class DetailsPedidoViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageDetails;
        private TextView nameDetails;
        private TextView priceDetails;
        private TextView quantityDetails;

        public DetailsPedidoViewHolder(@NonNull View itemView) {
            super(itemView);

            imageDetails = itemView.findViewById(R.id.image_cart);
            nameDetails = itemView.findViewById(R.id.name_cart);
            priceDetails= itemView.findViewById(R.id.price_cart);
            quantityDetails = itemView.findViewById(R.id.quantity_product_cart);
        }

        public void bind(ItemPedido itemPedido){
            nameDetails.setText(itemPedido.getProduct().getName());

            //Price total do item
            double priceItens = itemPedido.getQuantity() * itemPedido.getProduct().getPrice();
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String priceString = decimalFormat.format(priceItens);
            priceDetails.setText("$"+priceString);

            quantityDetails.setText("Quant: "+Integer.toString(itemPedido.getQuantity()));
            Picasso.get().load(itemPedido.getProduct().getImage()).into(imageDetails);



        }
    }

    public void setItemPedidos(List<ItemPedido> itemPedidos){
        this.itemPedidos = itemPedidos;
        notifyDataSetChanged();
    }
}
