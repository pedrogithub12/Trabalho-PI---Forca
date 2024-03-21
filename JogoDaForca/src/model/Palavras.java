
package model;


public class Palavras {
    public int idPalavra;
    public String palavra;
    public String dica;

    public int getId_palavra() {
        return idPalavra;
    }

    public void setId_palavra(int id_palavra) {
        this.idPalavra = id_palavra;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }
    
}
