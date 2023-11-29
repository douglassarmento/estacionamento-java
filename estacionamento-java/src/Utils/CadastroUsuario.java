package src.Utils;

import src.Entities.User.Admin;
import src.Entities.User.Funcionario;
import src.Entities.User.Usuario;
import src.Entities.User.UsuarioPremium;

import java.util.ArrayList;

import static src.Entities.User.Usuario.usuarios;
import static src.Main.Main.sc;

public class CadastroUsuario {

    static CadastroUsuarioDAO cadastroUsuarioDAO = new CadastroUsuarioDAO();
    private static ArrayList<Admin> administradores = new ArrayList<>();
    private static ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private static ArrayList<UsuarioPremium> usuariosPremium = new ArrayList<>();

    public static Usuario cadastrarUsuario() {

        Usuario usuario = new Usuario();
        int proximoIdUser = gerarId();
        usuario.setidUser(proximoIdUser);

        System.out.println("Qual tipo de usuário você deseja cadastrar?");
        System.out.println("(1) Usuário Comum | (2) Funcionário | (3) Usuário Premium | (4) Administrador");
        System.out.println("-> ");

        int escolherUsuario = sc.nextInt();

        var StrEscolha = new String[]{"Funcionário", "Usuário Premium", "Administrador"};

        switch (escolherUsuario) {
            case 1:
                System.out.println("Você escolheu cadastrar Usuário Comum.");
                break;
            case 2:
            case 3:
            case 4:
                System.out.printf("Você escolheu cadastrar %s. Informe o ID de %s:%n", StrEscolha[escolherUsuario - 2], StrEscolha[escolherUsuario - 2]);
                int nonUserId = Integer.parseInt(sc.next());

                usuario = switch (escolherUsuario) {
                    case 2 -> new Funcionario(nonUserId);
                    case 3 -> new UsuarioPremium(nonUserId);
                    case 4 -> new Admin(nonUserId);
                    default -> usuario;
                };
                usuario.setidUser(proximoIdUser);

            default:
                System.out.println("Opção inválida.");
        }

        //adicionar depois na lista para preservar a classe real dela para motivos de polimorfismo
        usuarios.add(usuario);
        cadastroUsuarioDAO.adiciona(usuario);

        switch (usuario) {
            case Admin admin -> administradores.add(admin);
            case Funcionario funcionario -> funcionarios.add(funcionario);
            case UsuarioPremium usuarioPremium -> usuariosPremium.add(usuarioPremium);
            case null, default -> {
            }
        }

        return usuario;
    }

    //public static void cadastrarUsuario() {
    //    cadastrarUsuario();
    //}

    public static void listarPessoas() {
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
    public static Usuario buscarUsuarioComumPorId(int idUser) {
        for (Usuario usuario : usuarios) {
            if (usuario.getidUser() == idUser && !(usuario instanceof Admin) && !(usuario instanceof Funcionario) && !(usuario instanceof UsuarioPremium)) {
                return usuario;
            }
        }
        return null;
    }
    public static Admin buscarAdminPorId(int idUser) {
        for (Admin admin : administradores) {
            if (admin.getidUser() == idUser) {
                return admin;
            }
        }
        return null;
    }
    public static Funcionario buscarFuncionarioPorId(int idUser) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getidUser() == idUser) {
                return funcionario;
            }
        }
        return null;
    }
    public static UsuarioPremium buscarUsuarioPremiumPorId(int idUser) {
        for (UsuarioPremium usuarioPremium : usuariosPremium) {
            if (usuarioPremium.getidUser() == idUser) {
                return usuarioPremium;
            }
        }
        return null;
    }
    public static ArrayList<Usuario> getPessoas() {
        return (ArrayList<Usuario>) usuarios;
    }

    public static Usuario buscarPorId(int idUser) {
        return cadastroUsuarioDAO.buscarPorId(idUser);
    }

    public static void atualiza(Usuario usuarioAtualizada) {
        cadastroUsuarioDAO.atualiza(usuarioAtualizada);
    }

    public static void exclui(int idUserExcluir) {
        cadastroUsuarioDAO.exclui(idUserExcluir);
    }

    public static void limparTabela() {
        cadastroUsuarioDAO.limparTabela();
    }

    public static int gerarId() {
        return cadastroUsuarioDAO.gerarId();
    }

    public static boolean verificarCPF(String cpfLogin) {
        for (Usuario usuario : CadastroUsuario.getPessoas()) {
            if (usuario.getCpf().equals(cpfLogin)) {
                return true;
            }
        }
        return false;
    }
}