package src.Utils;

import src.Entities.Cartao;
import src.Entities.User.Usuario;
import src.Entities.Vaga;

import java.util.Scanner;

import static src.Entities.Cartao.cartoesCadastrados;
import static src.Entities.User.Usuario.usuarios;
import static src.Entities.Vaga.vagas;
import static src.Main.Main.sc;

public class FuncoesAdmin {

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

                criarVagas();
                break;
            case 2:
                System.out.println("Você escolheu a opção: Editar src.Entities.Vaga(s).");
                System.out.println();
                editarVagaSistema();
                break;
            case 3:
                System.out.println("Você escolheu a opção: Deletar src.Entities.Vaga(s).");
                System.out.println();
                deletarVaga();
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
        System.out.println("Você escolheu a opção: Editar src.Entities.Vaga(s).");
        System.out.println();
        System.out.println("Digite o número da vaga que deseja editar:");
        String numeroVaga = sc.next();

        Vaga vaga = Vaga.encontrarVagaPorNumero(numeroVaga);

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

        if (usuarios.isEmpty()) {
            System.out.println("Não há usuários cadastrados.");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println("Nome: " + usuario.getNome() + " - ID: " + usuario.getidUser());
            }
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
}
