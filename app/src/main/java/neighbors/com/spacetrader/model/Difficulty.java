package neighbors.com.spacetrader.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Game difficulty enum
 */
public enum Difficulty {
    BEGINNER("Beginner", 10),
    EASY("Easy", 20),
    MEDIUM("Medium", 30),
    HARD("Hard", 40),
    IMPOSSIBLE("Impossible", 50);

    private final String name;
    private final int multiplier;

    Difficulty(String name, int multi) {
        this.name = name;
        multiplier = multi;
    }

    /**
     * Get List of difficulties for adapters
     *
     * @return a list of the difficulties
     */
    public static List<String> stringValues() {
        ArrayList<String> values = new ArrayList<>();
        for (Difficulty value : values()) {
            values.add(value.name);
        }
        return values;
    }

    public String getName() {
        return name;
    }

    public int getMultiplier() {
        return multiplier;
    }
}
