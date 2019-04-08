package neighbors.com.spacetrader.model;

import android.graphics.Color;

import java.util.Random;



public enum TechLevel {

    PREAGRICULTURE("Pre-Agriculture", Color.GRAY, 0),
    AGRICULTURE("Agriculture", Color.GREEN, 1),
    MEDEIVAL("Medieval", Color.BLUE, 2),
    RENAISSANCE("Renaissance", Color. MAGENTA, 3),
    EARLYINDUSTRIAL("Early-Industrial", Color.RED, 4),
    INDUSTRIAL("Industrial", Color.YELLOW, 5),
    POSTINDUSTRIAL("Post-Industrial", Color.WHITE, 6),
    HITECH("Hi-Tech", Color.CYAN, 7);

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

    private final String Techname;
    private final int color;
    private final int level;

    TechLevel(String name, int color, int level) {
        this.Techname = name;
        this.color = color;
        this.level = level;
    }

    public String getTechname() {
        return this.Techname;
    }
    public String toString() {
        return Techname;
    }
    public int getColor() {
        return color;
    }
    public int getLevel() { return level; }
}
