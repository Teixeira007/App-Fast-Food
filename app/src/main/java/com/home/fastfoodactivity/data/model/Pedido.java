package com.home.fastfoodactivity.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity
public class Pedido implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "lista_itens")
    private  List<ItemPedido> listItens;

    @ColumnInfo(name = "total_pedido")
    private double total;

    public Pedido(List<ItemPedido> listItens, double total) {
        this.listItens = listItens;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public List<ItemPedido> getListItens() {
        return listItens;
    }

    public double getTotal() {
        return total;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
