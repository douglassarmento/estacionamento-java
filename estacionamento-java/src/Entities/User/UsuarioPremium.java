package src.Entities.User;

public class UsuarioPremium extends Usuario {
    private int idPPre;

    public UsuarioPremium(int idPPre) {
        this.idPPre = idPPre;
    }

    public int getIdPPre(){return idPPre;}
    public void setIdPPre(int idPPre){this.idPPre = idPPre;}
    @Override
    public String toString() {
        return super.toString() + "ID Premium: " + idPPre + ", Tipo: PREMIUM";
    }
}