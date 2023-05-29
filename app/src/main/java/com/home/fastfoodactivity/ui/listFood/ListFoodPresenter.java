package com.home.fastfoodactivity.ui.listFood;

import com.home.fastfoodactivity.data.dto.DtoFood;
import com.home.fastfoodactivity.data.model.Food;
import com.home.fastfoodactivity.data.network.ApiFood;
import com.home.fastfoodactivity.data.network.response.FoodResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFoodPresenter implements ListFoodContract.presenter{

    private ListFoodContract.view view;


    public ListFoodPresenter(ListFoodContract.view view) {
        this.view = view;
    }

    @Override
    public void getFoods() {
        makeApiRequestType("BestFoods");
    }

    @Override
    public void getBurgers() {
       makeApiRequestType("Burgers");
    }

    @Override
    public void getAll() {
        makeApiRequestType("All");
    }

    @Override
    public void getSandwiches() {
        makeApiRequestType("Sandwiches");
    }

    @Override
    public void getDrinks() {
        makeApiRequestType("Drinks");
    }

    @Override
    public void getIceCream() {
        makeApiRequestType("IceCream");
    }

    @Override
    public void getPizzas() {
        makeApiRequestType("Pizzas");
    }

    @Override
    public void getAll(String search) {
        ApiFood.getINSTANCE().getAll().enqueue(new Callback<List<FoodResponse>>() {
            @Override
            public void onResponse(Call<List<FoodResponse>> call, Response<List<FoodResponse>> response) {
                if(response.isSuccessful()){
                    List<Food> foods = DtoFood.convertFoodResponseForFood(response.body());
                    List<Food> filterFoods = foods
                            .stream()
                            .filter(food -> food.getName().toLowerCase().contains(search.toLowerCase()))
                            .collect(Collectors.toList());
                    view.showFoods(filterFoods);
                }else{
                    view.showMessageError();
                }
            }

            @Override
            public void onFailure(Call<List<FoodResponse>> call, Throwable t) {
                view.showMessageError();
            }
        });
    }


    public void makeApiRequestType(String getFunction){
        Call<List<FoodResponse>> call;

        if(getFunction.equals("All")){
            call = ApiFood.getINSTANCE().getAll();
        }else if(getFunction.equals("Burgers")){
            call = ApiFood.getINSTANCE().getBurgers();
        }else if(getFunction.equals("Sandwiches")){
            call = ApiFood.getINSTANCE().getSandwiches();
        } else if (getFunction.equals("Pizzas")) {
            call = ApiFood.getINSTANCE().getPizzas();
        } else if (getFunction.equals("Drinks")) {
            call = ApiFood.getINSTANCE().getDrinks();
        } else if (getFunction.equals("IceCream")) {
            call = ApiFood.getINSTANCE().getIceCream();
        }
        else{
            call = ApiFood.getINSTANCE().getBestFoods();
        }

        makeApiRequestGeral(call);
    }

    public void makeApiRequestGeral(Call<List<FoodResponse>> call){
        call.enqueue(new Callback<List<FoodResponse>>() {
            @Override
            public void onResponse(Call<List<FoodResponse>> call, Response<List<FoodResponse>> response) {
                if(response.isSuccessful()){
                    List<Food> foods = DtoFood.convertFoodResponseForFood(response.body());
                    view.showFoods(foods);
                }else{
                    view.showMessageError();
                }
            }

            @Override
            public void onFailure(Call<List<FoodResponse>> call, Throwable t) {
                view.showMessageError();
            }
        });
    }

    @Override
    public void destroyView() {
        this.view = null;
    }
}
