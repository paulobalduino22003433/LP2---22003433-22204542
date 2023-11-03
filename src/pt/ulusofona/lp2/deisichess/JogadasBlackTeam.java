package pt.ulusofona.lp2.deisichess;

public class JogadasBlackTeam {
    int nrJogadaInvalida = 0;
    int nrTentativaInvalida = 0;


    public JogadasBlackTeam() {}


    public String calculaCaptura(Tabuleiro tabuleiro, int nrPeca) {
        return ((nrPeca/2) - tabuleiro.blackTeam.size()) + ""; //Ou seja, o nr total de pecas pretas - o nr de peca no tabuleiro. O resultado é q quantidade de pecas capturadas.
    }

    public void incJogadaInvalida() {
        nrJogadaInvalida++;
    }

    public void incTentativaInvalida() {
        nrTentativaInvalida++;
    }


    public String getJogadasInvalidas() {
        return nrJogadaInvalida + "";
    }

    public String getTentativasInvalidas() {
        return nrTentativaInvalida + "";
    }
}
