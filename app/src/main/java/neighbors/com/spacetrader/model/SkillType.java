package neighbors.com.spacetrader.model;

/**
 * Player skills
 */
public enum SkillType {
    PILOT("Pilot"),
    FIGHTER("Fighter"),
    ENGINEER("Engineer"),
    TRADER("Trader");

    private final String name;

    SkillType(String name) {
        this.name = name;
    }

    /**
     * Gets name of skill
     * @return name of skill
     */
    public String getName() {
        return name;
    }
}