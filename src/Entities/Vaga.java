package src.Entities;

import java.util.ArrayList;

public class Vaga {
    public int idP;
    private String numero;
    private boolean disponivel;
    private String razaoIndisponibilidade;

    public static ArrayList<Vaga> vagas = new ArrayList<>();

    public Vaga(String numero) {
        this.numero = numero;
        this.disponivel = true;
        this.razaoIndisponibilidade = "";
        vagas.add(this);
    }

    public String getNumero() {
        return numero;
    }

    public boolean Disponivel() {
        return disponivel;
    }

    public String getRazaoIndisponibilidade() {
        return razaoIndisponibilidade;
    }

    public void ocuparVaga(String razao) {
        this.disponivel = false;
        this.razaoIndisponibilidade = razao;
    }

    public void desocuparVaga() {
        this.disponivel = true;
        this.razaoIndisponibilidade = "";
    }


    public static Vaga encontrarVagaPorNumero(String numeroVaga) {
        for (Vaga vaga : vagas) {
            if (vaga.getNumero().equalsIgnoreCase(numeroVaga)) {
                return vaga;
            }
        }
        return null;
    }

}