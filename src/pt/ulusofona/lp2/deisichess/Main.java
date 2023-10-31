package pt.ulusofona.lp2.deisichess;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        System.out.println(gameManager.loadGame(new File("test-files/4x4.txt")));
    }
}
