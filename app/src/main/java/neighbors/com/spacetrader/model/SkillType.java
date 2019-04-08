package neighbors.com.spacetrader.model;

public enum SkillType {
    PILOT("Pilot"),
    FIGHTER("Fighter"),
    ENGINEER("Engineer"),
    TRADER("Trader");

    private final String name;

    SkillType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}