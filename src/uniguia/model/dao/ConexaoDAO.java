package uniguia.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

    private static final String URL =
            "jdbc:postgresql://[::1]:5432/uniguia_db";

    private static final String USER = "postgres";
    private static final String PASSWORD = "UniGuia2026";

    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgreSQL não encontrado!", e);
        }
    }

    public static void main(String[] args) {
        try (Connection conn = getConexao()) {
            System.out.println("CONEXÃO COM POSTGRESQL REALIZADA!");
        } catch (SQLException e) {
            System.out.println("ERRO NA CONEXÃO: " + e.getMessage());
        }
    }
}