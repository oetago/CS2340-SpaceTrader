package neighbors.com.spacetrader.model;
import java.util.HashMap;

/**
 * Player Model to hold data throughout the game
 */
public class Player {
    private String characterName;
    private HashMap<SkillCategory, Integer> skills;
    private Difficulty difficulty;
    private int credits;
    private String spaceship;
    private int availableSkillPoints;


    /*
    All the getter methods for the private fields of the Player class
     */
    public String getCharacterName() {
        return this.characterName;
    }
    public HashMap<SkillCategory, Integer> getSkills() {
        return this.skills;
    }
    public Difficulty getDifficulty() {
        return this.difficulty;
    }
    public int getCredits() {
        return this.credits;
    }
    public String getSpaceship() {
        return this.spaceship;
    }
    public int getAvailableSkillPoints() {
        return this.availableSkillPoints;
    }

    /*
    All the setter methods for the private fields of the Player class
     */
    public void setCharacterName(String name) {
        this.characterName = name;
    }
    public void assignSkillPoints(SkillCategory category, int numberOfPoints) {
        skills.put(category,numberOfPoints);
        this.availableSkillPoints -= numberOfPoints;
    }
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }
    public void setSpaceship(String spaceship) {
        this.spaceship = spaceship;
    }

}
