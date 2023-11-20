package src.Entities;

import src.Entities.User.Usuario;
import src.Main.Main;

import java.util.ArrayList;
import java.util.List;

import static src.Entities.Modelo.modelosCadastrados;
import static src.Main.Main.sc;

public class Carro {
    private int idCar, idUser, idMod;
    private String placa, cor;

    public int getIdCar() {
        return idCar;
    }
    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }
    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setIdMod(int idMod) {
        this.idMod = idMod;
    }

    public static int obterInteiroDoUsuario() {
        while (!sc.hasNextInt()) {
            System.out.println("Entrada inválida. Digite um número inteiro.");
            sc.next();
        }
        return sc.nextInt();
    }

    public static int obterIdMod() {
        System.out.println("Informe o Modelo:");
        String nomeModelo = sc.next();
        int idMod = Modelo.buscarIdPorNome(nomeModelo);

        if (idMod == -1) {
            System.out.println("Informe a Marca:");
            String marca = sc.next();

            System.out.println("Informe o Ano:");
            int ano = obterInteiroDoUsuario();

            Modelo novoModelo = new Modelo(modelosCadastrados.size() + 1, marca, ano, nomeModelo);
            modelosCadastrados.add(novoModelo);
            idMod = novoModelo.getIdMod();
        }
        return idMod;
    }

    public static Carro cadastrarCarro() {
        Carro carro = new Carro();

        System.out.println("Informe a Placa:");
        carro.setPlaca(sc.next());

        System.out.println("Informe a Cor:");
        carro.setCor(sc.next());

        carro.setIdMod(obterIdMod());

        return carro;
    }

}