package src.Entities;

import java.util.ArrayList;
import java.util.List;

public class Cartao {
    private String tipo;
    private String numero;

    public static List<Cartao> cartoesCadastrados = new ArrayList<>();

    public Cartao(String tipo, String numero) {
        this.tipo = tipo;
        this.numero = numero;
        cartoesCadastrados.add(this);
    }

    public String getTipo() {
        return tipo;
    }

    public String getNumero() {
        return numero;
    }
}