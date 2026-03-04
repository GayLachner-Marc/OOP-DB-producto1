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

    public Tienda() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    

    @Override
    public String toString() {
        return "Tienda{" + "nombre=" + nombre + ", clientes=" + clientes + ", articulos=" + articulos + ", pedidos=" + pedidos + '}';
    }

    public void añadirCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void añadirArticulo(Articulo articulo) {
        articulos.add(articulo);
    }

    public void añadirPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void eliminarPedido(Pedido pedido) {
        if (pedido.puedeCancelarse()) {
            pedidos.remove(pedido);
        } else {
            System.out.println("No se puede eliminar el pedido. Ya ha sido enviado.");
        }
    }

    public List<Pedido> mostrarPedidosPendientes() {
        List<Pedido> pendientes = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (!p.estaEnviado()) {
                pendientes.add(p);
            }
        }
        return pendientes;
    }

    public List<Pedido> mostrarPedidosEnviados() {
        List<Pedido> enviados = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.estaEnviado()) {
                enviados.add(p);
            }
        }
        return enviados;
    }
    
    // --------------------
    // MÉTODOS DE NEGOCIO
    // --------------------

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void agregarArticulo(Articulo articulo) {
        articulos.add(articulo);
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public Cliente buscarClientePorEmail(String email) {
        for (Cliente c : clientes) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }

    public Articulo buscarArticuloPorCodigo(String codigo) {
        for (Articulo a : articulos) {
            if (a.getCodigo().equalsIgnoreCase(codigo)) {
                return a;
            }
        }
        return null;
    }
    
    public Pedido buscarPedidoPorNumero(int numero) {
    for (Pedido p : pedidos) {
        if (p.getNumeroPedido() == numero) {
            return p;
        }
    }
    return null;
}
}
