package pt.ulusofona.lp2.deisichess;

public class PecaRei extends Peca {

    public PecaRei(String identificador, String tipoDePeca, String equipa, String alcunha) {
        super(identificador, tipoDePeca, equipa, alcunha);
        this.pontoDeCadaPeca = 1000;
    }

    @Override
    public String toString() {
        if(estado.equals("capturado")) {
            return identificador + " | Rei | (infinito) | " + equipa + " | " + alcunha + " @ (n/a)";
        }

        return identificador + " | Rei | (infinito) | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
    }
}
