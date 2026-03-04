
package com.compilers.onlinestore.controller;
import com.compilers.onlinestore.model.*;
import java.util.ArrayList;
import java.util.List;

public class Controladora {
    //controladora modelo
    
    //modelo.agregarCliente()
    private List<Cliente> clientes = new ArrayList<>();
    private List<Articulo> articulos = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();

    // ================= CLIENTES =================

    public boolean crearCliente(Cliente c) {
        if (buscarCliente(c.getEmail()) != null)
            return false;

        clientes.add(c);
        return true;
    }

    public Cliente buscarCliente(String email) {
        for (Cliente c : clientes) {
            if (c.getEmail().equalsIgnoreCase(email))
                return c;
        }
        return null;
    }

    public boolean modificarCliente(String email, String nombre,
                                    String domicilio, String nif) {

        Cliente c = buscarCliente(email);

        if (c != null) {
            c.setNombre(nombre);
            c.setDomicilio(domicilio);
            c.setNif(nif);
            return true;
        }
        return false;
    }

    public boolean eliminarCliente(String email) {
        Cliente c = buscarCliente(email);
        if (c != null) {
            clientes.remove(c);
            return true;
        }
        return false;
    }

    public List<Cliente> getClientes() { return clientes; }

    // ================= ARTICULOS =================

    public boolean crearArticulo(Articulo a) {
        if (buscarArticulo(a.getCodigo()) != null)
            return false;

        articulos.add(a);
        return true;
    }

    public Articulo buscarArticulo(String codigo) {
        for (Articulo a : articulos) {
            if (a.getCodigo().equalsIgnoreCase(codigo))
                return a;
        }
        return null;
    }

    public boolean modificarArticulo(String codigo, String descripcion,
                                     double precioVenta,
                                     double gastosEnvio,
                                     int tiempoPreparacion) {

        Articulo a = buscarArticulo(codigo);

        if (a != null) {
            a.setDescripcion(descripcion);
            a.setPrecioVenta(precioVenta);
            a.setGastosEnvio(gastosEnvio);
            a.setTiempoPreparacion(tiempoPreparacion);
            return true;
        }
        return false;
    }

    public boolean eliminarArticulo(String codigo) {
        Articulo a = buscarArticulo(codigo);
        if (a != null) {
            articulos.remove(a);
            return true;
        }
        return false;
    }

    public List<Articulo> getArticulos() { return articulos; }

    // ================= PEDIDOS =================

    public boolean crearPedido(Pedido p) {
        if (buscarPedido(p.getNumeroPedido()) != null)
            return false;

        pedidos.add(p);
        return true;
    }

    public Pedido buscarPedido(int numero) {
        for (Pedido p : pedidos) {
            if (p.getNumeroPedido() == numero)
                return p;
        }
        return null;
    }

    public boolean eliminarPedido(int numero) {
        Pedido p = buscarPedido(numero);
        if (p != null) {
            pedidos.remove(p);
            return true;
        }
        return false;
    }

    public List<Pedido> getPedidos() { return pedidos; }
}
