package src.Utils;

import java.util.ArrayList;
import java.util.List;
import src.Entities.Carro;
import src.Entities.Cartao;
import src.Entities.ParkLog;
import src.Entities.User.Usuario;
import src.Entities.Vaga;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Scanner;

import static src.Entities.Carro.cadastrarCarro;
import static src.Entities.Cartao.cartoesCadastrados;
import static src.Entities.Vaga.encontrarVagaPorNumero;
import static src.Entities.Vaga.vagas;
import static src.Main.Main.sc;
import static src.Utils.CadastroVeiculo.cadastroVeiculoDAO;

public class FuncoesUsuario {
    private static List<ParkLog> parkLogs = new ArrayList<>();

    public static void painelUsuario(int idUser){
        System.out.println("\n---------- Bem-vindo(a) ao Sistema de Vagas do Estacionamento ----------\n");
        System.out.println("\nDigite uma das opções abaixo para prosseguir.\n");
        System.out.println("(1) Visualizar Vaga(s) | (2) Editar Vaga(s) | (3) Cadastrar Veículo(s)");
        System.out.println("(4) Listar Veículo(s) | (5) Cadastrar Cartão(ões) | (6) Escolher forma(s) de pagamento");
        System.out.println("(7) Liberar vaga(s)\n-> ");
        int opcaoUsuario = sc.nextInt();
        sc.nextLine();
        switch (opcaoUsuario) {
            case 1:
                System.out.println("Você escolheu a opção: Visualizar Vaga(s).\n");
                visualizarVaga();
                System.out.println("Escolha uma das opções para prosseguir.");
                System.out.println("(1) Alugar vaga(s) | (2) Cancelar");
                System.out.println("-> ");
                int escolhaUsuario = sc.nextInt();
                if (escolhaUsuario == 1){
                    System.out.println("Você escolheu a opção: Agendar vaga(s).\n");
                    System.out.println("Insira o ID do seu veículo: ");
                    int idCar = sc.nextInt();
                    agendarVaga(idUser, idCar);
                } else if (escolhaUsuario == 2){
                    System.out.println("Você escolheu a opção: Cancelar.");
                    //TODO: Criar uma estrutura de loop aqui para o usuário voltar quando quiser.
                } else {
                    System.out.println("Opção inválida.");
                }
                break;
            case 2:
                System.out.println("Você escolheu a opção: Editar Vaga(s).\n");
                System.out.println("Insira o ID do seu veículo: ");
                int idCar = sc.nextInt();
                editarVagaUsuario(idUser, idCar);
                break;
            case 3:
                System.out.println("Você escolheu a opção: Cadastrar Veículo(s).\n");
                Carro carro = new Carro();
                CadastroVeiculo.cadastrarVeiculo(carro, idUser);
                break;
            case 4:
                System.out.println("Você escolheu a opção: Listar Veículo(s).\n");
                // TODO: Listar apenas os veículos cadastrados pelo usuário
                listarVeiculos();
                break;
            case 5:
                System.out.println("Você escolheu a opção: Cadastrar Cartão(ões).\n");
                cadastrarCartao();
                break;
            case 6:
                System.out.println("Você escolheu a opção: Escolher forma(s) de pagamento.\n");
                System.out.println("(1) Pagamento em dinheiro / (2) Pagamento com cartão / (3) Pagamento via Pix");
                System.out.println("-> ");
                int opcaoPagamento = sc.nextInt();
                if (opcaoPagamento == 1){
                    System.out.println("Efetue o pagamento na saída.");
                } else if (opcaoPagamento == 2){
                    System.out.println("Cartão: (1) Crédito / (2) Débito\n");
                    System.out.println("-> ");
                    int opcaoCartao = sc.nextInt();
                    if (opcaoCartao == 1){
                        System.out.println("Você escolheu a opção: Pagamento via Cartão de Crédito.\n");
                        System.out.println("Aguardando confirmação de pagamento...\n");
                        System.out.println("Pagamento realizado com sucesso!");
                        System.out.println("Agradecemos a preferência. Volte sempre!");
                    } else if (opcaoCartao == 2){
                        System.out.println("Você escolheu a opção: Pagamento via Cartão de Débito.\n");
                        System.out.println("Aguardando confirmação de pagamento...\n");
                        System.out.println("Pagamento realizado com sucesso!");
                        System.out.println("Agradecemos a preferência. Volte sempre!");
                    } else {
                        System.out.println("Opção inválida.");
                    }
                } else if (opcaoPagamento == 3){
                    System.out.println("Você escolheu a opção: Pagamento via Pix.\n");
                    System.out.println("Aguardando confirmação de pagamento...\n");
                    System.out.println("Pagamento realizado com sucesso!");
                    System.out.println("Agradecemos a preferência. Volte sempre!");
                } else {
                    System.out.println("Opção inválida.");
                }
                break;
            case 7:
                System.out.println("Você escolheu a opção: Liberar vaga(s).\n");
                sairDaVaga();
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    private static void listarVeiculos() {
        ArrayList<Carro> carros = cadastroVeiculoDAO.listar();
        if (carros.isEmpty()) {
            System.out.println("Não há veículos cadastrados.");
        } else {
            System.out.println("Lista de veículos:");
            for (Carro carro : carros) {
                System.out.println(carro);
            }
        }
    }

    private static void visualizarVaga() {

        System.out.println("Digite o número da vaga que deseja visualizar:");
        String numeroVaga = sc.next();
        Vaga vaga = encontrarVagaPorNumero(numeroVaga);

        if (vaga != null) {
            System.out.println("Você selecionou a vaga: " + numeroVaga);

            System.out.println("Número da vaga: " + vaga.getNumero());
            if (vaga.Disponivel()) {
                System.out.println("Status: Disponível");
            } else {
                System.out.println("Status: Não disponível");
                System.out.println("Razão da indisponibilidade: " + vaga.getRazaoIndisponibilidade());
            }
            System.out.println("-----------");

        } else {
            System.out.println("numero de vaga não existe.");
        }
    }
    private static void agendarVaga(int idUser, int idCar) {
        System.out.println("Agendamento de Vaga(s).");
        System.out.println("Digite o número da vaga que deseja agendar:");
        String numeroVaga = sc.next();
        Vaga vagaEscolhida = encontrarVagaPorNumero(numeroVaga);

        if (vagaEscolhida != null) {
            if (vagaEscolhida.Disponivel()) {
//                 TODO: Adicionar o novo ParkLog a uma lista e armazenar na tabela correspondente do BD
//                 parkLogs.add(novoParkLog);
                
                LocalDateTime dataEntrada = LocalDateTime.now();
                ParkLog novoParkLog = new ParkLog(0.0f, idUser, idCar);
                novoParkLog.setData_entrada(dataEntrada);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String dataFormatada = dataEntrada.format(formatter);
                System.out.println("Vaga agendada com sucesso. Data: " + dataFormatada);

            } else {
                System.out.println("A vaga escolhida não está disponível para agendamento.");
            }
        } else {
            System.out.println("Vaga não encontrada.");
        }
    }

    private static void sairDaVaga() {
        System.out.println("Vagas já alugadas ou agendadas:");

        for (ParkLog registro : parkLogs) {
            if (registro.getData_entrada() != null && registro.getData_saida() == null) {
                System.out.println("- Vaga: " + registro.getIdPkLog());
            }
        }

        System.out.println("Escolha o número da vaga para sair:");
        int numeroVagaEscolhida = sc.nextInt();

        ParkLog registroEscolhido = null;
        for (ParkLog registro : parkLogs) {
            if (registro.getIdPkLog() == numeroVagaEscolhida) {
                registroEscolhido = registro;
                break;
            }
        }

        if (registroEscolhido != null) {
            LocalDateTime dataSaida = LocalDateTime.now();
            registroEscolhido.setData_saida(dataSaida);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataFormatada = dataSaida.format(formatter);

            System.out.println("Saída da vaga " + numeroVagaEscolhida + " registrada com sucesso. Data de saída: " + dataFormatada);
        } else {
            System.out.println("Vaga escolhida não encontrada nos registros.");
        }
    }

    private static void editarVagaUsuario(int idUser, int idCar) {
        System.out.println("Digite o número da vaga que deseja editar:");
        String numeroVaga = sc.next();
        Vaga vagaEscolhida = encontrarVagaPorNumero(numeroVaga);

        //TODO verifique se a vaga é do usuario antes de editar

        if (vagaEscolhida != null) {
            System.out.println("Você selecionou a vaga: " + numeroVaga);

            System.out.println("Digite: (1) Para trocar de vaga / (2) Para desistir da vaga selecionada:");
            int opcao = sc.nextInt();

            if (opcao == 1) {
                System.out.println("Você escolheu a opção: Trocar de vaga.");
                //TODO trocar vaga
            } else if (opcao == 2) {
                System.out.println("Você escolheu a opção: Desistir da vaga selecionada.");

                vagaEscolhida.desocuparVaga();
                System.out.println("Você desistiu da vaga com sucesso!");
            } else {
                System.out.println("Opção inválida.");
            }

        } else {
            System.out.println("Você não selecionou nenhuma vaga para edição.");
        }
    }

    private static void cadastrarCartao() {
        System.out.println("Escolha o tipo do cartão: ");
        System.out.println("(1) Crédito / (2) Débito");
        int opcaoTipo = sc.nextInt();
        String tipoCartao;
        if (opcaoTipo == 1) {
            tipoCartao = "Crédito";
        } else if (opcaoTipo == 2) {
            tipoCartao = "Débito";
        } else {
            System.out.println("Opção inválida.");
            return;
        }

        sc.nextLine();
        System.out.println("Digite o número do cartão:");
        String numeroCartao = sc.nextLine();

        for (Cartao cartao : cartoesCadastrados) {
            if (cartao.getNumero().equals(numeroCartao)) {
                System.out.println("Este cartão já está cadastrado.");
                return;
            }
        }

        Cartao novoCartao = new Cartao(tipoCartao, numeroCartao);
        cartoesCadastrados.add(novoCartao);
        System.out.println("Cartão cadastrado com sucesso!");
    }
}