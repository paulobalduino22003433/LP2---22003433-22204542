package pt.ulusofona.lp2.deisichess;

public class PecaHomer extends Peca {

    public PecaHomer(String identificador, String tipoDePeca, String equipa, String alcunha) {
        super(identificador, tipoDePeca, equipa, alcunha);
        this.pontoDeCadaPeca = 2;
    }

    @Override
    public void setPng() {
        if (equipa.equals("10")) {
            png = "homer_black.png";
        } else {
            png = "homer_white.png";
        }
    }

    @Override
    public String toString() {
        if(estado.equals("capturado")) {
            return identificador + " | Homer Simpson | 2 | " + equipa + " | " + alcunha + " @ (n/a)";
        }

        return identificador + " | Homer Simpson | " + "2 | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
    }
}
