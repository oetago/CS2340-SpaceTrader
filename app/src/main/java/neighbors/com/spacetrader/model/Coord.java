package neighbors.com.spacetrader.model;

import org.jetbrains.annotations.NotNull;

/**
 * Class used to represent the coordinates of a planet/solar system
 */
public class Coord {
    final int x;
    final int y;

    /**
     * Asserts if two sets of coordinates are equals
     * @param o the set of coordinates to compare to
     * @return whether or not the coordinates are equivalent
     */
    public boolean equals(Object o) {
        if (!(o instanceof Coord)) {
            return false;
        }
        Coord c = (Coord) o;
        return (c.x == x) && (c.y == y);
    }

    /**
     * Creates an instance of Coord class for use in a solar system
     * @param x the x coordinate on the x-y plane
     * @param y the y-coordinate on the x-y plane
     */
    public Coord(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Overrides Object's hashCode() to allow for coordinates in a map
     * @return hashcode of a Coord object
     */
    public int hashCode() {
        return x + (y * 13);
    }

    @NotNull
    @Override
    /**
     * Converts coordinate to String
     * @return String representing the coordinate
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}