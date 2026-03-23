package com.compilers.onlinestore.dao;

import java.util.List;
import com.compilers.onlinestore.model.Pedidos.Pedido;

public interface PedidoDAO {

    void crear(Pedido pedido);

    Pedido obtenerPorNumero(int numero);

    List<Pedido> obtenerTodos();

    void actualizar(Pedido pedido);
}