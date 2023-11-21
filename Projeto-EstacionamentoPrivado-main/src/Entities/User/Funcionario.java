package src.Entities.User;

import src.Entities.User.Usuario;

public class Funcionario extends Usuario {
    private int idFunc;

    public Funcionario(int idFunc) {
        this.idFunc = idFunc;
    }

    public int getIdFunc(){return idFunc;}
    public void setIdFunc(int idFunc){this.idFunc = idFunc;}
    @Override
    public String toString(){
        return super.toString() + "ID Funcionário: " + idFunc + ", Tipo: FUNCIONARIO";
    }
}