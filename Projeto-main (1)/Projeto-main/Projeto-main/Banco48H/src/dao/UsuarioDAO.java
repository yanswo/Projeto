
package dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UsuarioDAO {
     public boolean cadastrarUsuario(String cpf, String nome, String senha) {
        String sql = "INSERT INTO usuarios (cpf, nome, senha) VALUES (?, ?, ?)";
        try (Connection conexao = ConexaoDB.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);
            stmt.setString(2, nome);
            stmt.setString(3, senha);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; 
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
            return false;
        }
    }
     
      public Usuario validarLogin(String nome, String senha) {
        String sql = "SELECT * FROM usuarios WHERE nome = ? AND senha = ?";
        try (Connection conexao = ConexaoDB.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
         
                String cpf = rs.getString("cpf");
                String nomeUsuario = rs.getString("nome");
                String senhaUsuario = rs.getString("senha");
                return new Usuario(nomeUsuario, cpf, senhaUsuario); 
            }
        } catch (Exception e) {
            System.out.println("Erro ao validar login: " + e.getMessage());
        }
        return null; 
    }
}
