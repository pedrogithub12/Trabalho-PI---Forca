package controller;

import java.sql.SQLException;
import java.sql.PreparedStatement; // Dentro da conexão permite executar comandos SQL
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.Jogadores;
import model.Partidas;
import model.Pontuacoes;
import model.Tentativas;

public class TentativasDao extends ConectarDao{
    String sql;
    PreparedStatement ps;
    
    public TentativasDao() {
        super();
    }
    
    public void incluir(Tentativas obj, Partidas obj1) {
        sql = "INSERT INTO TENTATIVAS(letraUtilizada,acertoErro,idPartida) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = mycon.prepareStatement(sql);
            ps.setString(1, String.valueOf(obj.getLetraUtilizada()));
            ps.setBoolean(2, obj.isAcertoErro());
            ps.setInt(3, obj1.getIdPartida());
            ps.execute();
            ps.close();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir usuário!" + err.getMessage());
        }
    }
    
}
