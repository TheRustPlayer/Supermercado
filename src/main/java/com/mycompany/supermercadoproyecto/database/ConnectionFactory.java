package com.mycompany.supermercadoproyecto.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Datos de la conexión. Cámbialos si hace falta.
    private static final String URL = "jdbc:mysql://localhost:3306/supermercado?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "MEDAC";

    /**
     * Abre una conexión a MySQL.
     */
    public static Connection open() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Error al abrir la conexión: " + e.getMessage());
            return null;
        }
    }

    /**
     * Cierra la conexión manualmente.
     */
    public static void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}
