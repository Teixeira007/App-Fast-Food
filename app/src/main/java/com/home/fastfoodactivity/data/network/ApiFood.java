package com.home.fastfoodactivity.data.network;

import com.home.fastfoodactivity.data.model.Food;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiFood {

    private static FoodService INSTANCE;

    public static FoodService getINSTANCE(){
        if(INSTANCE == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://free-food-menus-api.onrender.com/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build();

            INSTANCE = retrofit.create(FoodService.class);
        }

        return INSTANCE;
    }
}
