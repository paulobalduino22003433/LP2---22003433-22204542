package pt.ulusofona.lp2.deisichess;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        Peca peca = new PecaRei("69", "", "10", "Comedor de XXT");
        peca.setX("0");
        peca.setY("0");

        gameManager.loadGame(new File("test-files/8x8.txt"));
        int x0 =0, y0 = 3, x1 = 3, y1 = 3;

        //gameManager.possibilidadesDeMovimentos(0, 8, 0, 0,0);

        gameManager.move(1,0, 1, 3);

        //System.out.println("Tamanho do Tabuleiro: " + gameManager.tabuleiro.getTamanhoTabuleiro());
        //System.out.println("Número de Peças: " + gameManager.tabuleiro.getNumPecaTotal());
        //System.out.println("Cordenadas das Peças: " + gameManager);
        //System.out.println("movi:" + gameManager.move(0,0,1,0));

        //gameManager.saveGame(new File("src/aaa.txt"));

        /*
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 16; i++) {
                System.out.println(gameManager.pecas.get(i).toString());
            }
        }

         */




        /*
        Peca pecaToMove = new Peca("5", "4", "10", "Artolas");
        pecaToMove.x = String.valueOf(x0);
        pecaToMove.y = String.valueOf(y0);
        //System.out.println(gameManager.isMoveValid(pecaToMove,x0,y0,x1,y1));
        System.out.println("Before move: " + pecaToMove.x + "," + pecaToMove.y);

        boolean moveResult = gameManager.move( x0, y0, x1, y1);

        System.out.println("After move:"+ pecaToMove.x + "," + pecaToMove.y);

        System.out.println("Move result: " + moveResult);

         */
    }
}