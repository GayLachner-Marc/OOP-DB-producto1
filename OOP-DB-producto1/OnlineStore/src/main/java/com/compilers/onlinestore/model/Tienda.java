package com.compilers.onlinestore.model;

import java.util.List;
import java.util.ArrayList;

public class Tienda {
    private String nombre;

    private List<Cliente> clientes;
    private List<Articulo> articulos;
    private List<Pedido> pedidos;

    public Tienda(String nombre) {

        this.nombre = nombre;

        clientes = new ArrayList<>();
        articulos = new ArrayList<>();
        pedidos = new ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
