package com.home.fastfoodactivity.ui.listFood;

import com.home.fastfoodactivity.data.model.Food;

import java.util.List;

public interface ListFoodContract {

    public interface view{

        void showFoods(List<Food> foods);

        void showMessageError();

    }

    public interface presenter{
        void getFoods();

        void getBurgers();
        void getAll();
        void getSandwiches();
        void getDrinks();
        void getIceCream();
        void getPizzas();
        void getAll(String food);

        void destroyView();
    }
}
