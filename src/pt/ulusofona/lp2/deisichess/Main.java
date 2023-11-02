package pt.ulusofona.lp2.deisichess;

import java.io.File;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/4x4.txt"));
        System.out.println("Tamanho do Tabuleiro: " + gameManager.tamanhoTabuleiro);
        System.out.println("Número de Peças: " + gameManager.numeroPecas);
        System.out.println("Cordenadas das Peças: " + gameManager.cordenadasPecas);

        for(Peca peca : gameManager.pecas){
            System.out.println(peca);
        }


        System.out.println(gameManager.cordenadasPecas);

        for (int i = 0; i < 4; i++) {
            System.out.print((gameManager.cordenadasPecasArray[i][1]) + " ");
        }

        System.out.println("\n" + Arrays.toString(gameManager.getPieceInfo(1)));

        System.out.println(gameManager.getPieceInfoAsString(6));
    }
}
