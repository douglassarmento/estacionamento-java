package src.Entities;

import static src.Main.Main.sc;

public class Carro {
    private int idCar;
    private String placa;
    private String cor;

    public int getIdCar() {
        return idCar;
    }
    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }
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

    public Carro() {
        Carro carro = new Carro();
        System.out.println("Informe a Placa:");
        carro.setPlaca(sc.next());
        System.out.println("Informe a Cor:");
        carro.setCor(sc.next());
    }


}