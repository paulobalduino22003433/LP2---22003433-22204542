package pt.ulusofona.lp2.deisichess;

public class Hint implements Comparable<Hint> {
    public int x;
    public int y;
    public int points;

    public Hint(int x, int y, int points) {
        this.x = x;
        this.y = y;
        this.points = points;
    }

    @Override
    public int compareTo(Hint other) {
        return Integer.compare(this.points, other.points);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")->" + points;
    }
}
