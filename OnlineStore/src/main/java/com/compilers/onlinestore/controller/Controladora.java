package com.compilers.onlinestore.controller;
import com.compilers.onlinestore.exceptions.ArticuloNoExisteException;
import com.compilers.onlinestore.exceptions.ClienteNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoYaEnviadoException;
import com.compilers.onlinestore.model.Articulos.Articulo;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Clientes.ClienteEstandar;
import com.compilers.onlinestore.model.Clientes.ClientePremium;
import com.compilers.onlinestore.model.Pedidos.Pedido;
import com.compilers.onlinestore.model.Tienda;
import com.compilers.onlinestore.model.Datos;

import java.util.List;

public class Controladora {

    private Datos datos;

    public Controladora() {
        datos = new Datos();
    }

    // ================= CLIENTES =================

    public boolean crearClienteEstandar(String nombre, String email, String domicilio, String nif) {
        return datos.crearClienteEstandar(nombre, email, domicilio, nif);
    }

    public boolean crearClientePremium(String nombre, String email, String domicilio, String nif) {
        return datos.crearClientePremium(nombre, email, domicilio, nif);
    }

    public boolean eliminarCliente(String email) {
        return datos.eliminarCliente(email);
    }

    public List<Cliente> listarClientes() {
        return datos.listarClientes();
    }

    public Cliente buscarCliente(String email) {
        return datos.buscarCliente(email);
    }

    public Cliente modificarCliente(String email, String nombre,
                                    String domicilio, String nif)
            throws ClienteNoExisteException {

        return datos.modificarCliente(email, nombre, domicilio, nif);
    }

    // ================= ARTICULOS =================

    public boolean crearArticulo(String codigo, String descripcion,
                                 double precioVenta, double gastosEnvio,
                                 int tiempoPreparacion) {

        return datos.crearArticulo(codigo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion);
    }
    
    public Articulo buscarArticulo(String codigo) {
        return datos.buscarArticulo(codigo);
    }

    public Articulo modificarArticulo(String codigo,
                                  String descripcion,
                                  double precioVenta,
                                  double gastosEnvio,
                                  int tiempoPreparacion)
            throws ArticuloNoExisteException {

        return datos.modificarArticulo(codigo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion);
    }

    public boolean eliminarArticulo(String codigo)
            throws ArticuloNoExisteException {

        return datos.eliminarArticulo(codigo);
    }

    public List<Articulo> listarArticulos() {
        return datos.listarArticulos();
    }

    // ================= PEDIDOS =================

    public boolean crearPedido(int numeroPedido,
                               String emailCliente,
                               String codigoArticulo,
                               int cantidad) {

        return datos.crearPedido(numeroPedido, emailCliente, codigoArticulo, cantidad);
    }

    public boolean eliminarPedido(int numeroPedido)
            throws PedidoYaEnviadoException {

        return datos.eliminarPedido(numeroPedido);
    }

    public List<Pedido> listarPedidos() {
        return datos.listarPedidos();
    }
    
    public Pedido buscarPedido(int numeroPedido) {
        return datos.buscarPedido(numeroPedido);
    }
    public Pedido modificarPedido(int numeroPedido, int nuevaCantidad)
            throws PedidoYaEnviadoException, PedidoNoExisteException {

        
        return datos.modificarPedido(numeroPedido, nuevaCantidad);
    }
}