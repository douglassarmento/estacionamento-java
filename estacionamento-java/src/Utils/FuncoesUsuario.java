package src.Utils;

import src.Entities.Cartao;
import src.Entities.Vaga;

import java.time.LocalDateTime;

import static src.Entities.Cartao.cartoesCadastrados;
import static src.Entities.Vaga.encontrarVagaPorNumero;
import static src.Entities.Vaga.vagas;
import static src.Main.Main.sc;

public class FuncoesUsuario {


    public static void visualizarVaga() {

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
            return;
        }
    }
    public static void agendarVaga(int idP, int idC) {
        System.out.println("Agendamento de src.Entities.Vaga(s).");
        System.out.println("Digite o número da vaga que deseja agendar:");
        String numeroVaga = sc.next();
        Vaga vagaEscolhida = encontrarVagaPorNumero(numeroVaga);

        if (vagaEscolhida != null) {
            if (vagaEscolhida.Disponivel()) {
                System.out.println("Digite a data para o agendamento (DD/MM/AAAA):");
                String dataAgendamento = sc.next();
                System.out.println("src.Entities.Vaga agendada com sucesso para " + dataAgendamento);
                LocalDateTime now = LocalDateTime.now();

                //TODO criar parklog, com idP e idC crie um novo parklog com os mesmos e as datas com o construtor

            } else {
                System.out.println("A vaga escolhida não está disponível para agendamento.");
            }
        } else {
            System.out.println("src.Entities.Vaga não encontrada.");
        }
    }

    public static void editarVagaUsuario(int idP, int idC) {
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
            return;
        }
    }

    public static void cadastrarCartao() {
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


    public static void opcoesUsuario(int idP, int idC){
        System.out.println("---------- Bem-vindo(a) ao Sistema de Vagas do Estacionamento ----------");
        System.out.println();
        System.out.println("Digite uma das opções abaixo para prosseguir.");
        System.out.println();
        System.out.println("(1) Visualizar src.Entities.Vaga(s) / (2) Editar src.Entities.Vaga(s) / (3) Cadastrar Cartão(ões) / (4) Escolher forma(s) de pagamento");
        System.out.println("-> ");
        int opcaoUsuario = sc.nextInt();
        switch (opcaoUsuario) {
            case 1:
                System.out.println("Você escolheu a opção: Visualizar src.Entities.Vaga(s).");
                System.out.println();
                visualizarVaga();
                System.out.println("Escolha uma das opções para prosseguir.");
                System.out.println("(1) Alugar vaga(s) / (2) Cancelar");
                System.out.println("-> ");


                int escolhaUsuario = sc.nextInt();
                if (escolhaUsuario == 1){
                    System.out.println("Você escolheu a opção: Agendar vaga(s).");
                    System.out.println();
                    agendarVaga(int idP, int idC);
                } else if (escolhaUsuario == 2){
                    System.out.println("Você escolheu a opção: Cancelar.");
                    //Criar uma estrutura de loop aqui para ele voltar quando quiser.
                } else {
                    System.out.println("Opção inválida.");
                }
                break;
            case 2:
                System.out.println("Você escolheu a opção: Editar src.Entities.Vaga(s).");
                System.out.println();
                editarVagaUsuario(int idP, int idC);
                break;
            case 3:
                System.out.println("Você escolheu a opção: Cadastrar Cartão(ões).");
                System.out.println();
                cadastrarCartao();
                break;
            case 4:
                System.out.println("Você escolheu a opção: Escolher forma(s) de pagamento.");
                System.out.println();
                System.out.println("(1) Pagamento em dinheiro / (2) Pagamento com cartão / (3) Pagamento via Pix");
                System.out.println("-> ");
                int opcaoPagamento = sc.nextInt();
                if (opcaoPagamento == 1){
                    System.out.println("Efetue o pagamento na saída.");
                } else if (opcaoPagamento == 2){
                    System.out.println("Cartão: (1) Crédito / (2) Débito");
                    System.out.println();
                    System.out.println("-> ");
                    int opcaoCartao = sc.nextInt();
                    if (opcaoCartao == 1){
                        System.out.println("Você escolheu a opção: Pagamento via Cartão de Crédito.");
                        System.out.println();
                        System.out.println("Aguardando confirmação de pagamento...");
                        System.out.println();
                        System.out.println("Pagamento realizado com sucesso!");
                        System.out.println("Agradecemos a preferência. Volte sempre!");
                    } else if (opcaoCartao == 2){
                        System.out.println("Você escolheu a opção: Pagamento via Cartão de Débito.");
                        System.out.println();
                        System.out.println("Aguardando confirmação de pagamento...");
                        System.out.println();
                        System.out.println("Pagamento realizado com sucesso!");
                        System.out.println("Agradecemos a preferência. Volte sempre!");
                    } else {
                        System.out.println("Opção inválida.");
                    }
                } else if (opcaoPagamento == 3){
                    System.out.println("Você escolheu a opção: Pagamento via Pix.");
                    System.out.println();
                    System.out.println("Aguardando confirmação de pagamento...");
                    System.out.println();
                    System.out.println("Pagamento realizado com sucesso!");
                    System.out.println("Agradecemos a preferência. Volte sempre!");
                } else {
                    System.out.println("Opção inválida.");
                }
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }
}
