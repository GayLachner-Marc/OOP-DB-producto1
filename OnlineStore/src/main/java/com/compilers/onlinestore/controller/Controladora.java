package com.compilers.onlinestore.controller;

import com.compilers.onlinestore.dao.*;
import com.compilers.onlinestore.model.Articulos.Articulo;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Pedidos.Pedido;
import com.compilers.onlinestore.exceptions.*;

import java.util.List;

public class Controladora {

    private ClienteDAO clienteDAO;
    private ArticuloDAO articuloDAO;
    private PedidoDAO pedidoDAO;

    public Controladora() {

        clienteDAO = new ClienteDAOImpl();
        articuloDAO = new ArticuloDAOImpl();
        pedidoDAO = new PedidoDAOImpl();
    }

    // ================= CLIENTES =================
    public void crearCliente(Cliente cliente) {
        clienteDAO.crear(cliente);
    }

    public Cliente buscarCliente(String email) {
        return clienteDAO.obtenerPorEmail(email);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.obtenerTodos();
    }

    public void actualizarCliente(Cliente cliente) {
        clienteDAO.actualizar(cliente);
    }

    public boolean eliminarCliente(String email) {

        Cliente c = clienteDAO.obtenerPorEmail(email);

        if (c == null) {
            return false;
        }

        if (pedidoDAO.pedidosPendientesORegistrados(c.getId())) {
            throw new RuntimeException("No se puede eliminar, el cliente tiene pedidos pendientes o registrados");
        }

        clienteDAO.eliminar(email);
        return true;
    }

    // ================= ARTICULOS =================
    public void crearArticulo(Articulo a) {
        articuloDAO.crear(a);
    }

    public Articulo buscarArticulo(String codigo) {
        return articuloDAO.obtenerPorCodigo(codigo);
    }

    public List<Articulo> listarArticulos() {
        return articuloDAO.obtenerTodos();
    }

    public void actualizarArticulo(Articulo a)
            throws ArticuloNoExisteException {

        if (articuloDAO.obtenerPorCodigo(a.getCodigo()) == null) {
            throw new ArticuloNoExisteException("Articulo no encontrado");
        }

        articuloDAO.actualizar(a);
    }

    public boolean eliminarArticulo(String codigo) {

        Articulo a = articuloDAO.obtenerPorCodigo(codigo);

        if (a == null) {
            return false;
        }

        if (pedidoDAO.articulosEnPedidos(a.getId())) {
            throw new RuntimeException("No se puede eliminar, el articulo está enlazado con pedidos");
        }

        articuloDAO.eliminar(codigo);

        return true;
    }

    // ================= PEDIDOS =================
    public void crearPedido(Pedido p) {
        pedidoDAO.crear(p);
    }

    public Pedido buscarPedido(int numero) {
        return pedidoDAO.obtenerPorNumero(numero);
    }

    public List<Pedido> listarPedidos() {
        return pedidoDAO.obtenerTodos();
    }

    public void actualizarPedido(Pedido p)
            throws PedidoYaEnviadoException {

        if (p.estaEnviado()) {
            throw new PedidoYaEnviadoException("No se puede modificar, el pedido ya fue enviado");
        }

        pedidoDAO.actualizar(p);
    }

    public boolean eliminarPedido(int numero)
            throws PedidoYaEnviadoException {

        Pedido p = pedidoDAO.obtenerPorNumero(numero);

        if (p == null) {
            return false;
        }

        if (p.estaEnviado()) {
            throw new PedidoYaEnviadoException("No se puede eliminar, el pedido ya fue enviado");
        }

        pedidoDAO.eliminar(numero);

        return true;
    }
}
