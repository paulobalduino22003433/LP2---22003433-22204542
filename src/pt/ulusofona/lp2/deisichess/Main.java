package pt.ulusofona.lp2.deisichess;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/8x8.txt"));
        int x0 =2, y0 = 0, x1 = 5, y1 = 3;

        Peca pecaParaMover = new Peca("4", "3", "10", "Amante de Praia");
        pecaParaMover.x = String.valueOf(x0);
        pecaParaMover.y = String.valueOf(y0);
        System.out.println("Piece type: " + pecaParaMover.tipoDePeca);


        System.out.println(gameManager.isMoveValid(pecaParaMover,x0,y0,x1,y1));
        System.out.println("x0: " + x0 + ", y0: " + y0 + ", x1: " + x1 + ", y1: " + y1);

        System.out.println("Before move: " + pecaParaMover.x + "," + pecaParaMover.y);

        boolean moveResult = gameManager.move( x0, y0, x1, y1);

        System.out.println("After move:"+ pecaParaMover.x + "," + pecaParaMover.y);

        System.out.println("Move result: " + moveResult);



    }
}