package src.Entities;

import src.Entities.User.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;

public class parkLog {
    int idPkLog;
    //LocalDateTime data_emitida, data_entrada, data_saida;
    float valor;

    private static final ArrayList<Integer> idPkLogList = new ArrayList<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1);
    private int idP;
    private int idCar;

    public parkLog(int idPkLog, float valor, int idP, int idCar) {
        this.idPkLog = idPkLog;
        this.valor = valor;
        this.idP = idP;
        this.idCar = idCar;
    }

    public int getIdPkLog() {
        return idPkLog;
    }
    public int getIdP() {
        return idP;
    }
    public int getIdCar() {
        return idCar;
    }
    public void setIdP(int idP) {
        this.idP = idP;
    }
    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }


}