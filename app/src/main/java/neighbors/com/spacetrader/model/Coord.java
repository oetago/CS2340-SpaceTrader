package neighbors.com.spacetrader.model;


public class Coord {
    int x;
    int y;

    public boolean equals(Object o) {
        Coord c = (Coord) o;
        return c.x == x && c.y == y;
    }

    public Coord(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int hashCode() {
        return x + (y * 13);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}