package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


        @Test
        void testHomer() throws IOException, InvalidGameInputException {
            GameManager gameManager = new GameManager();
            gameManager.loadGame(new File("test-files/8x8.txt"));
            int x0 = 6, y0 = 0, x1 = 5, y1 =1;
            int x2 = 6, y2 = 7, x3 = 5, y3 =6;
            int x4 = 3, y4 = 4, x5 = 2, y5 =3;
            Peca pecaParaMover = new Peca("7", "6", "10", "Hommie");
            GameManager.nrTurno = 2;
            assertEquals(true,gameManager.isMoveValid(pecaParaMover,x0,y0,x1,y1));
            assertEquals(true,gameManager.isMoveValid(pecaParaMover,x2,y2,x3,y3));
            assertEquals(true,gameManager.isMoveValid(pecaParaMover,x4,y4,x5,y5));
            GameManager.nrTurno = 3;
            assertEquals(false,gameManager.isMoveValid(pecaParaMover,x0,y0,x1,y1));
            assertEquals(false,gameManager.isMoveValid(pecaParaMover,x2,y2,x3,y3));
            assertEquals(false,gameManager.isMoveValid(pecaParaMover,x4,y4,x5,y5));
            PecaHomer homer = new PecaHomer(pecaParaMover.identificador,pecaParaMover.tipoDePeca,pecaParaMover.equipa,pecaParaMover.alcunha);
            assertEquals(true,homer.isSleeping());
        }

        @Test
        void testToStringHomer() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/8x8.txt"));
        GameManager.nrTurno=6;
            Peca pecaParaMover = new Peca("7", "6", "10", "Hommie");
            PecaHomer homer = new PecaHomer(pecaParaMover.identificador,pecaParaMover.tipoDePeca,pecaParaMover.equipa,pecaParaMover.alcunha);
        assertEquals("Doh! zzzzzz",homer.toString());
        GameManager.nrTurno=8;
        gameManager.setCoordinatesPieces();
            if (gameManager.tabuleiro.getIsBlackTurn()) {
                for (Peca peca : gameManager.blackTeam) {
                    if (peca.getIdentificador().equals(pecaParaMover.identificador)) {
                        pecaParaMover=peca;
                        pecaParaMover.x=peca.x;
                        pecaParaMover.y=peca.y;
                        homer = new PecaHomer(pecaParaMover.identificador,pecaParaMover.tipoDePeca,pecaParaMover.equipa,pecaParaMover.alcunha);
                        homer.x=pecaParaMover.x;
                        homer.y=pecaParaMover.y;
                    }
                }
            }
            assertEquals("7 | Homer Simpson | 2 | 10 | Hommie @ (6, 0)",homer.toString());
        }


    @Test
    void testColocarTipoDePeca() {
        PecaRei pecaRei = new PecaRei("1", "0", "equipaA", "ReiA");
        assertEquals("1", pecaRei.getIdentificador());
        assertEquals("0", pecaRei.getTipoDePeca());
        assertEquals("equipaA", pecaRei.getEquipa());
        assertEquals("ReiA", pecaRei.getAlcunha());
        assertEquals(1000, pecaRei.getPontos());
    }

    @Test
    void testColocarTipoDePeca2() {
        PecaJoker pecaJoker = new PecaJoker("2", "7", "equipaB", "JokerB");
        assertEquals("2", pecaJoker.getIdentificador());
        assertEquals("7", pecaJoker.getTipoDePeca());
        assertEquals("equipaB", pecaJoker.getEquipa());
        assertEquals("JokerB", pecaJoker.getAlcunha());
        assertEquals(4, pecaJoker.getPontos());
    }

    @Test
    void testGetPieceInfoAsStringValidID() {
        GameManager gameManager = new GameManager();
        // Add some sample pieces to the GameManager
        gameManager.pecas.add(new PecaRei("1", "0", "equipaA", "ReiA"));
        gameManager.pecas.add(new PecaJoker("2", "7", "equipaB", "JokerB"));

        assertEquals("1 | Rei | (infinito) | equipaA | ReiA @ (, )", gameManager.getPieceInfoAsString(1));
        assertEquals("2 | Joker/Rainha | 4 | equipaB | JokerB @ (, )", gameManager.getPieceInfoAsString(2));
    }

    @Test
    void testGetPieceInfoAsStringEmptyGameManager() {
        GameManager gameManager = new GameManager();
        assertEquals("", gameManager.getPieceInfoAsString(1)); // Empty GameManager
    }

    @Test
    void testGameOverWhiteWins() {
        GameManager gameManager = new GameManager();

        // Set up the game with only the white king
        gameManager.whiteTeam.add(new PecaRei("1", "0", "equipaB", "ReiB"));

        assertTrue(gameManager.gameOver());
        assertTrue(gameManager.gameResults.getResultadoJogo().equals("VENCERAM AS BRANCAS"));
    }

    @Test
    void testGameOverBlackWins() {
        GameManager gameManager = new GameManager();

        // Set up the game with only the black king
        gameManager.blackTeam.add(new PecaRei("1", "0", "equipaA", "ReiA"));

        assertTrue(gameManager.gameOver());
        assertTrue(gameManager.gameResults.getResultadoJogo().equals("VENCERAM AS PRETAS"));
    }

    @Test
    void testGameOverDraw() {
        GameManager gameManager = new GameManager();

        // Set up the game with only one piece per team (both kings)
        gameManager.blackTeam.add(new PecaRei("1", "0", "equipaA", "ReiA"));
        gameManager.whiteTeam.add(new PecaRei("2", "0", "equipaB", "ReiB"));

        assertTrue(gameManager.gameOver());
        assertTrue(gameManager.gameResults.getResultadoJogo().equals("EMPATE"));
    }

}
