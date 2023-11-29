package src.Utils;

import src.Entities.Carro;
import src.Entities.Modelo;

import java.util.ArrayList;
import java.util.List;

import static src.Entities.Carro.obterInteiroDoUsuario;
import static src.Entities.Modelo.modelosCadastrados;
import static src.Main.Main.sc;

public class CadastroVeiculo {

    private static List<Carro> carros = new ArrayList<>();
    static CadastroVeiculoDAO cadastroVeiculoDAO = new CadastroVeiculoDAO();

    public static int gerarIdCarro() {
        return cadastroVeiculoDAO.gerarIdCarro();
    }

    public static void cadastrarVeiculo(Carro carro, int idUser) {

        carro.setIdUser(idUser);
        carro.setIdCar(gerarIdCarro());

        System.out.println("Informe a Placa:");
        carro.setPlaca(sc.next());

        System.out.println("Informe a Cor:");
        carro.setCor(sc.next());

        carro.setIdMod(Carro.obterIdMod());

        carros.add(carro);
        cadastroVeiculoDAO.adicionaCarro(carro);
    }

    public static void listarVeiculos() {
        if (carros.isEmpty()) {
            System.out.println("Não há veículos cadastrados.");
        } else {
            System.out.println("Lista de veículos:");
            for (Carro carro : carros) {
                System.out.println(carro);
            }
        }
    }
    public static boolean verificarPlaca(String placa) {
        for (Carro carro : carros) {
            if (carro.getPlaca().equals(placa)) {
                return true;
            }
        }
        return false;
    }

}