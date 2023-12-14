package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class PecaJoker extends Peca {
    public PecaJoker(String identificador, String tipoDePeca, String equipa, String alcunha) {
        super(identificador, tipoDePeca, equipa, alcunha);
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
    public StatsPecaException moveOfPiece(int pecaAtualJoker, String equipe, int x0, int y0, int x1, int y1, ArrayList<String> movimentosParaPeca, ArrayList<Peca> blackTeam, ArrayList<Peca> whiteTeam) throws StatsPecaException {
        Peca pecaAtual;

        switch (pecaAtualJoker) {
            case 1 -> {
                pecaAtual = new PecaRainha("", "1", "", "");
                throw pecaAtual.moveOfPiece(equipe, x0, y0, x1, y1, movimentosParaPeca, blackTeam, whiteTeam);
            }
            case 2 -> {
                pecaAtual = new PecaPoneiMagico("", "2", "", "");
                throw pecaAtual.moveOfPiece(equipe, x0, y0, x1, y1, movimentosParaPeca, blackTeam, whiteTeam);
            }
            case 3 -> {
                pecaAtual = new PecaPadreVila("", "3", "", "");
                throw pecaAtual.moveOfPiece(equipe, x0, y0, x1, y1, movimentosParaPeca, blackTeam, whiteTeam);
            }
            case 4 -> {
                pecaAtual = new PecaTorreH("", "4", "", "");
                throw pecaAtual.moveOfPiece(equipe, x0, y0, x1, y1, movimentosParaPeca, blackTeam, whiteTeam);
            }
            case 5 -> {
                pecaAtual = new PecaTorreV("", "5", "", "");
                throw pecaAtual.moveOfPiece(equipe, x0, y0, x1, y1, movimentosParaPeca, blackTeam, whiteTeam);
            }
            case 6 -> {
                pecaAtual = new PecaHomer("", "6", "", "");
                throw pecaAtual.moveOfPiece(equipe, x0, y0, x1, y1, movimentosParaPeca, blackTeam, whiteTeam);
            }
        }

        throw new StatsPecaException("INVALID"); //A peça não se pode movimentar para a casa x1 e y1
    }

    @Override
    public String toString() {
        int pecaAtualJoker = turnoAtual + 1;

        while (pecaAtualJoker > 6) {
            pecaAtualJoker -= 6;
        }

        String[] opcoesJoker = {"Rainha", "Ponei Mágico", "Padre da Vila", "TorreHor", "TorreVert", "Homer Simpson"};

        String output = opcoesJoker[pecaAtualJoker];

        if(estado.equals("capturado")) {
            return identificador + " | Joker/" + output + " | 4 | " + equipa + " | " + alcunha + " @ (n/a)";
        }

        return identificador + " | Joker/" + output + "4 | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
    }
}
