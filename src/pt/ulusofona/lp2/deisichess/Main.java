package pt.ulusofona.lp2.deisichess;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static pt.ulusofona.lp2.deisichess.GameManager.cordenadasPecasArray;

public class Main {

    public static void main(String[] args) throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/8x8.txt"));
        int x0 = 4, y0 = 0, x1 = 4, y1 = 5;

        Peca pecaParaMover = new Peca("6", "5", "10", "O Maior Grande");
        pecaParaMover.x = String.valueOf(x0);
        pecaParaMover.y = String.valueOf(y0);
        System.out.println("Piece type: " + pecaParaMover.tipoDePeca);
        System.out.println(gameManager.isMoveValid(pecaParaMover, x0, y0, x1, y1));
        System.out.println("x0: " + x0 + ", y0: " + y0 + ", x1: " + x1 + ", y1: " + y1);

        boolean moveResult = gameManager.move(x0, y0, x1, y1);

        System.out.println("Move result: " + moveResult);

        System.out.println("Math.abs(y1 - y0): " + Math.abs(y1 - y0));

        boolean isValidMove = gameManager.isMoveValid(pecaParaMover, x0, y0, x1, y1);
        System.out.println("Move result: " + isValidMove);

    }

}
