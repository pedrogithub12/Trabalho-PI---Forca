package controller;

import java.sql.SQLException;
import java.sql.PreparedStatement; // Dentro da conexão permite executar comandos SQL
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.Jogadores;
import model.Partidas;
import model.Pontuacoes;

public class PontuacoesDao extends ConectarDao {

    String sql;
    PreparedStatement ps;
    public static Pontuacoes pt  = new Pontuacoes();

    public PontuacoesDao() {
        super();
    }

    public void incluir(Pontuacoes obj, Jogadores obj1) {
        sql = "INSERT INTO PONTUACOES(pontuacao,idUsuario) VALUES (?, ?)";
        try {
            PreparedStatement ps = mycon.prepareStatement(sql);
            ps.setInt(1, obj.getPontuacao());
            ps.setString(2, obj1.getIdUsuario());
            ps.execute();
            ps.close();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir usuário!" + err.getMessage());
        }
    }

    public Pontuacoes obterPontos(String login) throws SQLException {
        sql = "SELECT SUM(pontuacao) AS totalPontuacao FROM PONTUACOES WHERE idUsuario=?";
        try {
            PreparedStatement ps = (PreparedStatement) mycon.prepareStatement(sql);
            ps.setString(1, login);
            ResultSet resul = ps.executeQuery();

            if (resul.next()) {
                pt.setPontuacao(resul.getInt("totalPontuacao"));
            }

            return pt;
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
            return null;
        }
    }
}
