package pt.ulusofona.lp2.deisichess;

public class PecaPadreVila extends Peca {

    public PecaPadreVila(String identificador, String tipoDePeca, String equipa, String alcunha) {
        super(identificador, tipoDePeca, equipa, alcunha);
    }

    @Override
    public void setPng() {
        if (equipa.equals("10")) {
            png = "padre_vila_black.png";
        } else {
            png = "padre_vila_white.png";
        }
    }
}
