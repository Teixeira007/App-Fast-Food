package com.home.fastfoodactivity.data.room;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.home.fastfoodactivity.data.model.ItemPedido;

import java.lang.reflect.Type;
import java.util.List;

public class ListConvert {

    @TypeConverter
    public String fromList(List<ItemPedido> list){
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public List<ItemPedido> toList(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<ItemPedido>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
