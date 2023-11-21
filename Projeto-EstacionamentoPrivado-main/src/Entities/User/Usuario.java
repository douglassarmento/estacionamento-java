package src.Entities.User;

import src.Main.Main;

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
    public static Usuario criarUsuario() {
        Usuario usuario = new Usuario();
        System.out.println("Informe o CPF:");
        usuario.setCpf(Main.sc.next());
        System.out.println("Informe o nome:");
        usuario.setNome(Main.sc.next());
        System.out.println("Informe o e-mail:");
        usuario.setEmail(Main.sc.next());
        System.out.println("Informe a senha:");
        usuario.setSenha(Main.sc.next());
        return usuario;
    }
    @Override
    public String toString() {
        return "ID: " + idUser + ", CPF: " + cpf + ", Nome: " + nome + ", Email: " + email + ", Senha: " + senha;
    }
}