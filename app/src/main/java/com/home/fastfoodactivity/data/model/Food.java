package com.home.fastfoodactivity.data.model;

import java.io.Serializable;

public class Food implements Serializable {

    private String id;
    private String name;
    private String image;
    private double price;
    private String description;
    private int rate;

    public Food(String name, String image, double price, String description, int rate) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getRate() {
        return rate;
    }
}
