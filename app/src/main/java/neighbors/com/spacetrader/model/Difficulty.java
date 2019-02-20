package neighbors.com.spacetrader.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Game difficulty enum
 */
public enum Difficulty {
    BEGINNER("Beginner"),
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard"),
    IMPOSSIBLE("Impossible");

    private final String name;

    Difficulty(String name) {
        this.name = name;
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
}
