package src.Entities.User;

public class Usuario {
    private int idUser;
    private String cpf, nome, email, senha;
    public int getidUser() {
        return idUser;
    }
    public void setidUser(int idUser) {
        this.idUser = idUser;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    @Override
    public String toString() {
        return "ID: " + idUser + ", CPF: " + cpf + ", Nome: " + nome + ", Email: " + email + ", Senha: " + senha;
    }
}