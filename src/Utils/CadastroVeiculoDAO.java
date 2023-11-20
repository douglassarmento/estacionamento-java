package src.Utils;

import src.Entities.Carro;
import src.Entities.Modelo;
import src.Entities.User.Usuario;
import src.Main.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static src.Entities.Modelo.modelosCadastrados;

public class CadastroVeiculoDAO {
    private Connection connection;

    public CadastroVeiculoDAO() {
        this.connection = new Conexao().GeraConexao();
    }

    public void adicionaModelo(Modelo modelo) {
        String sql = "INSERT INTO Modelo(idMod, marca, ano, modelo) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, modelo.getIdMod());
            stmt.setString(2, modelo.getMarca());
            stmt.setInt(3, modelo.getAno());
            stmt.setString(4, modelo.getModelo());
            stmt.execute();
            stmt.close();
            System.out.println("Modelo cadastrado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionaCarro(Carro carro) {
        try {
            // Em desenvolvimento

            Modelo ultimoModelo = modelosCadastrados.get(modelosCadastrados.size() - 1);
            int idMod = gerarIdModelo();
            adicionaModelo(new Modelo(idMod, ultimoModelo.getMarca(), ultimoModelo.getAno(), ultimoModelo.getModelo()));

            if (carroJaExiste(carro.getIdCar())) {
                System.out.println("Carro com id " + carro.getIdCar() + " já existe na tabela.");
                return;
            }

            String sql = "INSERT INTO Carro(idCar, idUser, idMod, placa, cor) VALUES(?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, carro.getIdCar());
            stmt.setInt(2, carro.getIdUser());
            stmt.setInt(3, idMod);
            stmt.setString(4, carro.getPlaca());
            stmt.setString(5, carro.getCor());
            stmt.execute();
            stmt.close();
            System.out.println("Carro cadastrado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean carroJaExiste(int idUser) {
        String sql = "SELECT COUNT(*) FROM carro WHERE idCar = ?";
        try { PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idUser);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            stmt.close(); return count > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Carro> listar() {
        ArrayList<Carro> carros = new ArrayList<>();
        String sql = "SELECT idCar, idUser, idMod, placa, cor FROM carro";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Carro carro = new Carro();
                carro.setIdCar(resultSet.getInt("idCar"));
                carro.setIdUser(resultSet.getInt("idUser"));
                carro.setIdMod(resultSet.getInt("idMod"));
                carro.setPlaca(resultSet.getString("placa"));
                carro.setCor(resultSet.getString("cor"));
                carros.add(carro);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carros;
    }

    public int gerarIdCarro() {
        String sql = "SELECT MAX(idCar) + 1 AS proximoId FROM carro";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("proximoId");
            } else {
                return 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int gerarIdModelo() {
        String sql = "SELECT MAX(idMod) + 1 AS proximoId FROM modelo";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("proximoId");
            } else {
                return 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}