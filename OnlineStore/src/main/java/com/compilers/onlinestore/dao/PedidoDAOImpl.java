package com.compilers.onlinestore.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.compilers.onlinestore.model.Pedidos.Pedido;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Clientes.ClienteEstandar;
import com.compilers.onlinestore.model.Clientes.ClientePremium;
import com.compilers.onlinestore.model.Articulos.Articulo;

public class PedidoDAOImpl implements PedidoDAO {

    private Connection conn;

    public PedidoDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void crear(Pedido p) {
        String sql = "INSERT INTO pedidos VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getNumeroPedido());
            ps.setString(2, p.getCliente().getEmail());
            ps.setString(3, p.getArticulo().getCodigo());
            ps.setInt(4, p.getCantidad());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setBoolean(6, false);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
public void eliminar(int numero) {

    String sql = "DELETE FROM pedidos WHERE numero_pedido = ?";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, numero);
        ps.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @Override
    public Pedido obtenerPorNumero(int numero) {
        String sql = "SELECT * FROM pedidos WHERE numero_pedido = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, numero);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // ⚠ simplificado: deberías buscar cliente/articulo vía DAO
                return new Pedido(
                    rs.getInt("numero_pedido"),
                    null,
                    null,
                    rs.getInt("cantidad")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Pedido> obtenerTodos() {
        List<Pedido> lista = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM pedidos");

            while (rs.next()) {
                lista.add(new Pedido(
                    rs.getInt("numero_pedido"),
                    null,
                    null,
                    rs.getInt("cantidad")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void actualizar(Pedido p) {
        String sql = "UPDATE pedidos SET cantidad=?, enviado=? WHERE numero_pedido=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getCantidad());
            ps.setBoolean(2, p.estaEnviado());
            ps.setInt(3, p.getNumeroPedido());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}