package com.home.fastfoodactivity.data.model;

import java.io.Serializable;
import java.util.List;

public class ItemPedido implements Serializable {

    private Food product;
    private int quantity;

    public ItemPedido(Food product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Food getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
