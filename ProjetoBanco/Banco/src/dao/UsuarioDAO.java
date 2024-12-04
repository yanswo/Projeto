package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean cadastrarUsuario(String cpf, String nome, String senha, double saldo) {
        String sql = "INSERT INTO usuarios (cpf, nome, senha, saldo) VALUES (?, ?, ?, ?)";
        try (Connection conexao = ConexaoDB.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);
            stmt.setString(2, nome);
            stmt.setString(3, senha);
            stmt.setDouble(4, saldo);
            
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
                double saldo = rs.getDouble("saldo");
                return new Usuario(nomeUsuario, cpf, senhaUsuario, saldo); 
            }
        } catch (Exception e) {
            System.out.println("Erro ao validar login: " + e.getMessage());
        }
        return null; 
    }
    
    public Usuario buscarUsuarioPorCpf(String cpf) {
        String sql = "SELECT * FROM usuarios WHERE cpf = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setCpf(rs.getString("cpf"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSaldo(rs.getDouble("saldo"));
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário por CPF: " + e.getMessage());
        }
        return null;
    }

    public boolean atualizarSaldo(String cpf, double novoSaldo) {
        String sql = "UPDATE usuarios SET saldo = ? WHERE cpf = ?";
        try (Connection conexao = ConexaoDB.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setDouble(1, novoSaldo);
            stmt.setString(2, cpf);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar saldo: " + e.getMessage());
            return false;
        }
    }
    
   public boolean atualizarSenha(String cpf, String nome, String novaSenha) {
    String sql = "UPDATE usuarios SET senha = ? WHERE cpf = ? AND nome = ?";
    
    try (Connection conn = ConexaoDB.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, novaSenha);
        stmt.setString(2, cpf);
        stmt.setString(3, nome);

        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0; 
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
   
   public Usuario RedefinirSenha(String cpf, String nome) {
    String sql = "SELECT * FROM usuarios WHERE cpf = ? AND nome = ?";
    try (Connection conn = ConexaoDB.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, cpf);
        stmt.setString(2, nome);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setCpf(rs.getString("cpf"));
            usuario.setNome(rs.getString("nome"));
            usuario.setSaldo(rs.getDouble("saldo"));
            return usuario;
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar usuário por CPF e Nome: " + e.getMessage());
    }
    return null;
}
}
