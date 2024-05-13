package controller;

import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement; // Dentro da conexão permite executar comandos SQL
import javax.swing.JOptionPane;
import model.Palavras;
import java.sql.ResultSet;

public class PalavrasDao extends ConectarDao {

    String sql;
    PreparedStatement ps;

    public PalavrasDao() {
        super();
    }

    public void incluir(Palavras obj) {
        sql = "INSERT INTO PALAVRAS (palavra,dica) VALUES (?, ?)";
        try {
            PreparedStatement ps = mycon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, obj.getPalavra());
            ps.setString(2, obj.getDica());
            ps.execute();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()) {
                int idPalavra = rs.getInt(1);
                obj.setIdPalavra(idPalavra);
            }

            ps.close();
        } catch (SQLException err) {
        }
    }
}
