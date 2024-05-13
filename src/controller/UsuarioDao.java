package controller;

import java.sql.SQLException;
import java.sql.PreparedStatement; // Dentro da conexão permite executar comandos SQL
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Connection;
import model.Jogadores;

public class UsuarioDao extends ConectarDao {

    String sql;
    PreparedStatement ps; // objeto para executar o sql

    public UsuarioDao() {
        super();
    }

    public Jogadores validarLogin(String login, String senha)throws SQLException {
        sql = "SELECT * FROM JOGADORES WHERE idUsuario=? AND senha=?";
        try {
            PreparedStatement ps = (PreparedStatement) mycon.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, senha);
            ResultSet resul = ps.executeQuery();
            Jogadores jg = null;
            
            if (resul.next()) {
                jg = new Jogadores();
                jg.setIdUsuario(resul.getString("idUsuario"));
                jg.setNome(resul.getString("nome"));
                jg.setEmail(resul.getString("email"));
                jg.setSenha(resul.getString("senha"));
            }

            return jg;
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
            return null;
        }
    }

    public void incluir(Jogadores obj) {
        sql = "INSERT INTO JOGADORES VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = mycon.prepareStatement(sql);
            ps.setString(1, obj.getIdUsuario());
            ps.setString(2, obj.getNome());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getSenha());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Registro Incluído com Sucesso!");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir usuário!" + err.getMessage());
        }
    }

}
