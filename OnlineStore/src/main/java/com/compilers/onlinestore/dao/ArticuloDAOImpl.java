package com.compilers.onlinestore.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.compilers.onlinestore.model.Articulos.Articulo;

public class ArticuloDAOImpl implements ArticuloDAO {

    private Connection conn;

    public ArticuloDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void crear(Articulo a) {
        String sql = "INSERT INTO articulos VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, a.getCodigo());
            ps.setString(2, a.getDescripcion());
            ps.setDouble(3, a.getPrecioVenta());
            ps.setDouble(4, a.getGastosEnvio());
            ps.setInt(5, a.getTiempoPreparacion());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
public void eliminar(String codigo) {

    String sql = "DELETE FROM articulos WHERE codigo = ?";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, codigo);
        ps.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @Override
    public Articulo obtenerPorCodigo(String codigo) {
        String sql = "SELECT * FROM articulos WHERE codigo = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Articulo(
                    rs.getString("codigo"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio_venta"),
                    rs.getDouble("gastos_envio"),
                    rs.getInt("tiempo_preparacion")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Articulo> obtenerTodos() {
        List<Articulo> lista = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM articulos");

            while (rs.next()) {
                lista.add(new Articulo(
                    rs.getString("codigo"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio_venta"),
                    rs.getDouble("gastos_envio"),
                    rs.getInt("tiempo_preparacion")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}