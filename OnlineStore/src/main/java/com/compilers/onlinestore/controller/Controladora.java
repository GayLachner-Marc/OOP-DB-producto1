package com.compilers.onlinestore.controller;

import com.compilers.onlinestore.dao.*;
import com.compilers.onlinestore.factory.DAOFactory;
import com.compilers.onlinestore.model.Articulos.Articulo;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Pedidos.Pedido;
import com.compilers.onlinestore.exceptions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.compilers.onlinestore.util.ConexionBD;

public class Controladora {

    private Connection conn;
    private ClienteDAO clienteDAO;
    private ArticuloDAO articuloDAO;
    private PedidoDAO pedidoDAO;

    public Controladora() {
        try {
            conn = ConexionBD.getConnection();

            clienteDAO = DAOFactory.getClienteDAO(conn);//retorna clienteDaoimpl
            articuloDAO = DAOFactory.getArticuloDAO(conn);
            pedidoDAO = DAOFactory.getPedidoDAO(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    /*public void eliminarCliente(String email) {
        clienteDAO.eliminar(email);
    }*/
    public boolean eliminarCliente(String email) {

        Cliente c = clienteDAO.obtenerPorEmail(email);

        if (c == null) {
            return false;
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

    public boolean eliminarArticulo(String codigo)
            throws ArticuloNoExisteException {

        Articulo a = articuloDAO.obtenerPorCodigo(codigo);

        if (a == null) {
            throw new ArticuloNoExisteException("Articulo no encontrado");
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
