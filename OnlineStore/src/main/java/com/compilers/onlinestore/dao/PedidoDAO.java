package com.compilers.onlinestore.dao;

import java.util.List;
import com.compilers.onlinestore.model.Pedidos.Pedido;

public interface PedidoDAO {

    void crear(Pedido pedido);

    Pedido obtenerPorNumero(int numero);

    List<Pedido> obtenerTodos();

    void actualizar(Pedido pedido);
    void eliminar(int numero);

    public List<Pedido> obtenerPorCliente(int id);
    
    //Función para revisar si el cliente tiene pedidos
    public boolean pedidosPendientesORegistrados(int clienteId); 
    public boolean articulosEnPedidos(int articuloId);
    
    
}