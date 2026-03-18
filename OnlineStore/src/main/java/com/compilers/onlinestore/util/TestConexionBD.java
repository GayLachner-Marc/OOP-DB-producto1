package com.compilers.onlinestore.util;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexionBD {
    public static void main(String[] args) {
        try (Connection conn = ConexionBD.getConnection()) {
            System.out.println("Conectado a la base de datos exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
