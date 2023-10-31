package pt.ulusofona.lp2.deisichess;

public class Peca {
    String identificador;
    String tipoDePeca ;
    String equipa ;
    String alcunha ;
    String estado;


    public Peca(String identificador, String tipoDePeca, String equipa, String alcunha) {
        this.identificador = identificador;
        this.tipoDePeca = tipoDePeca;
        this.equipa = equipa;
        this.alcunha = alcunha;
    }

    public Peca(String identificador, String tipoDePeca, String equipa, String alcunha, String estado) {
        this.identificador = identificador;
        this.tipoDePeca = tipoDePeca;
        this.equipa = equipa;
        this.alcunha = alcunha;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Peca{" +
                "identificador='" + identificador + '\'' +
                ", tipoDePeca='" + tipoDePeca + '\'' +
                ", equipa='" + equipa + '\'' +
                ", alcunha='" + alcunha + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getTipoDePeca() {
        return tipoDePeca;
    }

    public String getEquipa() {
        return equipa;
    }

    public String getAlcunha() {
        return alcunha;
    }

}
