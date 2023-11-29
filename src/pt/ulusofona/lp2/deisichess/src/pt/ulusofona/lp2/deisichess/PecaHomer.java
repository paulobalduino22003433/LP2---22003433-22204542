package pt.ulusofona.lp2.deisichess;

public class PecaHomer extends Peca {

    public PecaHomer(String identificador, String tipoDePeca, String equipa, String alcunha) {
        super(identificador, tipoDePeca, equipa, alcunha);
    }

    @Override
    public void setPng() {
        if (equipa.equals("10")) {
            png = "homer_black.png";
        } else {
            png = "homer_white.png";
        }
    }
}
