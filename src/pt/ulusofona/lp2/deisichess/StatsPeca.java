package pt.ulusofona.lp2.deisichess;

public class StatsPeca {
    private int captures;
    private int validMoves;
    private int invalidMoves;

    public StatsPeca() {
        this.captures = 0;
        this.validMoves = 0;
        this.invalidMoves = 0;
    }

    public void incCaptures() {
        captures++;
    }

    public void incValidMoves() {
        validMoves++;
    }

    public void incInvalidMoves() {
        invalidMoves++;
    }

    public int getCaptures() {
        return captures;
    }

    public int getValidMoves() {
        return validMoves;
    }

    public int getInvalidMoves() {
        return invalidMoves;
    }
}
