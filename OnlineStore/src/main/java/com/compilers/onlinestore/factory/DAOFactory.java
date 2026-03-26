package com.compilers.onlinestore.factory;

import java.sql.Connection;

import com.compilers.onlinestore.dao.ClienteDAO;
import com.compilers.onlinestore.dao.ClienteDAOImpl;
import com.compilers.onlinestore.dao.ArticuloDAO;
import com.compilers.onlinestore.dao.ArticuloDAOImpl;
import com.compilers.onlinestore.dao.PedidoDAO;
import com.compilers.onlinestore.dao.PedidoDAOImpl;

public class DAOFactory {

    // ========================
    // CLIENTE DAO
    // ========================
    public static ClienteDAO getClienteDAO(Connection conn) {
        return new ClienteDAOImpl(conn);
    }

    // ========================
    // ARTICULO DAO
    // ========================
    public static ArticuloDAO getArticuloDAO(Connection conn) {
        return new ArticuloDAOImpl(conn);
    }

    // ========================
    // PEDIDO DAO
    // ========================
    public static PedidoDAO getPedidoDAO(Connection conn) {
        return new PedidoDAOImpl(conn);
    }
}