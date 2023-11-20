package src.Utils;

import src.Entities.Cartao;
import src.Entities.Vaga;
import src.Entities.ParkLog;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

import static src.Entities.Cartao.cartoesCadastrados;
import static src.Entities.Vaga.vagas;
import static src.Main.Main.sc;

public class FuncoesAdmin {

    public void painelAdmin() {
        System.out.println("\n---------- Bem-vindo(a) ao Sistema Administrativo do Estacionamento ----------\n");
        System.out.println("\nDigite uma das opções abaixo para prosseguir.\n");
        System.out.println("(1) Criar Vaga(s) | (2) Editar Vaga(s) | (3) Deletar Vaga(s) | (4) Monitorar Vaga(s)");
        System.out.println("(5) Listar Usuário(s) | (6) Listar Veículo(s) | (7) Listar Vaga(s) | (8) Listar Cartão(ões)\n-> ");
        int opcaoAdm = sc.nextInt();
        switch (opcaoAdm) {
            case 1:
                System.out.println("Você escolheu a opção: Criar Vaga(s).\n");
                System.out.println("Informe o número de vagas a serem criadas: ");
                criarVagas();
                break;
            case 2:
                System.out.println("Você escolheu a opção: Editar Vaga(s).\n");
                editarVagaSistema();
                break;
            case 3:
                System.out.println("Você escolheu a opção: Deletar Vaga(s).\n");
                deletarVaga();
                break;
            case 4:
                System.out.println("Você escolheu a opção: Monitorar Vaga(s).\n");
                monitorarVaga();
                break;
            case 5:
                System.out.println("Você escolheu a opção: Listar Usuário(s).\n");
                CadastroUsuario.listarPessoas();
                break;
            case 6:
                System.out.println("Você escolheu a opção: Listar Veículo(s).\n");
                CadastroVeiculo.listarVeiculos();
                break;
            case 7:
                System.out.println("Você escolheu a opção: Listar Vaga(s).\n");
                listarVagas();
                break;
            case 8:
                System.out.println("Você escolheu a opção: Listar Cartão(ões).\n");
                listarCartoes();
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    public void criarVagas() {

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
    public void editarVagaSistema() {
        System.out.println("Você escolheu a opção: Editar Vaga(s).\n");
        System.out.println("Digite o número da vaga que deseja editar:");
        String numeroVaga = sc.next();

        Vaga vaga = Vaga.encontrarVagaPorNumero(numeroVaga);

        if (vaga != null) {
            System.out.println("Digite: (1) Para tornar a vaga disponível / (2) Para torná-la indisponível:");
            int opcao = sc.nextInt();

            if (opcao == 1) {
                vaga.desocuparVaga();
                System.out.println("Vaga ficou disponível com sucesso!");
            } else if (opcao == 2) {
                System.out.println("Digite a razão da indisponibilidade da vaga:");
                sc.nextLine();
                String razao = sc.nextLine();
                vaga.ocuparVaga(razao);
                System.out.println("Vaga ficou indisponível com sucesso!");
            } else {
                System.out.println("Opção inválida.");
            }
        } else {
            System.out.println("Vaga não encontrada.");
        }
    }

    public void deletarVaga() {
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
            System.out.println("Vaga não encontrada!");
        } else {
            System.out.println("Vaga excluída com sucesso!");
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

    public void listarVagas() {
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
    
    public List<String> infoGeralParkLog(List<ParkLog> parkLogs) {
        List<String> infoGeralParklog = new ArrayList<>();

        for (ParkLog log : parkLogs) {
            String detalhe = "ID: " + log.getIdPkLog() +
                    ", User ID: " + log.getIdP() +
                    ", Car ID: " + log.getIdCar() +
                    ", Data emitida: " + log.getData_emitida() +
                    ", Data entrada: " + log.getData_entrada() +
                    ", Data saída: " + log.getData_saida() +
                    ", Valor: " + log.getValor();
            infoGeralParklog.add(detalhe);
        }
        return infoGeralParklog;
    }
}
