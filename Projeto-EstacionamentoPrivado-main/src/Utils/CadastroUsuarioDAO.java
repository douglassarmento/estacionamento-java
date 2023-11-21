package src.Utils;

import src.Entities.User.Usuario;
import src.Main.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CadastroUsuarioDAO {
    private Connection connection;

    public CadastroUsuarioDAO() {
        this.connection = new Conexao().GeraConexao();
    }

    // Create
    public void adiciona(Usuario usuario) {
        if (usuarioJaExiste(usuario.getidUser())) {
            System.out.println("Usuário com id " + usuario.getidUser() + " já existe na tabela.");
            return;
        }
        String sql = "INSERT INTO src.Classes.User.Usuario(idUser, cpf, email, nome, senha) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, usuario.getidUser());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getNome());
            stmt.setString(5, usuario.getSenha());
            stmt.execute();
            stmt.close();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean usuarioJaExiste(int idUser) {
    // Verifica se o usuário já existe no banco de dados
        String sql = "SELECT COUNT(*) FROM usuario WHERE idUser = ?";
        try { PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idUser);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            stmt.close(); return count > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Read
    public ArrayList<Usuario> listar() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT idUser, cpf, email, nome, senha FROM usuario";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Usuario p = new Usuario();
                p.setidUser(resultSet.getInt("idUser"));
                p.setCpf(resultSet.getString("cpf"));
                p.setEmail(resultSet.getString("email"));
                p.setNome(resultSet.getString("nome"));
                p.setSenha(resultSet.getString("senha"));
                usuarios.add(p);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    // Read por ID
    public Usuario buscarPorId(int idUser) { String sql = "SELECT idUser, cpf, email, nome, senha FROM usuario WHERE idUser = ?";
        try { PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idUser); ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) { Usuario p = new Usuario();
                p.setidUser(resultSet.getInt("idUser"));
                p.setCpf(resultSet.getString("cpf"));
                p.setEmail(resultSet.getString("email"));
                p.setNome(resultSet.getString("nome"));
                p.setSenha(resultSet.getString("senha"));
                stmt.close();
                return p;
            } else {
                stmt.close();
                return null;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update
    public void atualiza(Usuario p) {
        String sql = "UPDATE usuario SET cpf = ?, email = ?, nome = ?, senha = ? WHERE idUser = ?";
        try { PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, p.getCpf());
            stmt.setString(2, p.getEmail());
            stmt.setString(3, p.getNome());
            stmt.setString(4, p.getSenha());
            stmt.setInt(5, p.getidUser());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Usuário atualizado com sucesso!");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete
    public void exclui(int idUser) {
        String sql = "DELETE FROM usuario WHERE idUser = ?";
        try { PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idUser);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Usuário excluído com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 💣 Nuke 💣
    public void limparTabela() {
        String sql = "DELETE FROM usuario";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Tabela Usuário limpa com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Gera ID automaticamente
    public int gerarId() {
        String sql = "SELECT MAX(idUser) + 1 AS proximoId FROM usuario";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("proximoId");
            } else {
                return 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}