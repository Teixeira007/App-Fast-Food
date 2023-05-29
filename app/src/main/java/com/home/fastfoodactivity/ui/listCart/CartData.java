package com.home.fastfoodactivity.ui.listCart;

import com.home.fastfoodactivity.data.model.ItemPedido;

import java.util.ArrayList;
import java.util.List;

public class CartData {
    private static List<ItemPedido> cartItens = new ArrayList<>();

    public static List<ItemPedido> getCartItems(){
        return cartItens;
    }

    public static void addToCart(ItemPedido itemPedido){
        cartItens.add(itemPedido);
    }

    public static void cleanCart(){
        cartItens.clear();
    }
}
