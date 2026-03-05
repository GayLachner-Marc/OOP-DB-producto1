
package com.compilers.onlinestore.controller;
import com.compilers.onlinestore.model.*;
import java.util.ArrayList;
import java.util.List;

public class Controladora {
    private Tienda tienda;

    public Controladora() {
        tienda = new Tienda("OnlineStore");
    }

    // ================= CLIENTES =================

    public boolean crearClienteEstandar(String nombre, String email, String domicilio, String nif) {

        if (buscarCliente(email) != null)
            return false;

        Cliente c = new ClienteEstandar(nombre, email, domicilio, nif);

        tienda.getClientes().add(c);

        return true;
    }

    public boolean crearClientePremium(String nombre, String email, String domicilio, String nif) {

        if (buscarCliente(email) != null)
            return false;

        Cliente c = new ClientePremium(nombre, email, domicilio, nif);

        tienda.getClientes().add(c);

        return true;
    }

    public Cliente buscarCliente(String email) {

        for (Cliente c : tienda.getClientes()) {

            if (c.getEmail().equalsIgnoreCase(email))
                return c;
        }

        return null;
    }

    public boolean eliminarCliente(String email) {

        Cliente c = buscarCliente(email);

        if (c != null) {
            tienda.getClientes().remove(c);
            return true;
        }

        return false;
    }

    public List<Cliente> listarClientes() {
        return tienda.getClientes();
    }

    // ================= ARTICULOS =================

    public boolean crearArticulo(String codigo, String descripcion,
                                 double precioVenta, double gastosEnvio,
                                 int tiempoPreparacion) {

        if (buscarArticulo(codigo) != null)
            return false;

        Articulo a = new Articulo(
                codigo,
                descripcion,
                precioVenta,
                gastosEnvio,
                tiempoPreparacion
        );

        tienda.getArticulos().add(a);

        return true;
    }

    public Articulo buscarArticulo(String codigo) {

        for (Articulo a : tienda.getArticulos()) {

            if (a.getCodigo().equalsIgnoreCase(codigo))
                return a;
        }

        return null;
    }

    public boolean eliminarArticulo(String codigo) {

        Articulo a = buscarArticulo(codigo);

        if (a != null) {
            tienda.getArticulos().remove(a);
            return true;
        }

        return false;
    }

    public List<Articulo> listarArticulos() {
        return tienda.getArticulos();
    }

    // ================= PEDIDOS =================

    public boolean crearPedido(int numeroPedido,
                               String emailCliente,
                               String codigoArticulo,
                               int cantidad) {

        Cliente cliente = buscarCliente(emailCliente);
        Articulo articulo = buscarArticulo(codigoArticulo);

        if (cliente == null || articulo == null)
            return false;

        Pedido p = new Pedido(numeroPedido, cliente, articulo, cantidad);

        tienda.getPedidos().add(p);

        return true;
    }

    public boolean eliminarPedido(int numeroPedido) {

        for (Pedido p : tienda.getPedidos()) {

            if (p.getNumeroPedido() == numeroPedido) {
                tienda.getPedidos().remove(p);
                return true;
            }
        }

        return false;
    }

    public List<Pedido> listarPedidos() {
        return tienda.getPedidos();
    }

}
