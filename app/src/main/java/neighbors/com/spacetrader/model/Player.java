package neighbors.com.spacetrader.model;

import org.jetbrains.annotations.NotNull;

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

    public Player(String name, Difficulty difficulty, Map<SkillType, Integer> skills) {
        this();
        this.characterName = name;
        this.difficulty = difficulty;
        this.skills = skills;
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

    @NotNull
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

    /**
     * Removes player credits
     * @param removeCredits amount of credits to be removed
     */
    public void removeCredits(int removeCredits) {
        credits -= removeCredits;
    }

    /**
     * Gets the quantity of a particular good in the spaceship
     * @param good good whose quantity is requested
     * @return quantity of requested good
     */
    public int getQuantity(Good good) {
        return spaceship.getQuantity(good);
    }

    /**
     * Gets fuel level of spaceship
     * @return the quantity of fuel remaining
     */
    public int getFuel() {
        return spaceship.getFuel();
    }

    /**
     * Calls the spaceship useFuel() method to use fuel
     */
    public void useFuel() {
        spaceship.useFuel();
    }

    /**
     * Sets spaceship inventory
     * @param inventory new spaceship inventory
     */
    public void setInventory(Inventory inventory) { this.spaceship.setInventory(inventory);}

    /**
     * Gets difficulty multiplier
     * @return difficulty multiplier from Difficulty
     */
    public int getDifficultyMultiplier() {
        return difficulty.getMultiplier();
    }

    /**
     * Gets player skill level for some skill
     * @param type the skill type requested
     * @return the skill level of that skill
     */
    public int getSkillsType(SkillType type) {
        return skills.get(type);
    }

    /**
     * Removes specified good and quantity from inventory
     * @param narcotics good to be removed from inventory
     * @param narcNum the quantity to be removed from inventory
     */
    public void removeGood(Good narcotics, int narcNum) {
        spaceship.remove(narcotics, narcNum);
    }

    /**
     * Gets the difficulty as a number
     * @return the player difficulty as a number
     */
    public int getDifficultyOrdinal() {
        return getDifficulty().ordinal();
    }

    /**
     * Gets spaceship type as a number
     * @return number representing spaceship type
     */
    public int getSpaceshipOrdinal() {
        return spaceship.ordinal();
    }

    /**
     * Gets inventory as map of goods to integers
     * @return map of goods to quantities in inventory
     */
    public Map<Good, Integer> getInventoryMap() {
        return spaceship.getInventoryMap();
    }
}
