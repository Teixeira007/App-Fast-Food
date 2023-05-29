package com.home.fastfoodactivity.ui.listPedidos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.home.fastfoodactivity.R;
import com.home.fastfoodactivity.data.model.Pedido;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ListPedidosAdapter extends RecyclerView.Adapter<ListPedidosAdapter.ListPedidosViewHolder> {

    private List<Pedido> pedidos;
    private static ItemClicado itemClicado;

    public ListPedidosAdapter(ItemClicado itemClicado) {
        this.pedidos = new ArrayList<>();
        this.itemClicado = itemClicado;

    }

    @NonNull
    @Override
    public ListPedidosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_pedidos, parent, false);
        return new ListPedidosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListPedidosViewHolder holder, int position) {
        holder.bind(pedidos.get(position));
    }

    @Override
    public int getItemCount() {
        return (pedidos != null && pedidos.size()>0) ? pedidos.size() : 0;
    }

    public static class ListPedidosViewHolder extends RecyclerView.ViewHolder {

        private TextView subDescricaoPedido;
        private TextView totalPedido;

        private Pedido pedido;

        public ListPedidosViewHolder(@NonNull View itemView) {
            super(itemView);
            subDescricaoPedido = itemView.findViewById(R.id.sub_descricao_list_pedidos);
            totalPedido = itemView.findViewById(R.id.total_list_pedidos);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemClicado != null){
                        itemClicado.onClickItem(pedido);
                    }
                }
            });
        }

        public void bind(Pedido pedido){
            this.pedido = pedido;
            if(pedido.getListItens().get(0).getProduct().getName().length() > 8){
                String subDescricao = pedido.getListItens().get(0).getQuantity()+" "+pedido.getListItens().get(0).getProduct().getName().substring(0, 8);
                subDescricaoPedido.setText(subDescricao+"...");
            }else{
                String subDescricao = pedido.getListItens().get(0).getQuantity()+" "+pedido.getListItens().get(0).getProduct().getName();
                subDescricaoPedido.setText(subDescricao+"...");
            }

            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String priceString = decimalFormat.format(pedido.getTotal());
            totalPedido.setText(priceString);
        }
    }

    public void setPedidos(List<Pedido> pedidos){
        this.pedidos = pedidos;
    }

    public interface ItemClicado{
        void onClickItem(Pedido pedido);
    }
}
