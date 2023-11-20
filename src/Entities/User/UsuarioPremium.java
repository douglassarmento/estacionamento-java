package src.Entities.User;

import src.Utils.CadastroUsuarioDAO;

public class UsuarioPremium extends Usuario {
    private int idPre;

    public UsuarioPremium() {
        CadastroUsuarioDAO cadastroUsuarioDAO = new CadastroUsuarioDAO();
        this.idPre = cadastroUsuarioDAO.gerarId("usuariopremium", "idPre");
    }

    public int getIdPre(){return idPre;}
    public void setIdPre(int idPre){this.idPre = idPre;}
    @Override
    public String toString() {
        return super.toString() + ", ID Premium: " + idPre + ", Tipo: PREMIUM";
    }
}