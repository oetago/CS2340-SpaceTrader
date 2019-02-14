package neighbors.com.spacetrader.ui.configuration;

public enum Difficulty {
    Beginner ("Beginner"),
    Easy ("Easy"),
    Normal ("Normal"),
    Hard ("Hard"),
    Impossible ("Impossible");

    private final String name;

    Difficulty (String pname) {
        name = pname;
    }

    public String getName() { return name; }
}
