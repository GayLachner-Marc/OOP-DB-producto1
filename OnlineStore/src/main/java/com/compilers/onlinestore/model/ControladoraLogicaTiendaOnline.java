package com.compilers.onlinestore.model;

import com.compilers.onlinestore.controller.ControladoraPersistenciaTiendaOnline;
import java.util.List;

public class ControladoraLogicaTiendaOnline {

    ControladoraPersistenciaTiendaOnline controlPersis = new ControladoraPersistenciaTiendaOnline();

    //Aqui debes poner todos los metodos CRUD
    private Tienda tienda;

    public ControladoraLogicaTiendaOnline(Tienda tienda) {
        this.tienda = tienda;
    }

    // ========================
    // CLIENTES
    // ========================
    public void crearClienteEstandar(String nombre, String domicilio,
            String nif, String email) {

        Cliente cliente = new ClienteEstandar(nombre, domicilio, nif, email);
        tienda.añadirCliente(cliente);
    }

    public void crearClientePremium(String nombre, String domicilio,
            String nif, String email) {

        Cliente cliente = new ClientePremium(nombre, domicilio, nif, email);
        tienda.añadirCliente(cliente);
    }

    public List<Cliente> obtenerClientes() {
        return tienda.getClientes();
    }

    // ========================
    // ARTICULOS
    // ========================
    public void crearArticulo(String codigo, String descripcion,
            double precio, double envio, int tiempoPrep) {

        Articulo articulo = new Articulo(codigo, descripcion, precio, envio, tiempoPrep);
        tienda.añadirArticulo(articulo);
    }

    public List<Articulo> obtenerArticulos() {
        return tienda.getArticulos();
    }

    // ========================
    // PEDIDOS
    // ========================
    public void crearPedido(int numeroPedido, String emailCliente,
            String codigoArticulo, int cantidad) {

        Cliente cliente = tienda.buscarClientePorEmail(emailCliente);
        Articulo articulo = tienda.buscarArticuloPorCodigo(codigoArticulo);

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }

        if (articulo == null) {
            throw new IllegalArgumentException("Articulo no encontrado");
        }

        Pedido pedido = new Pedido(numeroPedido, cliente, articulo, cantidad);
        tienda.añadirPedido(pedido);
    }

    public void eliminarPedido(int numeroPedido) {

        Pedido pedido = tienda.buscarPedidoPorNumero(numeroPedido);

        if (pedido != null && pedido.puedeCancelarse()) {
            tienda.eliminarPedido(pedido);
        } else {
            throw new IllegalStateException("No se puede eliminar el pedido");
        }
    }

    public List<Pedido> obtenerPedidosPendientes() {
        return tienda.mostrarPedidosPendientes();
    }

    public List<Pedido> obtenerPedidosEnviados() {
        return tienda.mostrarPedidosEnviados();
    }

}
