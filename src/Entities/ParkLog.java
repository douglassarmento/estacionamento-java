package src.Entities;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkLog {
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    private int idPkLog;
    private LocalDateTime data_emitida;
    private LocalDateTime data_entrada;
    private LocalDateTime data_saida;
    private float valor;
    private int idUser;
    private int idCar;

    public ParkLog(float valor, int idUser, int idCar) {
        this.idPkLog = idGenerator.getAndIncrement();
        this.valor = valor;
        this.idUser = idUser;
        this.data_emitida = LocalDateTime.now();
        this.idCar = idCar;
    }

    public LocalDateTime getData_emitida() {
        return data_emitida;
    }

    public LocalDateTime getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(LocalDateTime data_entrada) {
        this.data_entrada = data_entrada;
    }

    public LocalDateTime getData_saida() {
        return data_saida;
    }

    public float getValor() {
        return valor;
    }

    public void setData_saida(LocalDateTime data_saida) {
        this.data_saida = data_saida;
    }

    public int getIdPkLog() {
        return idPkLog;
    }

    public int getIdP() {
        return idUser;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdP(int idP) {
        this.idUser = idP;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }
}