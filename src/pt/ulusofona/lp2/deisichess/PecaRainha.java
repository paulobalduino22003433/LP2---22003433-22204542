package pt.ulusofona.lp2.deisichess;

public class PecaRainha extends Peca {

    public PecaRainha(String identificador, String tipoDePeca, String equipa, String alcunha) {
        super(identificador, tipoDePeca, equipa, alcunha);
    }

    @Override
    public void setPng() {
        if (equipa.equals("10")) {
            png = "rainha_black.png";
        } else {
            png = "rainha_white.png";
        }
    }
}
