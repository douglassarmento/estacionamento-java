package src.Entities;

import java.util.ArrayList;
import java.util.List;

public class Modelo {
    private int idMod;
    private String marca;
    private int ano;
    private String modelo;
    public int getIdMod() {
        return idMod;
    }
    public void setIdMod(int idMod) {
        this.idMod = idMod;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public Modelo(int idMod, String marca, int ano, String modelo) {
        this.idMod = idMod;
        this.marca = marca;
        this.ano = ano;
        this.modelo = modelo;
    }
    public static List<Modelo> modelosCadastrados = new ArrayList<>();
    public static int buscarIdPorNome(String nomeModelo) {
        for (Modelo modelo : modelosCadastrados) {
            if (modelo.getModelo().equalsIgnoreCase(nomeModelo)) {
                return modelo.getIdMod();
            }
        }
        return -1;
    }
    @Override
    public String toString() {
        return "ID: " + idMod + ", Marca: " + marca + ", Ano: " + ano + ", Modelo: " + modelo;
    }
}