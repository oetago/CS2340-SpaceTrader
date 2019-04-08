package neighbors.com.spacetrader.model;

import java.util.Collections;
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
    private String currentPlanetName;

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

    public void setCurrentPlanetName(String name) { this.currentPlanetName = name ;}

    public String getCurrentPlanetName() { return this.currentPlanetName; }

    private Difficulty getDifficulty() {
        return this.difficulty;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Spaceship getSpaceship() {
        return this.spaceship;
    }

    public Map<SkillType, Integer> getSkills() {
        return Collections.unmodifiableMap(this.skills);
    }

    public void setSkills(Map<SkillType, Integer> skills) {
        this.skills = skills;
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

    public void addCredits(int update) {
        this.credits += update;
    }

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

    public Inventory getInventory() {
        return spaceship.getInventory();
    }

    /**
     * Checks if the player has enough credits
     *
     * @param neededCredits the credits needed
     * @return true if the player has enough credits, false otherwise
     */
    public boolean hasEnoughCredits(int neededCredits) {
        return credits < neededCredits;
    }

    public void removeCredits(int removeCredits) {
        credits -= removeCredits;
    }

    public int getQuantity(Good good) {
        return spaceship.getQuantity(good);
    }

    public int getFuel() {
        return spaceship.getFuel();
    }

    public void useFuel() {
        spaceship.useFuel();
    }

    public void setInventory(Inventory inventory) { this.spaceship.setInventory(inventory);}

    public int getDifficultyMultiplier() {
        return difficulty.getMultiplier();
    }

    public int getSkillsType(SkillType type) {
        return skills.get(type);
    }

    public void removeGood(Good narcotics, int narcNum) {
        getInventory().remove(narcotics, narcNum);
    }

    public int getDifficultyOrdinal() {
        return getDifficulty().ordinal();
    }

    public int getSpaceshipOrdinal() {
        return spaceship.ordinal();
    }

    public Map<Good, Integer> getInventoryMap() {
        return getInventory().getInventory();
    }
}
