package com.home.fastfoodactivity.data.dto;

import com.home.fastfoodactivity.data.model.Food;
import com.home.fastfoodactivity.data.network.response.FoodResponse;

import java.util.ArrayList;
import java.util.List;

public class DtoFood {

    public static List<Food> convertFoodResponseForFood(List<FoodResponse> foodResponse){
        List<Food> foods = new ArrayList<>();

        for(FoodResponse response: foodResponse){
            Food food = new Food(response.getName(), response.getImage(), response.getPrice(),
                    response.getDescription(), response.getRate());

            foods.add(food);
        }

        return foods;
    }
}
