package pt.ulusofona.lp2.deisichess;

import java.util.Objects;

public class PecaTorreV extends Peca {
    public int pontos = 3;
    public PecaTorreV(String identificador, String tipoDePeca, String equipa, String alcunha) {
        super(identificador, tipoDePeca, equipa, alcunha);
    }

    @Override
    public void setPng() {
        if (equipa.equals("10")) {
            png = "torre_v_black.png";
        } else {
            png = "torre_v_white.png";
        }
    }

    @Override
    public String toString() {
        if(estado.equals("capturado")) {
            return identificador + " | TorreVert | 3 | " + equipa + " | " + alcunha + " @ (n/a)";
        }

        return identificador + " | TorreVert | 3 | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
    }

    @Override
    public int getPontos() {
        return pontos;
    }

    public boolean doesTorreVerticalMove(int x0, int y0, int x1, int y1) {
        int percursoVertical = y1 - y0;
        boolean obstacleInPath = false;
        if (x1 == x0) { // Check if the movement is vertical
            if (percursoVertical > 0) {
                for (int y = y0 + 1; y < y1; y++) {
                    if (y >= 0 && y < GameManager.cordenadasPecasArray.length) {
                        if (!Objects.equals(GameManager.cordenadasPecasArray[y][x0], "0")) {
                              obstacleInPath=true;
                              break;
                        }
                    }
                }
            }
            if (percursoVertical < 0) {
                for (int y = y1 + 1; y < y0; y++) {
                    if (y >= 0 && y < GameManager.cordenadasPecasArray.length) {
                        if (!Objects.equals(GameManager.cordenadasPecasArray[y][x0], "0")) {
                            obstacleInPath=true;
                            break;
                        }
                    }
                }
            }
            if (obstacleInPath) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

}
