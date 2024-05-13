package controller;

import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement; // Dentro da conexão permite executar comandos SQL
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.Jogadores;
import model.Palavras;
import model.Partidas;

public class PartidasDao extends ConectarDao {

    String sql;
    PreparedStatement ps;

    public PartidasDao() {
        super();
    }

    public void incluir(Partidas obj, Jogadores obj1, Palavras obj2) {
        sql = "INSERT INTO PARTIDAS (dataHora,palavraUtilizada,idUsuario,idPalavra) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = mycon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, obj.getDataHora());
            ps.setString(2, obj.getPalavraUtilizada());
            ps.setString(3, obj1.getIdUsuario());
            ps.setInt(4, obj2.getIdPalavra());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int idPartida = rs.getInt(1);
                obj.setIdPartida(idPartida);
            }

            ps.close();
        } catch (SQLException err) {
        }
    }

    public int contarPartidas(String login) throws SQLException {
        sql = "SELECT COUNT(*) AS totalPartidas FROM PARTIDAS WHERE idUsuario=?";
        try {
            PreparedStatement ps = mycon.prepareStatement(sql);
            ps.setString(1, login);
            ResultSet resul = ps.executeQuery();
            int totalPartidas = 0;

            if (resul.next()) {
                totalPartidas = resul.getInt("totalPartidas");
            }

            return totalPartidas;
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
            return 0;
        }
    }
}
