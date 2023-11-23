package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestGameManager {

    @Test
    void testloadGame(){
        GameManager gameManager = new GameManager();
        try {
        gameManager.loadGame(new File("test-files/4x4.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidGameInputException e) {
            throw new RuntimeException(e);
        }
    }

/*/
        @Test
        void testGetSquareInfo() {
            GameManager gameManager = new GameManager();
            gameManager.loadGame(new File("test-files/4x4.txt"));

            String[] squareInfo = gameManager.getSquareInfo(1, 2);
            assertEquals("3", squareInfo[0]);
            String[] squareInfo2 = gameManager.getSquareInfo(2, 3);
            assertNull(squareInfo2);
        }

 */
}
