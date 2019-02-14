package neighbors.com.spacetrader.model;

import java.util.Arrays;
import java.util.List;

/**
 * Player Model to hold data throughout the game
 */
public class Player {

    private Difficulty difficulty;

    public static List<String> legalDifficulty = Arrays.asList
            ("Beginner", "Easy", "Medium", "Hard", "Impossible");
}
