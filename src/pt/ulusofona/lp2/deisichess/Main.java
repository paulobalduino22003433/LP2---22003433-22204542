package pt.ulusofona.lp2.deisichess;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/4x4.txt"));
        System.out.println("Tamanho do Tabuleiro: " + gameManager.tamanhoTabuleiro);
        System.out.println("Número de Peças: " + gameManager.numeroPecas);
        System.out.println("Cordenadas das Peças: " + gameManager.cordenadasPecas);
        /*/for(Peca peca : gameManager.pecas){
            System.out.println(peca);
        }/*/
        System.out.println(gameManager.pecasMap);

    }

}
