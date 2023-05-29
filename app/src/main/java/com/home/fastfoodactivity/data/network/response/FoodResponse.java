package com.home.fastfoodactivity.data.network.response;

import com.squareup.moshi.Json;

public class FoodResponse {

    @Json(name = "id")
    private String id;

    @Json(name = "name")
    private String name;
    @Json(name = "img")
    private String image;

    @Json(name = "dsc")
    private String description;

    @Json(name = "price")
    private double price;

    @Json(name = "rate")
    private int rate;



    public FoodResponse(String name, String image, String description, double price, int rate) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getRate() {
        return rate;
    }
}
