package pt.ulusofona.lp2.deisichess;

public class Main {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        testMoves(gameManager);
    }

    private static void testMoves(GameManager gameManager) {
        int x0 =0, y0 = 5, x1 = 5, y1 = 5;

        Peca pecaToMove = new Peca("1", "4", "10", "Torre Hor");

        pecaToMove.x = String.valueOf(x0);
        pecaToMove.y = String.valueOf(y0);
        System.out.println("Before move: " + pecaToMove.x + "," + pecaToMove.y);

        boolean moveResult = gameManager.move( x0, y0, x1, y1);

        System.out.println("After move:"+ pecaToMove.x + "," + pecaToMove.y);

        System.out.println("Move result: " + moveResult);
    }



}
