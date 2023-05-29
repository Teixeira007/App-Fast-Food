package com.home.fastfoodactivity.data.network;

import com.home.fastfoodactivity.data.network.response.FoodResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodService {

    @GET("best-foods")
    Call<List<FoodResponse>> getBestFoods();

    @GET("burgers")
    Call<List<FoodResponse>> getBurgers();

    @GET("our-foods")
    Call<List<FoodResponse>> getAll();

    @GET("sandwiches")
    Call<List<FoodResponse>> getSandwiches();

    @GET("pizzas")
    Call<List<FoodResponse>> getPizzas();

    @GET("drinks")
    Call<List<FoodResponse>> getDrinks();

    @GET("ice-cream")
    Call<List<FoodResponse>> getIceCream();


}
