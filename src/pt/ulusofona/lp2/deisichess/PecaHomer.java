package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class PecaHomer extends Peca {

    public PecaHomer(String identificador, String tipoDePeca, String equipa, String alcunha) {
        super(identificador, tipoDePeca, equipa, alcunha);
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
    public StatsPecaException moveOfPiece(String equipe, int x0, int y0, int x1, int y1, ArrayList<String> movimentosParaPeca, ArrayList<Peca> blackTeam, ArrayList<Peca> whiteTeam) throws StatsPecaException {
        String[] partes;

        for (String s : movimentosParaPeca) {
            partes = s.split("/");

            for (Peca pecaPreta : blackTeam) {
                if (pecaPreta.getX().equals(partes[0]) && pecaPreta.getY().equals(partes[1])) {
                    if (!pecaPreta.getEquipa().equals(equipe) && pecaPreta.getX().equals(x1 + "") && pecaPreta.getY().equals(y1 + "")) {
                        throw new StatsPecaException("CAPTURE");
                    }
                }
            }

            for (Peca pecaBranca : whiteTeam) {
                if (pecaBranca.getX().equals(partes[0]) && pecaBranca.getY().equals(partes[1])) {
                    if (!pecaBranca.getEquipa().equals(equipe) && pecaBranca.getX().equals(x1 + "") && pecaBranca.getY().equals(y1 + "")) {
                        throw new StatsPecaException("CAPTURE");
                    }
                }
            }

            if (partes[0].equals(x1 + "") && partes[1].equals(y1 + "")) {
                throw new StatsPecaException("VALID");
            }
        }

        throw new StatsPecaException("INVALID"); //A peça não se pode movimentar para a casa x1 e y1
    }

    @Override
    public String toString() {
        if (turnoAtual % 3 == 0) {
            return "Doh! zzzzzz";
        }

        if(estado.equals("capturado")) {
            return identificador + " | Homer Simpson | 2 | " + equipa + " | " + alcunha + " @ (n/a)";
        }

        return identificador + " | Homer Simpson | " + "2 | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
    }
}
