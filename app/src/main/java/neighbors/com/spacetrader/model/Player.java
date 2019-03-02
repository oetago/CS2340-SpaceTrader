package neighbors.com.spacetrader.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Player Model to hold data throughout the game
 */
public class Player {

    private String characterName;
    private Map<SkillType, Integer> skills;
    private Difficulty difficulty;
    private int credits;
    private Spaceship spaceship;

    public Player() {
        skills = new HashMap<>();
        credits = 1000;
        spaceship = Spaceship.BEETLE;
    }


    /**
     * All the getter methods for the private fields of the Player class
     */
    public String getCharacterName() {
        return this.characterName;
    }

    /**
     * All the setter methods for the private fields of the Player class
     */
    public void setCharacterName(String name) {
        this.characterName = name;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public int getCredits() {
        return this.credits;
    }

    public Spaceship getSpaceship() {
        return this.spaceship;
    }

    public Map<SkillType, Integer> getSkills() {
        return this.skills;
    }

    public void assignSkillPoints(SkillType category, int numberOfPoints) {
        skills.put(category, numberOfPoints);
    }

    public void setSkillPoints(Map<SkillType, Integer> skillPoints) {
        this.skills = skillPoints;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void updateCredits(int update) { this.credits += update; }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    @Override
    public String toString() {
        return "Player{" +
                "characterName='" + characterName + '\'' +
                ", skills=" + skills +
                ", difficulty=" + difficulty +
                ", credits=" + credits +
                ", spaceship='" + spaceship.getName() + '\'' +
                '}';
    }
}
