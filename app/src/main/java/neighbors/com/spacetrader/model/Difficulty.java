package neighbors.com.spacetrader.model;

public enum Difficulty {
    BEGINNER("Beginner"),
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard"),
    IMPOSSIBLE("Impossible");

    private final String name;

    Difficulty(String pname) {
        name = pname;
    }

    public String getName() {
        return name;
    }
}
