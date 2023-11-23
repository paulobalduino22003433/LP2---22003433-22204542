package pt.ulusofona.lp2.deisichess;

public class GameResults {
    public int blackCaptures;
    public int whiteCaptures;
    public int blackValidMoves;
    public int blackInvalidMoves;
    public int whiteValidMoves;
    public int whiteInvalidMoves;

    public GameResults(int blackCaptures, int blackValidMoves, int blackInvalidMoves,
                      int whiteCaptures, int whiteValidMoves, int whiteInvalidMoves) {

        this.blackCaptures = blackCaptures;
        this.blackValidMoves = blackValidMoves;
        this.blackInvalidMoves = blackInvalidMoves;
        this.whiteCaptures = whiteCaptures;
        this.whiteValidMoves = whiteValidMoves;
        this.whiteInvalidMoves = whiteInvalidMoves;
    }


    public int getBlackCaptures() {
        return blackCaptures;
    }

    public int getBlackValidMoves() {
        return blackValidMoves;
    }

    public int getBlackInvalidMoves() {
        return blackInvalidMoves;
    }

    public int getWhiteCaptures() {
        return whiteCaptures;
    }

    public int getWhiteValidMoves() {
        return whiteValidMoves;
    }

    public int getWhiteInvalidMoves() {
        return whiteInvalidMoves;
    }
}

