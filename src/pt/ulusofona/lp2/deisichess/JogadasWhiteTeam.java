package pt.ulusofona.lp2.deisichess;

public class JogadasWhiteTeam extends JogadasBlackTeam {

    @Override
    public String calculaCaptura(Tabuleiro tabuleiro, int nrPeca) {
        return ((nrPeca/2) - tabuleiro.whiteTeam.size()) + ""; //Ou seja, o nr total de pecas brancas - o nr de peca no tabuleiro. O resultado é q quantidade de pecas capturadas.
    }
}
