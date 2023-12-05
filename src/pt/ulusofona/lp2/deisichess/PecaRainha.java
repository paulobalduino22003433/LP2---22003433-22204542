package pt.ulusofona.lp2.deisichess;

public class PecaRainha extends Peca {
    public PecaRainha(String identificador, String tipoDePeca, String equipa, String alcunha) {
        super(identificador, tipoDePeca, equipa, alcunha);
        this.pontoDeCadaPeca = 8;
    }

    @Override
    public void setPng() {
        if (equipa.equals("10")) {
            png = "rainha_black.png";
        } else {
            png = "rainha_white.png";
        }
    }

    @Override
    public String toString() {
        if(estado.equals("capturado")) {
            return identificador + " | Rainha | 8 | " + equipa + " | " + alcunha + " @ (n/a)";
        }

        return identificador + " | Rainha | 8 | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
    }
}
