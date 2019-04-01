package neighbors.com.spacetrader.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * Player Model to hold data throughout the game
 */
@Entity(tableName = "player_table")
public class Player {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String characterName;
    private Map<SkillType, Integer> skills;
    private Difficulty difficulty;
    private int credits;
    @Embedded
    private Spaceship spaceship;
    // Current system and planet are stored in player to allow for multi-player to be easier
    @Embedded
    private SolarSystem currentSystem;
    private Planet currentPlanet;
    @Embedded
    private Universe universe;
    @Embedded
    private Market market;

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

    public Universe getUniverse() { return this.universe; }

    public SolarSystem getSolarSystem() { return this.currentSystem;}

    public Market getMarket() { return this.market; }

    public int setCredits(Integer credits) { this.credits = credits; }


    /**
     * All the setter methods for the private fields of the Player class
     */
    public void setCharacterName(String name) {
        this.characterName = name;
    }
    public void setUniverse(Universe universe) { this.universe = universe; }

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

    public void setMarket(Market market) { this.market = market; }
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

    public SolarSystem getSolarSystem(String name) {
        if (name == null) {
            return universe.getSolarSystems().get(0);
        }
        List<SolarSystem> systems = universe.getSolarSystems();
        for (SolarSystem solarSystem : systems) {
            if (solarSystem.getName().equals(name)) {
                return solarSystem;
            }
        }
        return null;
    }

    public int getQuantity(Good good) {
        return spaceship.getQuantity(good);
    }
}
