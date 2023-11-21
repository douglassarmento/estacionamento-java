package src.Entities;

import src.Entities.User.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;

public class parkLog {
    int idPkLog;
    //LocalDateTime data_emitida, data_entrada, data_saida;
    float valor;
    private ArrayList<Vaga> vagas = new ArrayList<>();
    private List<Cartao> cartoesCadastrados = new ArrayList<>();
    private List<Usuario> usuariosCadastrados = new ArrayList<>();
    private static final ArrayList<Integer> idPkLogList = new ArrayList<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1);
    private int idP;
    private int idCar;
    public int getIdPkLog() {
        return idPkLog;
    }
    public int getIdP() {
        return idP;
    }
    public int getIdCar() {
        return idCar;
    }
    public void setIdP(int idP) {
        this.idP = idP;
    }
    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }
    public void parkLogSistema (Scanner sc) {
        System.out.println("---------- Bem-vindo(a) ao Sistema Administrativo do Estacionamento ----------");
        System.out.println();
        System.out.println("Digite uma das opções abaixo para prosseguir.");
        System.out.println();
        System.out.println("(1) Criar src.Entities.Vaga(s) / (2) Editar src.Entities.Vaga(s) / (3) Deletar src.Entities.Vaga(s) / (4) Monitorar src.Entities.Vaga(s) / (5) Listar Usuário(s)");
        System.out.println("-> ");
        int opcaoAdm = sc.nextInt();
        switch (opcaoAdm) {
            case 1:
                System.out.println("Você escolheu a opção: Criar src.Entities.Vaga(s).");
                System.out.println();
                System.out.println("Informe o número de vagas a serem criadas: ");
                int numeroVagas = sc.nextInt();
                criarVagas(sc);
                break;
            case 2:
                System.out.println("Você escolheu a opção: Editar src.Entities.Vaga(s).");
                System.out.println();
                editarVagaSistema(sc);
                break;
            case 3:
                System.out.println("Você escolheu a opção: Deletar src.Entities.Vaga(s).");
                System.out.println();
                deletarVaga(sc);
                break;
            case 4:
                System.out.println("Você escolheu a opção: Monitorar src.Entities.Vaga(s).");
                System.out.println();
                monitorarVaga();
                break;
            case 5:
                System.out.println("Você escolheu a opção: Listar Usuário(s).");
                System.out.println();
                listarUsuarios();
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }
    public void criarVagas(Scanner sc) {

        System.out.println("Digite a quantidade de vagas a serem criadas:");
        int quantidadeVagas = sc.nextInt();

        char letra = 'A';
        int numero = 1;

        for (int i = 0; i < quantidadeVagas; i++) {
            String numeroVaga = String.valueOf(letra) + numero;
            vagas.add(new Vaga(numeroVaga));
            if (numero < 10) {
                numero++;
            } else {
                letra++;
                numero = 1;
            }
        }
        System.out.println("Vagas criadas com sucesso!");
    }
    public void editarVagaSistema(Scanner sc) {
        System.out.println("Você escolheu a opção: Editar src.Entities.Vaga(s).");
        System.out.println();
        System.out.println("Digite o número da vaga que deseja editar:");
        String numeroVaga = sc.next();

        Vaga vaga = encontrarVagaPorNumero(numeroVaga);

        if (vaga != null) {
            System.out.println("Digite: (1) Para tornar a vaga disponível / (2) Para torná-la indisponível:");
            int opcao = sc.nextInt();

            if (opcao == 1) {
                vaga.desocuparVaga();
                System.out.println("src.Entities.Vaga tornada disponível com sucesso!");
            } else if (opcao == 2) {
                System.out.println("Digite a razão da indisponibilidade da vaga:");
                sc.nextLine();
                String razao = sc.nextLine();
                vaga.ocuparVaga(razao);
                System.out.println("src.Entities.Vaga tornada indisponível com sucesso!");
            } else {
                System.out.println("Opção inválida.");
            }
        } else {
            System.out.println("src.Entities.Vaga não encontrada.");
        }
    }
    private Vaga encontrarVagaPorNumero(String numeroVaga) {
        for (Vaga vaga : vagas) {
            if (vaga.getNumero().equalsIgnoreCase(numeroVaga)) {
                return vaga;
            }
        }
        return null;
    }
    public void deletarVaga(Scanner sc) {
        System.out.println("Digite o número da vaga que deseja excluir:");
        String numeroVaga = sc.next();

        boolean vagaEncontrada = false;
        for (Vaga vaga : vagas) {
            if (vaga.getNumero().equals(numeroVaga)) {
                vagas.remove(vaga);
                vagaEncontrada = true;
                break;
            }
        }
        if (!vagaEncontrada) {
            System.out.println("src.Entities.Vaga não encontrada!");
        } else {
            System.out.println("src.Entities.Vaga excluída com sucesso!");
        }
    }
    public void monitorarVaga() {
        System.out.println("Monitorando status das vagas...");
        for (Vaga vaga : vagas) {
            System.out.println("Número da vaga: " + vaga.getNumero());
            if (vaga.Disponivel()) {
                System.out.println("Status: Disponível");
            } else {
                System.out.println("Status: Não disponível");
                System.out.println("Razão da indisponibilidade: " + vaga.getRazaoIndisponibilidade());
            }
            System.out.println("-----------");
        }
    }
    public void listarUsuarios() {
        System.out.println("Lista de usuários cadastrados:");

        if (usuariosCadastrados.isEmpty()) {
            System.out.println("Não há usuários cadastrados.");
        } else {
            for (Usuario usuario : usuariosCadastrados) {
                System.out.println("Nome: " + usuario.getNome() + " - ID: " + usuario.getidUser());
            }
        }
    }
    public void visualizarVaga() {
        System.out.println("Vagas disponíveis:");
        for (Vaga vaga : vagas) {
            System.out.println("Número da vaga: " + vaga.getNumero());
            if (vaga.Disponivel()) {
                System.out.println("Status: Disponível");
            } else {
                System.out.println("Status: Não disponível");
                System.out.println("Razão da indisponibilidade: " + vaga.getRazaoIndisponibilidade());
            }
            System.out.println("-----------");
        }
    }
    public void agendarVaga(Scanner sc) {
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
                System.out.println("Você solicitou essa vaga: " + now.getDayOfMonth() + "/" + now.getMonthValue() + "/" + now.getYear() + " " + now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
            } else {
                System.out.println("A vaga escolhida não está disponível para agendamento.");
            }
        } else {
            System.out.println("src.Entities.Vaga não encontrada.");
        }
    }
    public void alugarVaga(Scanner sc) {
        System.out.println("Aluguel de src.Entities.Vaga(s).");
        System.out.println("Digite o número da vaga que deseja alugar:");
        String numeroVaga = sc.next();
        Vaga vagaEscolhida = encontrarVagaPorNumero(numeroVaga);

        if (vagaEscolhida != null) {
            if (vagaEscolhida.Disponivel()) {
                System.out.println("src.Entities.Vaga alugada com sucesso.");
                LocalDateTime now = LocalDateTime.now();
                System.out.println("Você solicitou essa vaga: " + now.getDayOfMonth() + "/" + now.getMonthValue() + "/" + now.getYear() + " " + now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
            } else {
                System.out.println("A vaga escolhida não está disponível para aluguel.");
            }
        } else {
            System.out.println("src.Entities.Vaga não encontrada.");
        }
    }
    public void editarVagaUsuario(Scanner sc) {
        System.out.println("Edição de src.Entities.Vaga(s).");

        if (idCar == 0) {
            System.out.println("Você não selecionou nenhuma vaga para edição.");
            return;
        }

        System.out.println("Você selecionou a vaga: " + idCar);

        System.out.println("Digite: (1) Para trocar de vaga / (2) Para desistir da vaga selecionada:");
        int opcao = sc.nextInt();

        if (opcao == 1) {
            System.out.println("Você escolheu a opção: Trocar de vaga.");

        } else if (opcao == 2) {
            System.out.println("Você escolheu a opção: Desistir da vaga selecionada.");
            Vaga vagaEscolhida = encontrarVagaPorNumero(Integer.toString(idCar));
            if (vagaEscolhida != null) {
                vagaEscolhida.desocuparVaga();
                idCar = 0;
                System.out.println("Você desistiu da vaga com sucesso!");
            } else {
                System.out.println("src.Entities.Vaga não encontrada.");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }
    public void cadastrarCartao(Scanner sc) {
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

    public void listarCartoes() {
        System.out.println("Cartões cadastrados:");
        if (cartoesCadastrados.isEmpty()) {
            System.out.println("Nenhum cartão cadastrado.");
        } else {
            for (Cartao cartao : cartoesCadastrados) {
                System.out.println("Tipo: " + cartao.getTipo() + ", Número: " + cartao.getNumero());
            }
        }
    }
    public void parkLogUsuario (int idP, int idCar, Scanner sc){
        this.idPkLog = idGenerator.getAndIncrement();
        this.idP = idP;
        this.idCar = idCar;
        idPkLogList.add(this.idPkLog);

        System.out.println("---------- Bem-vindo(a) ao Sistema de Vagas do Estacionamento ----------");
        System.out.println();
        System.out.println("Digite uma das opções abaixo para prosseguir.");
        System.out.println();
        System.out.println("(1) Visualizar src.Entities.Vaga(s) / (2) Editar src.Entities.Vaga(s) / (3) Cadastrar Cartão(ões) / (4) Listar Cartão(ões) / (5) Escolher forma(s) de pagamento");
        System.out.println("-> ");
        int opcaoUsuario = sc.nextInt();
        switch (opcaoUsuario) {
            case 1:
                System.out.println("Você escolheu a opção: Visualizar src.Entities.Vaga(s).");
                System.out.println();
                visualizarVaga();
                System.out.println("Escolha uma das opções para prosseguir.");
                System.out.println("(1) Alugar vaga(s) / (2) Agendar vaga(s) / (3) Cancelar");
                System.out.println("-> ");
                int escolhaUsuario = sc.nextInt();
                if (escolhaUsuario == 1){
                    System.out.println("Você escolheu a opção: Alugar src.Entities.Vaga(s).");
                    System.out.println();
                    alugarVaga(sc);
                } else if (escolhaUsuario == 2){
                    System.out.println("Você escolheu a opção: Agendar vaga(s).");
                    System.out.println();
                    agendarVaga(sc);
                } else if (escolhaUsuario == 3){
                    System.out.println("Você escolheu a opção: Cancelar.");
                    //Criar uma estrutura de loop aqui para ele voltar quando quiser.
                } else {
                    System.out.println("Opção inválida.");
                }
                break;
            case 2:
                System.out.println("Você escolheu a opção: Editar src.Entities.Vaga(s).");
                System.out.println();
                editarVagaUsuario(sc);
                break;
            case 3:
                System.out.println("Você escolheu a opção: Cadastrar Cartão(ões).");
                System.out.println();
                cadastrarCartao(sc);
                break;
            case 4:
                System.out.println("Você escolheu a opção: Listar Cartão(ões).");
                System.out.println();
                listarCartoes();
                break;
            case 5:
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