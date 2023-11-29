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
        usuario.setIdUser(proximoIdUser);

        System.out.println("Qual tipo de usuário você deseja cadastrar?");
        System.out.println("(1) Usuário Comum | (2) Usuário Premium | (3) Funcionário | (4) Administrador");
        System.out.println("-> ");

        int escolherUsuario = sc.nextInt();

        var StrEscolha = new String[]{"Usuário Premium", "Funcionário", "Administrador"};

        switch (escolherUsuario) {
            case 1:
                System.out.println("Você escolheu cadastrar Usuário Comum.");
                break;
            case 2:
            case 3:
            case 4:
                System.out.printf("Você escolheu cadastrar %s.\n", StrEscolha[escolherUsuario - 2]);

                usuario = switch (escolherUsuario) {
                    case 2 -> new UsuarioPremium();
                    case 3 -> new Funcionario();
                    case 4 -> new Admin();
                    default -> usuario;
                };
                usuario.setIdUser(proximoIdUser);
                break;
            default:
                System.out.println("Opção inválida. Cadastrando como Usuário Comum.\n");
        }

        String[] campos = {"CPF", "nome", "e-mail", "senha"};

        for (String campo : campos) {
            System.out.print(campo + ": ");
            String valor = sc.next();
            switch (campo) {
                case "CPF":
                    usuario.setCpf(valor);
                    break;
                case "nome":
                    usuario.setNome(valor);
                    break;
                case "e-mail":
                    usuario.setEmail(valor);
                    break;
                case "senha":
                    usuario.setSenha(valor);
                    break;
            }
        }

        //adicionar depois na lista para preservar a classe real dela para motivos de polimorfismo
        usuarios.add(usuario);
        cadastroUsuarioDAO.adiciona(usuario);

        switch (usuario) {
            case Admin admin -> {
                administradores.add(admin);
                cadastroUsuarioDAO.adicionaAdmin(admin);
            }
            case Funcionario funcionario -> {
                funcionarios.add(funcionario);
                cadastroUsuarioDAO.adicionaFuncionario(funcionario);
            }
            case UsuarioPremium usuarioPremium -> {
                usuariosPremium.add(usuarioPremium);
                cadastroUsuarioDAO.adicionaPremium(usuarioPremium);
            }
            case null, default -> {
            }
        }

        return usuario;
    }

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
            if (usuario.getIdUser() == idUser && !(usuario instanceof Admin) && !(usuario instanceof Funcionario) && !(usuario instanceof UsuarioPremium)) {
                return usuario;
            }
        }
        return null;
    }
    public static Admin buscarAdminPorId(int idUser) {
        for (Admin admin : administradores) {
            if (admin.getIdUser() == idUser) {
                return admin;
            }
        }
        return null;
    }
    public static Funcionario buscarFuncionarioPorId(int idUser) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getIdUser() == idUser) {
                return funcionario;
            }
        }
        return null;
    }
    public static UsuarioPremium buscarUsuarioPremiumPorId(int idUser) {
        for (UsuarioPremium usuarioPremium : usuariosPremium) {
            if (usuarioPremium.getIdUser() == idUser) {
                return usuarioPremium;
            }
        }
        return null;
    }
    public static ArrayList<Usuario> getUsuarios() {
        return (ArrayList<Usuario>) usuarios;
    }

    public static Usuario buscarPorId(int idUser) {
        return cadastroUsuarioDAO.buscarPorId(idUser);
    }

    public static void atualiza(Usuario usuarioAtualizado) {
        cadastroUsuarioDAO.atualiza(usuarioAtualizado);
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

}