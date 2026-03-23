package com.compilers.onlinestore.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Clientes.ClienteEstandar;
import com.compilers.onlinestore.model.Clientes.ClientePremium;

public class ClienteDAOImpl implements ClienteDAO {

    private Connection conn;

    public ClienteDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void crear(Cliente cliente) {
        String sql = "INSERT INTO clientes VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getEmail());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getDomicilio());
            ps.setString(4, cliente.getNif());
            ps.setString(5, cliente instanceof ClientePremium ? "PREMIUM" : "ESTANDAR");

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente obtenerPorEmail(String email) {
        String sql = "SELECT * FROM clientes WHERE email = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapCliente(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Cliente> obtenerTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Statement st = conn.createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                lista.add(mapCliente(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void actualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre=?, domicilio=?, nif=?, tipo_cliente=? WHERE email=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDomicilio());
            ps.setString(3, cliente.getNif());
            ps.setString(4, cliente instanceof ClientePremium ? "PREMIUM" : "ESTANDAR");
            ps.setString(5, cliente.getEmail());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String email) {
        String sql = "DELETE FROM clientes WHERE email = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Cliente mapCliente(ResultSet rs) throws SQLException {

        String tipo = rs.getString("tipo_cliente");

        if (tipo.equals("PREMIUM")) {
            return new ClientePremium(
                rs.getString("nombre"),
                rs.getString("domicilio"),
                rs.getString("nif"),
                rs.getString("email")
            );
        } else {
            return new ClienteEstandar(
                rs.getString("nombre"),
                rs.getString("domicilio"),
                rs.getString("nif"),
                rs.getString("email")
            );
        }
    }
}