package neighbors.com.spacetrader.model;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

/**
 * Player Model to hold data throughout the game
 */
@Entity
public class Player {

    @PrimaryKey
    @NonNull
    private String characterName;

    @TypeConverters(DataConverters.class)
    private Map<SkillType, Integer> skills;

    @TypeConverters(DataConverters.class)
    private Difficulty difficulty;
    private int credits;

    @TypeConverters(DataConverters.class)
    private Spaceship spaceship;
    // Current system and planet are stored in player to allow for multi-player to be easier
    @Ignore
    private SolarSystem currentSystem;
    @Ignore
    private Planet currentPlanet;
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

    public Difficulty getDifficulty() {
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
        return this.skills;
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

    public void setCurrentSystem(SolarSystem newSystem) {
        // TODO: make sure that current system cannot be set to a system that does not exist
        this.currentSystem = newSystem;
        // Defaults to first planet in system.
        this.currentPlanet = currentSystem.getPlanets().get(0);
    }

    public SolarSystem getCurrentSystem() {return this.currentSystem;}

    public void setCurrentPlanet(Planet newPlanet) {
        // Makes sure planet is in current system
        if (currentSystem.getPlanets().contains(newPlanet)) {
            this.currentPlanet = newPlanet;
        }
    }

    public Planet getCurrentPlanet() {return this.currentPlanet;}

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
        return credits >= neededCredits;
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
}
