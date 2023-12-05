package pt.ulusofona.lp2.deisichess;

public class PecaJoker extends Peca {

    public PecaJoker(String identificador, String tipoDePeca, String equipa, String alcunha) {
        super(identificador, tipoDePeca, equipa, alcunha);
        this.pontoDeCadaPeca = 4;
    }

    @Override
    public void setPng() {
        if (equipa.equals("10")) {
            png = "joker_black.png";
        } else {
            png = "joker_white.png";
        }
    }

    @Override
    public String toString() {
        String pecaAtual = "/";

        if(estado.equals("capturado")) {
            return identificador + " | Joker" + pecaAtual + " | 4 | " + equipa + " | " + alcunha + " @ (n/a)";
        }

        return identificador + " | Joker" + pecaAtual + " | 4 | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";

    }
}
