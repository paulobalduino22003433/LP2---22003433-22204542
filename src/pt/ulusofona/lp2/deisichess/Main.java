package pt.ulusofona.lp2.deisichess;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/8x8.txt"));
        int x0 =0, y0 = 3, x1 = 3, y1 = 3;

        Peca pecaToMove = new Peca("5", "4", "10", "Artolas");
        pecaToMove.x = String.valueOf(x0);
        pecaToMove.y = String.valueOf(y0);
        System.out.println(gameManager.isMoveValid(pecaToMove,x0,y0,x1,y1));
        System.out.println("Before move: " + pecaToMove.x + "," + pecaToMove.y);

        boolean moveResult = gameManager.move( x0, y0, x1, y1);

        System.out.println("After move:"+ pecaToMove.x + "," + pecaToMove.y);

        System.out.println("Move result: " + moveResult);

    }
}