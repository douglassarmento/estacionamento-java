package src.Main;

import src.Entities.*;
import src.Entities.User.Admin;
import src.Entities.User.Funcionario;
import src.Entities.User.Usuario;
import src.Entities.User.UsuarioPremium;
import src.Utils.CadastroUsuario;
import src.Utils.CadastroVeiculo;
import src.Entities.parkLog;

import java.util.Scanner;
public class Main {

    public static Scanner sc;

    public static void main(String[] args) {
        int repetir = 0;

        sc = new Scanner(System.in);
        //CadastroVeiculo cadastroVeiculo = new CadastroVeiculo();

        do {
            System.out.println("---------- Bem-vindo(a) ao Estacionamento ----------");
            System.out.println();
            System.out.println("Primeiro acesso? (1) Sim | (2) Não -> ");


            int primeiroAcesso = sc.nextInt();


            if (primeiroAcesso == 1) {
                System.out.println();
                System.out.println("Vamos realizar seu cadastro.");
                System.out.println();

                Usuario usuario = CadastroUsuario.cadastrarUsuario();

                if (usuario != null) {
                    System.out.println("Cadastro finalizado: " + usuario);
                } else {
                    System.out.println("Usuário não encontrado.");
                }

                System.out.println("Atualizar os dados do usuário recém-cadastrado? (1) Sim | (2) Não -> ");
                int opcaoAtualizar = sc.nextInt();

                if (opcaoAtualizar == 1) {
                    Usuario usuarioAtualizado = Usuario.criarUsuario();
                    usuarioAtualizado.setidUser(usuario.getidUser());
                    CadastroUsuario.atualiza(usuarioAtualizado);

                }

                System.out.println("alocando para area de registrados");
                continue;

            } else if (primeiroAcesso == 2) {
                System.out.println("Parece que você já possui um cadastro no sistema. Informe seu CPF para realizar o login:");
                String cpfLogin = sc.next();
                if (realizarLogin(cpfLogin)) {
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
                        System.out.println("Por favor, informe seus dados abaixo.");

                        System.out.println("Digite o seu idUser:");
                        int idLogin= sc.nextInt();

                        System.out.println("Digite a sua senha:");
                        String senhaLogin = sc.next();

                        Usuario usuario = CadastroUsuario.buscarPorId(idLogin);

                        if (usuario != null && usuario.getSenha().equals(senhaLogin)){

                            String tipo = "Usuario Comum";

                            switch (usuario) {
                                case Admin admin -> tipo = "ADMIN";
                                case Funcionario funcionario -> tipo = "FUNCIONARIO";
                                case UsuarioPremium usuarioPremium -> tipo = "usuario PREMIUM";
                                case null, default -> {
                                }
                            }

                            System.out.printf("Login como %s realizado com sucesso! %n", tipo);

                        } else{
                            System.out.println("Seus dados estão errados, tente novamente.");
                        }
                    case 2:
                        Carro carro = criarCarro();
                        CadastroVeiculo.cadastrarVeiculo(carro);
                        break;
                    case 3:
                        CadastroUsuario.listarPessoas();
                        break;
                    case 4:
                        CadastroVeiculo.listarVeiculos();
                        break;
                    case 5:
                        System.out.println("Informe a placa do carro: ");
                        String placaVerificar = sc.next();
                        verificarPlaca(placaVerificar);
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

    public static boolean realizarLogin(String cpfLogin) {
        for (Usuario usuario : CadastroUsuario.getPessoas()) {
            if (usuario.getCpf().equals(cpfLogin)) {
                return true;
            }
        }
        return false;
    }
    public static void verificarPlaca(String placaVerificar) {
        boolean carroCadastrado = CadastroVeiculo.verificarCarroCadastrado(placaVerificar);
        if (carroCadastrado) {
            System.out.println("O carro com a placa " + placaVerificar + " está cadastrado.");
        } else {
            System.out.println("O carro com a placa " + placaVerificar + " não está cadastrado.");
        }
    }

    public static Carro criarCarro() {
        Carro carro = new Carro();
        System.out.println("Informe a Placa:");
        carro.setPlaca(sc.next());
        System.out.println("Informe a Cor:");
        carro.setCor(sc.next());
        return carro;
    }
}