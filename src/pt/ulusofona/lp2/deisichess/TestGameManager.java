package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;

import java.io.File;

public class TestGameManager {

    @Test
    void testloadGame(){
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/4x4.txt"));
    }
}
