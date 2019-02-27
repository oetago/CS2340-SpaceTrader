package neighbors.com.spacetrader.model;
import java.util.Random;


public enum TechLevel {

    PREAGRICULTURE("Pre-Agriculture"),
    AGRICULTURE("Agriculture"),
    MEDEIVAL("Medieval"),
    RENAISSANCE("Renaissance"),
    EARLYINDUSTRIAL("Early-Industrial"),
    INDUSTRIAL("Industrial"),
    POSTINDUSTRIAL("Post-Industrial"),
    HITECH("Hi-Tech");

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

    TechLevel(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public String toString() {
        return name;
    }
}
