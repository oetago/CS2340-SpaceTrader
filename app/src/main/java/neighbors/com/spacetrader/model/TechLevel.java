package neighbors.com.spacetrader.model;
import android.graphics.Color;
import java.util.Random;



public enum TechLevel {

    PREAGRICULTURE("Pre-Agriculture", Color.GRAY),
    AGRICULTURE("Agriculture", Color.GREEN),
    MEDEIVAL("Medieval", Color.BLUE),
    RENAISSANCE("Renaissance", Color. MAGENTA),
    EARLYINDUSTRIAL("Early-Industrial", Color.RED),
    INDUSTRIAL("Industrial", Color.YELLOW),
    POSTINDUSTRIAL("Post-Industrial", Color.WHITE),
    HITECH("Hi-Tech", Color.CYAN);

    public static TechLevel getRandom() {
        Random rand = new Random();
        int i = rand.nextInt(8);
        if (i == 1) {
            return PREAGRICULTURE;
        } else if (i == 2) {
            return AGRICULTURE;
        } else if (i == 3) {
            return MEDEIVAL;
        } else if (i == 4) {
            return RENAISSANCE;
        } else if (i == 5) {
            return EARLYINDUSTRIAL;
        } else if (i == 6) {
            return INDUSTRIAL;
        } else if (i == 7) {
            return POSTINDUSTRIAL;
        } else {
            return HITECH;
        }

    }
    private final String name;
    private final int color;

    TechLevel(String name, int color) {
        this.name = name;
        this.color = color;

    }
    public String getName() {
        return this.name;
    }
    public String toString() {
        return name;
    }
    public int getColor() {
        return color;
    }
}
