package com.home.fastfoodactivity.data.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.home.fastfoodactivity.data.model.Pedido;

import java.util.List;

@Dao
public interface PedidoDao {

    @Query("SELECT * FROM Pedido")
    List<Pedido> getAll();

    @Insert
    void insertPedido(Pedido pedido);

    @Delete
    void delete(Pedido pedido);

    @Delete
    void deleteAll(List<Pedido> pedidos);

    @Update
    void update(Pedido pedido);


}
