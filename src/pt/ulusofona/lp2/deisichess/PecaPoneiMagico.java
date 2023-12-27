package pt.ulusofona.lp2.deisichess;

public class PecaPoneiMagico extends Peca {
    public int pontos = 5;
    public PecaPoneiMagico(String identificador, String tipoDePeca, String equipa, String alcunha) {
        super(identificador, tipoDePeca, equipa, alcunha);
    }

    @Override
    public void setPng() {
        if (equipa.equals("10")) {
            png = "ponei_magico_black.png";
        } else {
            png = "ponei_magico_white.png";
        }
    }

    @Override
    public String toString() {
        if(estado.equals("capturado")) {
            return identificador + " | Ponei Mágico | 5 | " + equipa + " | " + alcunha + " @ (n/a)";
        }

        return identificador + " | Ponei Mágico | 5 | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
    }

    @Override
    public int getPontos() {
        return pontos;
    }

    public boolean doesPoneiMove(int x0, int y0, int x1, int y1) {
        int percursoHorizontal = x1 - x0;
        int percursoVertical = y1 - y0;

        if ((percursoHorizontal == 2 || percursoHorizontal == -2) && (percursoVertical == 2 || percursoVertical == -2)) {
            return true;
        } else {
            return false;
        }
    }

}
