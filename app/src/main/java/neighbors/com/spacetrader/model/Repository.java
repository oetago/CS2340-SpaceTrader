package neighbors.com.spacetrader.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import neighbors.com.spacetrader.model.db.DataConvertHelper;
import neighbors.com.spacetrader.model.db.PlayerSave;


public class Repository {

    volatile private static Repository repo;

    private PlayerDao playerDao;
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

    public static Repository getInstance(Context context) {
        if (repo == null) {
            repo = new Repository(context);
        }
        return repo;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Planet getPlanet() {
        return getPlanet(player.getCurrentPlanetName());
    }

    private Planet getPlanet(String currentPlanetName) {
        for (Planet planet : universe.getPlanets()) {
            if (planet.getName().equals(currentPlanetName)) {
                return planet;
            }
        }
        return null;
    }

    public Spaceship getSpaceship() {
        return player.getSpaceship();
    }

    public Inventory getInventory() {
        return player.getInventory();
    }

    public Universe getUniverse() {
        return universe;
    }

    public List<SolarSystem> getSolarSystems() {
        return universe.getSolarSystems();
    }

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

    public Market getMarket(String name) {
        return getSolarSystem(name).getMarket();
    }

    public void save() {
        savePlayer(player, universe);
    }

    public boolean isFirstTime() {
        return player == null;
    }

    public void savePlayer(Player player, Universe universe) {
        this.player = player;
        this.universe = universe;
        savePlayerToDb(player, universe.getPlanets());
    }

    private void savePlayerToDb(Player player, List<Planet> planets) {
        PlayerSave save = new PlayerSave();
        save.setCharacterName(player.getCharacterName());
        save.setCredits(player.getCredits());
        save.setDifficulty(player.getDifficulty().ordinal());
        save.setFuelCount(player.getFuel());
        save.setCurrentPlanetName(player.getCurrentPlanetName());
        save.setSpaceSpaceShip(player.getSpaceship().ordinal());
        save.setSkills(DataConvertHelper.skillsToJson(player.getSkills()));
        save.setInventory(DataConvertHelper.inventoryToJson(player.getInventory().getInventory()));
        save.setPlanets(DataConvertHelper.planetsToJson(planets));
        playerDao.insert(save);
    }

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
        Inventory inventory = new Inventory(spaceship.getMaxCargo(), DataConvertHelper.jsonToInventory(save.getInventory()));
        player.setInventory(inventory);
        return player;
    }

    public void savePlanets(List<Planet> planets) {
//        for (Planet planet : planets) {
//            insert(planet);
//        }
    }
}
