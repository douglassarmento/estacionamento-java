import java.util.ArrayList;
public class CadastroUsuario {
    CadastroUsuarioDAO cadastroUsuarioDAO = new CadastroUsuarioDAO();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Admin> administradores = new ArrayList<>();
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private ArrayList<UsuarioPremium> usuariosPremium = new ArrayList<>();
    public void cadastrarUsuario(Usuario p) {
        usuarios.add(p);
        cadastroUsuarioDAO.adiciona(p);
        if (p instanceof Admin) {
            administradores.add((Admin) p);
        } else if (p instanceof Funcionario) {
            funcionarios.add((Funcionario) p);
        } else if (p instanceof UsuarioPremium) {
            usuariosPremium.add((UsuarioPremium) p);
        }
    }
    public void listarPessoas() {
        ArrayList<Usuario> usuarios = cadastroUsuarioDAO.listar();
        if (usuarios.isEmpty()) {
            System.out.println("Não há pessoas cadastradas.");
        } else {
            System.out.println("Lista de pessoas:");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }
    public Usuario buscarUsuarioComumPorId(int idUser) {
        for (Usuario usuario : usuarios) {
            if (usuario.getidUser() == idUser && !(usuario instanceof Admin) && !(usuario instanceof Funcionario) && !(usuario instanceof UsuarioPremium)) {
                return usuario;
            }
        }
        return null;
    }
    public Admin buscarAdminPorId(int idUser) {
        for (Admin admin : administradores) {
            if (admin.getidUser() == idUser) {
                return admin;
            }
        }
        return null;
    }
    public Funcionario buscarFuncionarioPorId(int idUser) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getidUser() == idUser) {
                return funcionario;
            }
        }
        return null;
    }
    public UsuarioPremium buscarUsuarioPremiumPorId(int idUser) {
        for (UsuarioPremium usuarioPremium : usuariosPremium) {
            if (usuarioPremium.getidUser() == idUser) {
                return usuarioPremium;
            }
        }
        return null;
    }
    public ArrayList<Usuario> getPessoas() {
        return usuarios;
    }

    public Usuario buscarPorId(int idUser) {
        return cadastroUsuarioDAO.buscarPorId(idUser);
    }

    public void atualiza(Usuario usuarioAtualizada) {
        cadastroUsuarioDAO.atualiza(usuarioAtualizada);
    }

    public void exclui(int idUserExcluir) {
        cadastroUsuarioDAO.exclui(idUserExcluir);
    }

    public void limparTabela() {
        cadastroUsuarioDAO.limparTabela();
    }

    public int gerarId() {
        return cadastroUsuarioDAO.gerarId();
    }
}