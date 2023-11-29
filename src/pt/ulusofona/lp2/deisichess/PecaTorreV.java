package pt.ulusofona.lp2.deisichess;

public class PecaTorreV extends Peca {
    public PecaTorreV(String identificador, String tipoDePeca, String equipa, String alcunha) {
        super(identificador, tipoDePeca, equipa, alcunha);
    }

    @Override
    public void setPng() {
        if (equipa.equals("10")) {
            png = "torre_v_black.png";
        } else {
            png = "torre_v_white.png";
        }
    }
}
