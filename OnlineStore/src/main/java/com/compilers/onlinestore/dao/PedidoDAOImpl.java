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

    // ===================== CREATE =====================
    @Override
    public void crear(Pedido p) {

        String sql = "INSERT INTO pedidos (numero_pedido, cliente_email, articulo_codigo, cantidad, fecha) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getNumeroPedido());
            ps.setString(2, p.getCliente().getEmail());
            ps.setString(3, p.getArticulo().getCodigo());
            ps.setInt(4, p.getCantidad());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ===================== DELETE =====================
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

    // ===================== OBTENER UNO =====================
    @Override
    public Pedido obtenerPorNumero(int numero) {

        String sql = """
        SELECT 
            p.numero_pedido,
            p.cantidad,
            p.fecha,
            
            c.email,
            c.nombre,
            c.domicilio,
            c.nif,
            c.tipo,

            a.codigo AS a_codigo,
            a.descripcion,
            a.precio_venta,
            a.gastos_envio,
            a.tiempo_preparacion

        FROM pedidos p
        JOIN clientes c ON p.cliente_email = c.email
        JOIN articulos a ON p.articulo_codigo = a.codigo
        WHERE p.numero_pedido = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, numero);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                // ARTICULO
                Articulo a = new Articulo(
                    rs.getString("a_codigo"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio_venta"),
                    rs.getDouble("gastos_envio"),
                    rs.getInt("tiempo_preparacion")
                );

                // CLIENTE
                Cliente c;
                String tipo = rs.getString("tipo");

                if ("premium".equalsIgnoreCase(tipo)) {
                    c = new ClientePremium(
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("domicilio"),
                        rs.getString("nif")
                    );
                } else {
                    c = new ClienteEstandar(
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("domicilio"),
                        rs.getString("nif")
                    );
                }

                Pedido p = new Pedido(
                    rs.getInt("numero_pedido"),
                    c,
                    a,
                    rs.getInt("cantidad")
                );

                // 🔥 IMPORTANTE: cargar fecha
                p.setFechaHora(rs.getTimestamp("fecha").toLocalDateTime());

                return p;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ===================== OBTENER TODOS =====================
    @Override
    public List<Pedido> obtenerTodos() {

        List<Pedido> lista = new ArrayList<>();

        String sql = """
        SELECT 
            p.numero_pedido,
            p.cantidad,
            p.fecha,
            
            c.email,
            c.nombre,
            c.domicilio,
            c.nif,
            c.tipo,

            a.codigo AS a_codigo,
            a.descripcion,
            a.precio_venta,
            a.gastos_envio,
            a.tiempo_preparacion

        FROM pedidos p
        JOIN clientes c ON p.cliente_email = c.email
        JOIN articulos a ON p.articulo_codigo = a.codigo
        """;

        try (Statement st = conn.createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                // ARTICULO
                Articulo a = new Articulo(
                    rs.getString("a_codigo"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio_venta"),
                    rs.getDouble("gastos_envio"),
                    rs.getInt("tiempo_preparacion")
                );

                // CLIENTE
                Cliente c;
                String tipo = rs.getString("tipo");

                if ("premium".equalsIgnoreCase(tipo)) {
                    c = new ClientePremium(
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("domicilio"),
                        rs.getString("nif")
                    );
                } else {
                    c = new ClienteEstandar(
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("domicilio"),
                        rs.getString("nif")
                    );
                }

                Pedido p = new Pedido(
                    rs.getInt("numero_pedido"),
                    c,
                    a,
                    rs.getInt("cantidad")
                );

                // 🔥 IMPORTANTE
                p.setFechaHora(rs.getTimestamp("fecha").toLocalDateTime());

                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ===================== ACTUALIZAR =====================
    @Override
    public void actualizar(Pedido p) {

        String sql = "UPDATE pedidos SET cantidad=? WHERE numero_pedido=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getCantidad());
            ps.setInt(2, p.getNumeroPedido());

            int filas = ps.executeUpdate();
            System.out.println("Filas actualizadas: " + filas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}