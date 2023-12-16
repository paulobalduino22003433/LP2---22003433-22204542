package pt.ulusofona.lp2.deisichess;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/8x8.txt"));
        int x0 = 3, y0 = 4, x1 = 2, y1 =3; // Example: move one square diagonally
        Peca pecaParaMover = new Peca("7", "6", "10", "Hommie");
        /*/gameManager.setHomerCounter(9);/*/
        pecaParaMover.x = String.valueOf(x0);
        pecaParaMover.y = String.valueOf(y0);
        System.out.println("Piece type: " + pecaParaMover.tipoDePeca);
        System.out.println(gameManager.isMoveValid(pecaParaMover, x0, y0, x1, y1));
    }
}
