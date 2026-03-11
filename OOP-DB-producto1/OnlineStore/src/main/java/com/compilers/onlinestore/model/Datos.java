package com.compilers.onlinestore.model;

import com.compilers.onlinestore.exceptions.ArticuloNoExisteException;
import com.compilers.onlinestore.exceptions.ClienteNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoYaEnviadoException;

import java.util.List;

public class Datos {

    private Tienda tienda;

    public Datos() {
        tienda = new Tienda("OnlineStore");
    }

    // ================= CLIENTES =================
    public boolean crearClienteEstandar(String nombre, String email, String domicilio, String nif) {

        if (buscarCliente(email) != null) {
            return false;
        }

        Cliente c = new ClienteEstandar(nombre, email, domicilio, nif);

        tienda.getClientes().add(c);

        return true;
    }

    public boolean crearClientePremium(String nombre, String email, String domicilio, String nif) {

        if (buscarCliente(email) != null) {
            return false;
        }

        Cliente c = new ClientePremium(nombre, email, domicilio, nif);

        tienda.getClientes().add(c);

        return true;
    }

    public Cliente buscarCliente(String email) {

        for (Cliente c : tienda.getClientes()) {

            if (c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
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

    public Cliente modificarCliente(String email, String nombre,
            String domicilio, String nif)
            throws ClienteNoExisteException {

        Cliente c = buscarCliente(email);

        if (c == null) {
            throw new ClienteNoExisteException("Cliente no encontrado");
        }

        c.setNombre(nombre);
        c.setDomicilio(domicilio);
        c.setNif(nif);

        return c;
    }

    // ================= ARTICULOS =================
    public boolean crearArticulo(String codigo, String descripcion,
            double precioVenta, double gastosEnvio,
            int tiempoPreparacion) {

        if (buscarArticulo(codigo) != null) {
            return false;
        }

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

            if (a.getCodigo().equalsIgnoreCase(codigo)) {
                return a;
            }
        }

        return null;
    }
    
    
    public Articulo modificarArticulo(String codigo,
            String descripcion,
            double precioVenta,
            double gastosEnvio,
            int tiempoPreparacion)
            throws ArticuloNoExisteException {

        Articulo a = buscarArticulo(codigo);

        if (a == null) {
            throw new ArticuloNoExisteException("Articulo no encontrado");
        }

        a.setDescripcion(descripcion);
        a.setPrecioVenta(precioVenta);
        a.setGastosEnvio(gastosEnvio);
        a.setTiempoPreparacion(tiempoPreparacion);
        return a;
    }
    

    public boolean eliminarArticulo(String codigo)
            throws ArticuloNoExisteException {

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
    public Pedido buscarPedido(int numeroPedido) {
        for (Pedido p : tienda.getPedidos()) {
            if (p.getNumeroPedido() == numeroPedido) {
                return p;
            }
        }
        return null;
    }

    public boolean eliminarPedido(int numeroPedido) throws PedidoYaEnviadoException {
        Pedido p = buscarPedido(numeroPedido);

        if (p != null) {
            if (p.estaEnviado()) {
                throw new PedidoYaEnviadoException("El pedido ya fue enviado");
            }
            tienda.getPedidos().remove(p);
            return true;
        }
        return false;
    }

    public List<Pedido> listarPedidos() {
        return tienda.getPedidos();
    }

    public Pedido modificarPedido(int numeroPedido, int nuevaCantidad)
            throws PedidoYaEnviadoException, PedidoNoExisteException {

        Pedido p = buscarPedido(numeroPedido);

        if (p == null) {
            throw new PedidoNoExisteException("Pedido no encontrado");
        }

        if (p.estaEnviado()) {
            throw new PedidoYaEnviadoException("El pedido ya fue enviado y no puede modificarse.");
        }

        p.setCantidad(nuevaCantidad);
        return p;
    }

    public boolean crearPedido(int numeroPedido,
            String emailCliente,
            String codigoArticulo,
            int cantidad) {

        Cliente cliente = buscarCliente(emailCliente);
        Articulo articulo = buscarArticulo(codigoArticulo);

        if (cliente == null || articulo == null) {
            return false;
        }

        Pedido p = new Pedido(numeroPedido, cliente, articulo, cantidad);
        tienda.getPedidos().add(p);

        return true;
    }

}
