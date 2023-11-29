package src.Utils;

import src.Entities.User.Admin;
import src.Entities.User.Funcionario;
import src.Entities.User.Usuario;
import src.Entities.User.UsuarioPremium;
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
        if (usuarioJaExiste(usuario.getIdUser())) {
            System.out.println("Usuário com id " + usuario.getIdUser() + " já existe na tabela.");
            return;
        }
        String sql = "INSERT INTO Usuario(idUser, cpf, email, nome, senha) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, usuario.getIdUser());
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

    public void adicionaAdmin(Admin admin) {
        if (usuarioAdminJaExiste(admin.getidAdm())) {
            System.out.println("Administrador com id " + admin.getidAdm() + " já existe na tabela.");
            return;
        }
        String sql = "INSERT INTO Admin(idAdm, idUser) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, admin.getidAdm());
            stmt.setInt(2, admin.getIdUser());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionaFuncionario(Funcionario funcionario) {
        if (usuarioFuncionarioJaExiste(funcionario.getIdFunc())) {
            System.out.println("Funcionário com id " + funcionario.getIdFunc() + " já existe na tabela.");
            return;
        }
        String sql = "INSERT INTO Funcionario(idFunc, idUser) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, funcionario.getIdFunc());
            stmt.setInt(2, funcionario.getIdUser());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionaPremium(UsuarioPremium usuarioPremium) {
        if (usuarioPremiumJaExiste(usuarioPremium.getIdPre())) {
            System.out.println("Usuário Premium com id " + usuarioPremium.getIdPre() + " já existe na tabela.");
            return;
        }
        String sql = "INSERT INTO UsuarioPremium(idPre, idUser) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, usuarioPremium.getIdPre());
            stmt.setInt(2, usuarioPremium.getIdUser());
            stmt.execute();
            stmt.close();
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

    private boolean usuarioAdminJaExiste(int idAdm) {
        String sql = "SELECT COUNT(*) FROM admin WHERE idAdm = ?";
        try { PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idAdm);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            stmt.close(); return count > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean usuarioFuncionarioJaExiste(int idFunc) {
        String sql = "SELECT COUNT(*) FROM funcionario WHERE idFunc = ?";
        try { PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idFunc);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            stmt.close(); return count > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean usuarioPremiumJaExiste(int idFunc) {
        String sql = "SELECT COUNT(*) FROM usuariopremium WHERE idPre = ?";
        try { PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idFunc);
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
                Usuario user = new Usuario();
                user.setIdUser(resultSet.getInt("idUser"));
                user.setCpf(resultSet.getString("cpf"));
                user.setEmail(resultSet.getString("email"));
                user.setNome(resultSet.getString("nome"));
                user.setSenha(resultSet.getString("senha"));
                usuarios.add(user);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    // Read por ID
    public Usuario buscarPorId(int idUser) {
        String sql = "SELECT idUser, cpf, email, nome, senha FROM usuario WHERE idUser = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idUser);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                Usuario user;

                user = existeNaTabela(idUser, "admin") ? new Admin() :
                        existeNaTabela(idUser, "funcionario") ? new Funcionario() :
                        existeNaTabela(idUser, "usuariopremium") ? new UsuarioPremium() :
                                new Usuario();

                user.setIdUser(resultSet.getInt("idUser"));
                user.setCpf(resultSet.getString("cpf"));
                user.setEmail(resultSet.getString("email"));
                user.setNome(resultSet.getString("nome"));
                user.setSenha(resultSet.getString("senha"));

                stmt.close();
                return user;
            } else {
                stmt.close();
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean existeNaTabela(int idUser, String tableName) {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE idUser = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idUser);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                stmt.close();
                return count > 0;
            } else {
                stmt.close();
                return false;
            }
        } catch (SQLException e) {
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
            stmt.setInt(5, p.getIdUser());
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

    public int gerarId(String tabela, String colunaId) {
        String sql = "SELECT MAX(" + colunaId + ") + 1 AS proximoId FROM " + tabela;
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