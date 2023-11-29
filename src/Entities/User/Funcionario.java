package src.Entities.User;

import src.Utils.CadastroUsuarioDAO;

public class Funcionario extends Usuario {
    private int idFunc;

    public Funcionario() {
        CadastroUsuarioDAO cadastroUsuarioDAO = new CadastroUsuarioDAO();
        this.idFunc = cadastroUsuarioDAO.gerarId("funcionario", "idFunc");
    }

    public int getIdFunc(){return idFunc;}
    public void setIdFunc(int idFunc){this.idFunc = idFunc;}
    @Override
    public String toString() {
        return super.toString() + ", ID Funcionário: " + idFunc + ", Tipo: FUNCIONARIO";
    }
}
