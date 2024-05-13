package controller;

import java.sql.DriverManager; // Driver para abrir Conexão
import java.sql.SQLException; // Tratamento de Erros SQL
import java.sql.Connection; // Armazena a Conexão Aberta
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ConectarDao {

    public Connection mycon = null;
    public String sql = null;
    PreparedStatement ps;

    public void criarBancoDeDados() {

    }

    public ConectarDao() {

        String sql = "CREATE DATABASE IF NOT EXISTS PRJFORCA";

        try (Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", ""); Statement statement = conexao.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Erro ao criar o banco de dados: " + e.getMessage());
        }

        try {
            mycon = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PrjForca", "root", "");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro de Conexão com o MySQL ...\n" + err.getMessage());
        }
    }

    public void criarBanco() {
        try {

            sql = "CREATE TABLE IF NOT EXISTS PALAVRAS ("
                    + "idPalavra int auto_increment not null,"
                    + "palavra varchar(20) not null, "
                    + "dica varchar(20) not null, "
                    + "primary key(idPalavra) ) ";
            ps = mycon.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.execute(); // Executa o comando SQL

            sql = "CREATE TABLE IF NOT EXISTS JOGADORES ("
                    + "idUsuario varchar(15) not null ,"
                    + "nome varchar(50) not null ,"
                    + "email varchar(50) not null ,"
                    + "senha varchar(20) not null ,"
                    + "primary key (idUsuario) )";
            ps = mycon.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.execute(); // Executa o comando SQL

            sql = "CREATE TABLE IF NOT EXISTS PARTIDAS ("
                    + "idPartida int auto_increment not null ,"
                    + "dataHora varchar(50) not null ,"
                    + "palavraUtilizada varchar(20) not null ,"
                    + "idUsuario varchar(15) not null ,"
                    + "FOREIGN KEY (idUsuario) REFERENCES JOGADORES(idUsuario),"
                    + "idPalavra int not null ,"
                    + "FOREIGN KEY (idPalavra) REFERENCES PALAVRAS(idPalavra),"
                    + "primary key (idPartida) )";
            ps = mycon.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.execute(); // Executa o comando SQL

            sql = "CREATE TABLE IF NOT EXISTS PONTUACOES ("
                    + "idPontuacao int auto_increment not null ,"
                    + "pontuacao varchar(20) not null ,"
                    + "idUsuario varchar(15) not null ,"
                    + "FOREIGN KEY (idUsuario) REFERENCES JOGADORES(idUsuario),"
                    + "primary key (idPontuacao) )";
            ps = mycon.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.execute(); // Executa o comando SQL

            sql = "CREATE TABLE IF NOT EXISTS TENTATIVAS ("
                    + "idTentativa int auto_increment not null ,"
                    + "letraUtilizada char not null ,"
                    + "acertoErro boolean not null ,"
                    + "idPartida int not null ,"
                    + "FOREIGN KEY (idPartida) REFERENCES PARTIDAS(idPartida),"
                    + "primary key (idTentativa) )";
            ps = mycon.prepareStatement(sql); // prepara o objeto que irá executar o comando SQL
            ps.execute(); // Executa o comando SQL

            ps.close(); // Fecha o objeto
            mycon.close(); // Fecha a conexão

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao criar banco de dados " + err.getMessage());
        }
    }

}
