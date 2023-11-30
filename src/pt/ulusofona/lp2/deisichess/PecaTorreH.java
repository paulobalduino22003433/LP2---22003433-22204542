package pt.ulusofona.lp2.deisichess;

public class PecaTorreH extends Peca {

    public PecaTorreH(String identificador, String tipoDePeca, String equipa, String alcunha) {
        super(identificador, tipoDePeca, equipa, alcunha);
    }

    @Override
    public void setPng() {
        if (equipa.equals("10")) {
            png = "torre_h_black.png";
        } else {
            png = "torre_h_white.png";
        }
    }
}
