package src.Main;

import src.Entities.*;
import src.Entities.User.Admin;
import src.Entities.User.Usuario;
import src.Entities.User.UsuarioPremium;
import src.Utils.CadastroUsuario;
import src.Utils.CadastroVeiculo;
import src.Entities.parkLog;

import java.util.Scanner;
public class Main {

    public static Scanner sc;

    public static void main(String[] args) {
        int repetir;

        sc = new Scanner(System.in);
        CadastroUsuario cadastroUsuario = new CadastroUsuario();
        CadastroVeiculo cadastroVeiculo = new CadastroVeiculo();

        do {
            System.out.println("---------- Bem-vindo(a) ao Estacionamento ----------");
            System.out.println();
            System.out.println("Primeiro acesso? (1) Sim | (2) Não -> ");


            int primeiroAcesso = sc.nextInt();


            if (primeiroAcesso == 1) {
            int proximoIdUser = 0;

                System.out.println();
                System.out.println("Vamos realizar seu cadastro.");
                System.out.println();

                Usuario usuario = criarUsuario(sc);
                proximoIdUser = cadastroUsuario.gerarId();
                usuario.setidUser(proximoIdUser);
                cadastroUsuario.cadastrarUsuario(usuario);
                System.out.println();

                System.out.println("Qual tipo de usuário você deseja cadastrar?");
                System.out.println("(1) Usuário Comum | (2) Funcionário | (3) Usuário Premium | (4) Administrador");
                System.out.println("-> ");
                int escolherUsuario = sc.nextInt();
                if (escolherUsuario == 1){
                    System.out.println("Você escolheu cadastrar Usuário Comum.");
                } else if (escolherUsuario == 2){
                    Funcionario funcionario = new Funcionario();
                    System.out.println("Você escolheu cadastrar Funcionário.");
                    System.out.println("Informe o ID de Funcionário:");
                    funcionario.setIdFunc(Integer.parseInt(sc.next()));
                    funcionario.setidUser(proximoIdUser);
                } else if (escolherUsuario == 3){
                    UsuarioPremium premium = new UsuarioPremium();
                    System.out.println("Você escolheu cadastrar Usuário Premium.");
                    System.out.println("Informe o ID de Usuário Premium:");
                    premium.setIdPPre(Integer.parseInt(sc.next()));
                    premium.setidUser(proximoIdUser);
                } else if (escolherUsuario == 4){
                    Admin admin = new Admin();
                    System.out.println("Você escolheu cadastrar Administrador.");
                    System.out.println("Informe o ID de Administrador:");
                    admin.setIdAdm(Integer.parseInt(sc.next()));
                    admin.setidUser(proximoIdUser);
                } else {
                    System.out.println("Opção inválida.");
                }

                System.out.println();
                Usuario usuarioCadastrada = cadastroUsuario.buscarPorId(usuario.getidUser());

                if (usuarioCadastrada != null) {
                    System.out.println("Cadastro finalizado: " + usuarioCadastrada);
                } else {
                    System.out.println("Usuário não encontrado.");
                }

                System.out.println();
                cadastroUsuario.listarPessoas();
                System.out.println();

                System.out.println("Atualizar os dados do usuário recém-cadastrado? (1) Sim | (2) Não -> ");
                int opcaoAtualizar = sc.nextInt();

                if (opcaoAtualizar == 1) {
                    Usuario usuarioAtualizado = criarUsuario(sc);
                    usuarioAtualizado.setidUser(usuarioCadastrada.getidUser());
                    cadastroUsuario.atualiza(usuarioAtualizado);
                } else {
                    System.out.println("Tudo bem! Seguindo...");
                }

                System.out.println();

                System.out.println("Deletar um usuário? (1) Sim / (2) Não -> ");
                int opcaoExcluir = sc.nextInt();

                if (opcaoExcluir == 1) {
                    System.out.println("Informe o ID do usuário que deseja excluir:");
                    int idUserExcluir = sc.nextInt();
                    cadastroUsuario.exclui(idUserExcluir);
                } else {
                    System.out.println("Tudo bem! Seguindo...");
                }

                System.out.println("Deseja limpar toda a tabela src.Classes.User.Usuario? (1) Sim / (2) Não -> ");
                int opcaoLimparTabela = sc.nextInt();

                if (opcaoLimparTabela == 1) {
                    cadastroUsuario.limparTabela();
                } else {
                    System.out.println("Tudo bem! Seguindo...");
                }

            } else if (primeiroAcesso == 2) {
                System.out.println("Parece que você já possui um cadastro no sistema. Informe seu CPF para realizar o login:");
                String cpfLogin = sc.next();
                if (realizarLogin(cadastroUsuario, cpfLogin)) {
                    System.out.println("Login realizado com sucesso!");
                } else {
                    System.out.println("CPF não cadastrado. Por favor, cadastre-se.");
                }
                System.out.println("Por favor, escolha uma das opções a seguir.");

                System.out.println("Realizar login: (1) Usuário");
                System.out.println("Cadastrar: (2) Veículo");
                System.out.println("Listar: (3) Pessoas / (4) Veículos");
                System.out.println("Verificar: (5) Placa do Veículo");
                System.out.println("-> ");
                int escolha = sc.nextInt();
                switch (escolha) {
                    case 1:
                       System.out.println("Com qual tipo de usuário você deseja realizar o login?");
                       System.out.println("Realizar login como: (1) Administrador / (2) Funcionário / (3) Pessoa Premium / (4) Usuário Comum");
                       System.out.println("-> ");
                       int escolherUsuario = sc.nextInt();

                       if (escolherUsuario == 1) {
                           System.out.println("Você escolheu realizar login como: Administrador.");
                           System.out.println("Por favor, informe seus dados abaixo.");
                           System.out.println("Digite o seu idAdm:");
                           int idLoginAdm = sc.nextInt();
                           System.out.println("Digite a sua senha:");
                           String senhaLoginAdm = sc.next();
                           Admin adminCadastrado = cadastroUsuario.buscarAdminPorId(idLoginAdm);
                           if (adminCadastrado != null && adminCadastrado.getSenha().equals(senhaLoginAdm)){
                               System.out.println("Login como Administrador realizado com sucesso!");
                               parkLog pl = new parkLog();
                               pl.parkLogSistema(sc);
                           } else{
                               System.out.println("Seus dados estão errados, tente novamente.");
                           }

                       } else if (escolherUsuario == 2){
                           System.out.println("Você escolheu realizar login como: Funcionário.");
                           System.out.println("Por favor, informe seus dados abaixo.");
                           System.out.println("Digite o seu idFunc:");
                           int idLoginFuncionario = sc.nextInt();
                           System.out.println("Digite a sua senha:");
                           String senhaLoginFuncionario = sc.next();
                           Funcionario funcionarioCadastrado = cadastroUsuario.buscarFuncionarioPorId(idLoginFuncionario);
                           if (funcionarioCadastrado != null && funcionarioCadastrado.getSenha().equals(senhaLoginFuncionario)){
                               System.out.println("Login como Funcionário realizado com sucesso!");
                               parkLog pl = new parkLog();
                               pl.parkLogSistema(sc);
                           } else{
                               System.out.println("Seus dados estão errados, tente novamente.");
                           }

                       } else if (escolherUsuario == 3){
                          System.out.println("Você escolheu realizar login como: Pessoa Premium.");
                          System.out.println("Por favor, informe seus dados abaixo.");
                           System.out.println("Digite o seu idPPre:");
                           int idLoginPremium = sc.nextInt();
                           System.out.println("Digite a sua senha:");
                           String senhaLoginPremium = sc.next();
                           UsuarioPremium usuarioPremiumCadastrado = cadastroUsuario.buscarUsuarioPremiumPorId(idLoginPremium);
                           if (usuarioPremiumCadastrado != null && usuarioPremiumCadastrado.getSenha().equals(senhaLoginPremium)){
                               System.out.println("Login como Pessoa Premium realizado com sucesso!");
                               parkLog pl = new parkLog();
                               int idPremium = //falta adicionar codigo
                               int idCarroPremium = //falta adicionar codigo
                               pl.parkLogUsuario(idPremium, idCarroPremium, sc);
                           } else{
                               System.out.println("Seus dados estão errados, tente novamente.");
                           }

                       } else if (escolherUsuario == 4) {
                           System.out.println("Você escolheu realizar login como: Usuário Comum.");
                           System.out.println("Por favor, informe seus dados abaixo.");
                           System.out.println("Digite o seu idUser:");
                           int idLoginUsuario = sc.nextInt();
                           System.out.println("Digite a sua senha:");
                           String senhaLoginUsuario = sc.next();
                           Usuario usuarioCadastrado = cadastroUsuario.buscarUsuarioComumPorId(idLoginUsuario);
                           if (usuarioCadastrado != null && usuarioCadastrado.getSenha().equals(senhaLoginUsuario)){
                               System.out.println("Login como Usuário Comum realizado com sucesso!");
                               parkLog pl = new parkLog();
                               int idComum = //falta adicionar codigo
                               int idCarroComum = //falta adicionar codigo
                               pl.parkLogUsuario(idComum, idCarroComum, sc);
                           } else{
                               System.out.println("Seus dados estão errados, tente novamente.");
                           }

                       } else {
                            System.out.println("Opção inválida.");}
                        break;
                    case 2:
                        Carro carro = criarCarro(sc);
                        cadastroVeiculo.cadastrarVeiculo(carro);
                        break;
                    case 3:
                        cadastroUsuario.listarPessoas();
                        break;
                    case 4:
                        cadastroVeiculo.listarVeiculos();
                        break;
                    case 5:
                        System.out.println("Informe a placa do carro: ");
                        String placaVerificar = sc.next();
                        verificarPlaca(cadastroVeiculo, placaVerificar);
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } else {
                System.out.println("Opção inválida.");
            }
            System.out.println("Para ver novamente, digite 0");
            repetir = sc.nextInt();
        } while (repetir==0);
    }
    public static boolean realizarLogin(CadastroUsuario cadastroUsuario, String cpfLogin) {
        for (Usuario usuario : cadastroUsuario.getPessoas()) {
            if (usuario.getCpf().equals(cpfLogin)) {
                return true;
            }
        }
        return false;
    }
    public static void verificarPlaca(CadastroVeiculo cadastroVeiculo, String placaVerificar) {
        boolean carroCadastrado = cadastroVeiculo.verificarCarroCadastrado(placaVerificar);
        if (carroCadastrado) {
            System.out.println("O carro com a placa " + placaVerificar + " está cadastrado.");
        } else {
            System.out.println("O carro com a placa " + placaVerificar + " não está cadastrado.");
        }
    }
    public static Usuario criarUsuario(Scanner sc) {
        Usuario usuario = new Usuario();
        System.out.println("Informe o CPF:");
        usuario.setCpf(sc.next());
        System.out.println("Informe o nome:");
        usuario.setNome(sc.next());
        System.out.println("Informe o e-mail:");
        usuario.setEmail(sc.next());
        System.out.println("Informe a senha:");
        usuario.setSenha(sc.next());
        return usuario;
    }
    public static Carro criarCarro(Scanner sc) {
        Carro carro = new Carro();
        System.out.println("Informe a Placa:");
        carro.setPlaca(sc.next());
        System.out.println("Informe a Cor:");
        carro.setCor(sc.next());
        return carro;
    }
}