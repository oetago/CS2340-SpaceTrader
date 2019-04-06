package neighbors.com.spacetrader.model.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class PlayerSave {

    @PrimaryKey
    @NonNull
    private String characterName;
    private String skills;
    private int difficulty;
    private int credits;
    private int spaceSpaceShip;
    private int fuelCount;
    private String currentPlanetName;
    private String inventory;
    private String planents;

    public PlayerSave(@NonNull String characterName, String skills, int difficulty, int credits, int spaceSpaceShip, int fuelCount, String currentPlanetName, String inventory, String planents) {
        this.characterName = characterName;
        this.skills = skills;
        this.difficulty = difficulty;
        this.credits = credits;
        this.spaceSpaceShip = spaceSpaceShip;
        this.fuelCount = fuelCount;
        this.currentPlanetName = currentPlanetName;
        this.inventory = inventory;
        this.planents = planents;
    }

    @Ignore
    public PlayerSave() {
    }

    public String getPlanents() {
        return planents;
    }

    public void setPlanents(String planents) {
        this.planents = planents;
    }

    @NonNull
    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(@NonNull String characterName) {
        this.characterName = characterName;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getSpaceSpaceShip() {
        return spaceSpaceShip;
    }

    public void setSpaceSpaceShip(int spaceSpaceShip) {
        this.spaceSpaceShip = spaceSpaceShip;
    }

    public int getFuelCount() {
        return fuelCount;
    }

    public void setFuelCount(int fuelCount) {
        this.fuelCount = fuelCount;
    }

    public String getCurrentPlanetName() {
        return currentPlanetName;
    }

    public void setCurrentPlanetName(String currentPlanetName) {
        this.currentPlanetName = currentPlanetName;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }
}
