public class Vaga {
    private String numero;
    private boolean disponivel;
    private String razaoIndisponibilidade;

    public Vaga(String numero) {
        this.numero = numero;
        this.disponivel = true;
        this.razaoIndisponibilidade = "";
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
}