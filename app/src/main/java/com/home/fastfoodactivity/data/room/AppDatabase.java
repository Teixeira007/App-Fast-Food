package com.home.fastfoodactivity.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.home.fastfoodactivity.data.model.Pedido;

@TypeConverters(ListConvert.class)
@Database(entities = {Pedido.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PedidoDao pedidoDao();
}
