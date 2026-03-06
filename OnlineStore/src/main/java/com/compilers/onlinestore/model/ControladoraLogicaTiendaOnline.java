package com.compilers.onlinestore.model;
import com.compilers.onlinestore.controller.ControladoraPersistenciaTiendaOnline;
import com.compilers.onlinestore.exceptions.ArticuloNoExisteException;
import com.compilers.onlinestore.exceptions.ClienteNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoYaEnviadoException;
import com.compilers.onlinestore.model.Articulos.Articulo;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Clientes.ClienteEstandar;
import com.compilers.onlinestore.model.Clientes.ClientePremium;
import com.compilers.onlinestore.model.Pedidos.Pedido;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
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


  public void modificarCliente(String email, String nombre,
                             String domicilio, String nif)
        throws ClienteNoExisteException {

    for (Cliente c : tienda.getClientes()) {

        if (c.getEmail().equals(email)) {

            c.setNombre(nombre);
            c.setDomicilio(domicilio);
            c.setNif(nif);
            return;
        }
    }

    throw new ClienteNoExisteException("Cliente no encontrado");
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

    public boolean modificarArticulo(String codigo, String nuevaDescripcion,
                                 double nuevoPvp, double nuevosGastosEnvio,
                                 int nuevoTiempoPreparacion) {

    for (Articulo art : tienda.getArticulos()) {

        if (art.getCodigo().equals(codigo)) {

            art.setDescripcion(nuevaDescripcion);
            art.setPrecioVenta(nuevoPvp);
            art.setGastosEnvio(nuevosGastosEnvio);
            art.setTiempoPreparacion(nuevoTiempoPreparacion);

            return true;
        }
    }

    return false;
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

   public void modificarPedido(int numeroPedido, int nuevaCantidad)
        throws PedidoYaEnviadoException {

    Pedido pedido = tienda.buscarPedidoPorNumero(numeroPedido);

    if (pedido == null) {
        throw new IllegalArgumentException("Pedido no encontrado");
    }
    if (pedido.estaEnviado()) {
        throw new PedidoYaEnviadoException("No se puede modificar el pedido porque ya fue enviado");
    }

    pedido.setCantidad(nuevaCantidad);

    System.out.println("Pedido modificado correctamente.");
}
}
