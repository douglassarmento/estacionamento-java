package src.Entities.User;

public class Admin extends Usuario {
    private int idAdm;

    public Admin(int idAdm) {
        this.idAdm = idAdm;
    }

    public int getidAdm(){return idAdm;}
    public void setIdAdm(int idAdm){
        this.idAdm = idAdm;
    }
    @Override
    public String toString(){
        return super.toString() + "ID Administrador: " + idAdm + ", Tipo: ADMIN";
    }
}