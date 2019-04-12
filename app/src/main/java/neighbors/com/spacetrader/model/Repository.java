package neighbors.com.spacetrader.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import neighbors.com.spacetrader.model.db.DataConvertHelper;
import neighbors.com.spacetrader.model.db.PlayerSave;


/**
 * Singleton class to hold all data
 */
public final class Repository {

    private static volatile Repository repo;

    private final PlayerDao playerDao;
    private Player player;
    private Universe universe;

    private Repository(Context context) {
        playerDao = PlayerDatabase.getInstance(context).playerDao();
        PlayerSave save = playerDao.getPlayer();
        if (save != null) {
            player = getPlayerFromSave(save);
            List<Planet> planets = DataConvertHelper.jsonToPlanets(save.getPlanets());
            List<SolarSystem> solarSystems = new ArrayList<>();
            for (Planet planet : planets) {
                solarSystems.add(new SolarSystem(planet));
            }
            universe = new Universe(solarSystems);
        }
    }

    /**
     * Gets instance of the current repository with universe data
     * @param context the universe containing the information
     * @return an instance of repository
     */
    public static Repository getInstance(Context context) {
        if (repo == null) {
            repo = new Repository(context);
        }
        return repo;
    }

    /**
     * Gets current player
     * @return player object
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Gets current inventory
     * @return inventory object
     */
    public Inventory getInventory() {
        return player.getInventory();
    }

    /**
     * Gets current universe
     * @return universe object
     */
    public Universe getUniverse() {
        return universe;
    }

    /**
     * Gets all solar systems in universe
     * @return list of all solar system objects in universe
     */
    public List<SolarSystem> getSolarSystems() {
        return universe.getSolarSystems();
    }

    /**
     * Gets a solar system in universe by its name
     * @param name name of desired solar system
     * @return solar system matching name
     */
    public SolarSystem getSolarSystem(String name) {
        if (name == null) {
            return getSolarSystems().get(0);
        }
        for (SolarSystem solarSystem : getSolarSystems()) {
            if (solarSystem.getName().equals(name)) {
                return solarSystem;
            }
        }
        return null;
    }

    /**
     * Gets market by its name
     * @param name name of planet containing market
     * @return market on planet
     */
    public Market getMarket(String name) {
        //Method chain to not duplicate for loop in getSolarSystem
        return Objects.requireNonNull(getSolarSystem(name)).getMarket();
    }

    /**
     * Saves current player and universe
     */
    public void save() {
        savePlayer(player, universe);
    }

    /**
     * Returns whether repo has been saved to before
     * @return whether player has been instantiated or not
     */
    public boolean isFirstTime() {
        return player == null;
    }

    /**
     * Saves player and universe instances for future instances
     * @param player current player
     * @param universe current universe
     */
    public void savePlayer(Player player, Universe universe) {
        this.player = player;
        this.universe = universe;
        savePlayerToDb(player, universe.getPlanets());
    }

    /**
     * Saves player data and planets to database
     * @param player current player
     * @param planets planets in current universe
     */
    private void savePlayerToDb(Player player, List<Planet> planets) {
        PlayerSave save = new PlayerSave();
        save.setCharacterName(player.getCharacterName());
        save.setCredits(player.getCredits());
        save.setDifficulty(player.getDifficultyOrdinal());
        save.setFuelCount(player.getFuel());
        save.setCurrentPlanetName(player.getCurrentPlanetName());
        save.setSpaceSpaceShip(player.getSpaceshipOrdinal());
        save.setSkills(DataConvertHelper.skillsToJson(player.getSkills()));
        save.setInventory(DataConvertHelper.inventoryToJson(player.getInventoryMap()));
        save.setPlanets(DataConvertHelper.planetsToJson(planets));
        playerDao.insert(save);
    }

    /**
     * Gets player data based on save input
     * @param save last save in game
     * @return player data for save
     */
    private Player getPlayerFromSave(PlayerSave save) {
        if (save == null) {
            return null;
        }
        Player player = new Player();
        player.setCharacterName(save.getCharacterName());
        player.setDifficulty(Difficulty.values()[save.getDifficulty()]);
        player.setCredits(save.getCredits());
        player.setCurrentPlanetName(save.getCurrentPlanetName());
        int fuel = save.getFuelCount();
        Spaceship spaceship = Spaceship.values()[save.getSpaceSpaceShip()];
        spaceship.setFuel(fuel);
        player.setSpaceship(spaceship);
        player.setSkills(DataConvertHelper.jsonToSkills(save.getSkills()));
        Inventory inventory = new Inventory(spaceship.getMaxCargo(),
                DataConvertHelper.jsonToInventory(save.getInventory()));
        player.setInventory(inventory);
        return player;
    }

    /**
     * Gets player difficulty multiplier
     * @return difficulty multiplier
     */
    public int getPlayerDifficultyMultiplier() {
        return player.getDifficultyMultiplier();
    }

    /**
     * Gets player skill level for a skill
     * @param type skill level requested
     * @return skill level of type
     */
    public int getPlayerSkill(SkillType type) {
        return player.getSkillsType(type);
    }

    /**
     * Gets player quantity of goods
     * @param narcotics good whose quantity is requested
     * @return quantity of specified good in player inventory
     */
    public int getPlayerGoodQuantity(Good narcotics) {
        return player.getQuantity(narcotics);
    }

    /**
     * Removes good from player inventory
     * @param narcotics good to be removed from inventory
     * @param narcNum quantity to be removed from inventory
     */
    public void getPlayerRemoveGood(Good narcotics, int narcNum) {
        player.removeGood(narcotics, narcNum);
    }

    /**
     * Gets player current planet
     * @return planet name that player is currently on
     */
    public String getCurrentPlanetName() {
        return player.getCurrentPlanetName();
    }

    /**
     * Sets player current planet name
     * @param name new name for current planet
     */
    public void setCurrentPlanetName(String name) {
        player.setCurrentPlanetName(name);
    }

    /**
     * Gets player fuel quantity
     * @return quantity of fuel in spaceship
     */
    public int getFuel() {
        return player.getFuel();
    }

    /**
     * Uses fuel in spaceship
     */
    public void useFuel() {
        player.useFuel();
    }

    /**
     * Gets current player credits
     * @return number of player credits
     */
    public int getPlayerCredits() {
        return player.getCredits();
    }

    /**
     * Removes player credits
     * @param removeCredits quantity of credits to remove
     */
    public void removePlayerCredits(int removeCredits) {
        player.removeCredits(removeCredits);
    }

    /**
     * Adds player credits
     * @param addCredits quantity of credits to add
     */
    public void addPlayerCredits(int addCredits) {
        player.addCredits(addCredits);
    }
}
