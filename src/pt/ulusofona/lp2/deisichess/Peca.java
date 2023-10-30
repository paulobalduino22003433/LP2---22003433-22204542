package pt.ulusofona.lp2.deisichess;

public class Peca {
    String identificador;
    String tipoDePeca ;
    String equipa ;
    String alcunha ;

    public Peca(String identificador, String tipoDePeca, String equipa, String alcunha) {
        this.identificador = identificador;
        this.tipoDePeca = tipoDePeca;
        this.equipa = equipa;
        this.alcunha = alcunha;
    }
}
