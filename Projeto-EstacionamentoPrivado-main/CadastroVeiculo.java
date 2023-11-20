import java.util.ArrayList;
public class CadastroVeiculo {
    private ArrayList<Carro> carros = new ArrayList<>();
    public void cadastrarVeiculo(Carro carro) {
        carros.add(carro);
        System.out.println("Veículo cadastrado com sucesso!");
    }
    public void listarVeiculos() {
        if (carros.isEmpty()) {
            System.out.println("Não há veículos cadastrados.");
        } else {
            System.out.println("Lista de veículos:");
            for (Carro carro : carros) {
                System.out.println(carro);
            }
        }
    }
    public boolean verificarCarroCadastrado(String placa) {
        for (Carro carro : carros) {
            if (carro.getPlaca().equals(placa)) {
                return true;
            }
        }
        return false;
    }
}