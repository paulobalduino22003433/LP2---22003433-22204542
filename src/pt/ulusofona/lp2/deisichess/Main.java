package pt.ulusofona.lp2.deisichess;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        Peca peca = new PecaRei("69", "", "10", "Comedor de XXT");
        peca.setX("0");
        peca.setY("0");

        gameManager.loadGame(new File("test-files/8x8.txt"));

        //gameManager.possibilidadesDeMovimentos(0, 8, 0, 0,0);
        try {
            gameManager.veSePodeSeMovimentar(2,0, 4, 2);
        } catch (StatsPecaException e) {
            System.out.println(e.nameException);
        }

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





        /*/
        for(Peca peca : gameManager.pecas){
            System.out.println(peca);
        }


        System.out.println(gameManager.cordenadasPecas);


        for (int i = 0; i < 4; i++) {
            System.out.print((gameManager.cordenadasPecasArray[i][1]) + " ");
        }
        System.out.println("\n" + Arrays.toString(gameManager.getPieceInfo(1)));
        System.out.println("\n\n" + Arrays.toString(gameManager.getSquareInfo(3,3)));


        for (int i = 0; i < 4; i++) {
            System.out.print((gameManager.cordenadasPecasArray[i][i]) + " ");
        }

        System.out.println(gameManager.move(1,0,1,1));
        System.out.println(gameManager.pecas.get(0));
        for (int i = 0; i < 4; i++) {
            System.out.print((gameManager.cordenadasPecasArray[i][i]) + " ");
        }
        System.out.println(gameManager.pecas.get(0));
        /*/
    }
}