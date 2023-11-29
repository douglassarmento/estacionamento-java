package src.Entities.User;

import src.Utils.CadastroUsuarioDAO;

public class Admin extends Usuario {
    private int idAdm;

    public Admin() {
        CadastroUsuarioDAO cadastroUsuarioDAO = new CadastroUsuarioDAO();
        this.idAdm = cadastroUsuarioDAO.gerarId("admin", "idAdm");
    }

    public int getidAdm(){return idAdm;}
    public void setIdAdm(int idAdm){
        this.idAdm = idAdm;
    }
    @Override
    public String toString(){
        return super.toString() + ", ID Administrador: " + idAdm + ", Tipo: ADMIN";
    }
}