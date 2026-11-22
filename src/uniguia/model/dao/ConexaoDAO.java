package uniguia.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {
    
    // Configurações da conexão com o PostgreSQL local (pgAdmin)
    private static final String URL = "jdbc:postgresql://localhost:5432/uniguia_db";
    private static final String USER = "postgres"; 
    private static final String PASSWORD = "1234"; // Sua senha do pgAdmin

    // Método estático para obter a conexão com o banco
    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC do PostgreSQL não encontrado no projeto!", e);
        }
    }

    // Método main temporário para teste isolado de conexão
    public static void main(String[] args) {
        try {
            Connection conn = getConexao();
            if (conn != null) {
                System.out.println("✅ Conexão com o PostgreSQL (uniguia_db) realizada com sucesso!");
                conn.close();
            }
        } catch (Exception e) {
            System.err.println("❌ Erro ao conectar ao PostgreSQL: " + e.getMessage());
        }
    }
}