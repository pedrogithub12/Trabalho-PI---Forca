
package model;


public class Tentativas {
    public int idTentativas;
    public char letraUtilizada;
    public boolean acertoErro;

    public int getIdTentativas() {
        return idTentativas;
    }

    public void setIdTentativas(int idTentativas) {
        this.idTentativas = idTentativas;
    }

    public char getLetraUtilizada() {
        return letraUtilizada;
    }

    public void setLetraUtilizada(char letraUtilizada) {
        this.letraUtilizada = letraUtilizada;
    }

    public boolean isAcertoErro() {
        return acertoErro;
    }

    public void setAcertoErro(boolean AcertoErro) {
        this.acertoErro = AcertoErro;
    }
}
