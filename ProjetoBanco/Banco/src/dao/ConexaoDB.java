package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class ConexaoDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/banco_48h";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "assadads21"; 
    public static Connection conectar() {
        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            if (conexao != null) {
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            }
            return conexao;
        } catch (SQLException e) {
            System.out.println("Erro na conexão com o banco de dados: " + e.getMessage());
            return null;
        }
    }
}
