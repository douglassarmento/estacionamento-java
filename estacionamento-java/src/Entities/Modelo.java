package src.Entities;

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
    @Override
    public String toString() {
        return "ID: " + idMod + ", Marca: " + marca + ", Ano: " + ano + ", src.Entities.Modelo: " + modelo;
    }
}