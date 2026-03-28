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

        // especifica columnas 
        String sql = "INSERT INTO articulos (codigo, descripcion, precio_venta, gastos_envio, tiempo_preparacion) VALUES (?, ?, ?, ?, ?)";

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
    public void actualizar(Articulo a) {

        String sql = "UPDATE articulos SET descripcion = ?, precio_venta = ?, gastos_envio = ?, tiempo_preparacion = ? WHERE codigo = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, a.getDescripcion());
            ps.setDouble(2, a.getPrecioVenta());
            ps.setDouble(3, a.getGastosEnvio());
            ps.setInt(4, a.getTiempoPreparacion());
            ps.setString(5, a.getCodigo());

            int filas = ps.executeUpdate();
            System.out.println("Filas actualizadas: " + filas);

            if (filas == 0) {
                System.out.println("No se encontró el artículo para actualizar");
            } else {
                System.out.println("Artículo actualizado correctamente");
            }

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