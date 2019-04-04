package neighbors.com.spacetrader.model;

import android.app.Application;

public class Repository {

    private PlayerDao playerDao;
    private Player player;

    private PlanetDao planetDao;
    private SolarSystemDao solarSystemDao;
    private SpaceshipDao spaceshipDao;
    private InventoryDao inventoryDao;

    public Repository(Application application) {
        PlayerDatabase db = PlayerDatabase.getInstance(application);
        playerDao = db.playerDao();
        player = playerDao.getPlayer();

        PlanetDatabase db1 = PlanetDatabase.getInstance(application);
        planetDao = db1.planetDao();
        player.setCurrentPlanet(planetDao.getAllPlanets().get(0));

        SolarSystemDatabase db2 = SolarSystemDatabase.getInstance(application);
        solarSystemDao = db2.solarSystemDao();
        player.setCurrentSystem(solarSystemDao.getSolarSystem());

        SpaceshipDatabase db3 = SpaceshipDatabase.getInstance(application);
        spaceshipDao = db3.spaceshipDao();
        player.setSpaceship(spaceshipDao.getSpaceship());

        InventoryDatabase db4 = InventoryDatabase.getInstance(application);
        inventoryDao = db4.inventoryDao();
        player.setInventory(inventoryDao.getInventory());

    }

    public void insert(Player player) { playerDao.insert(player);}
    public void insert(Planet planet) { planetDao.insert(planet);}
    public void insert(SolarSystem system) { solarSystemDao.insert(system);}
    public void insert(Spaceship spaceship) { spaceshipDao.insert(spaceship);}
    public void insert(Inventory inventory) { inventoryDao.insert(inventory);}

    public void update(Player player) { playerDao.update(player);}
    public void update(Planet planet) { planetDao.update(planet);}
    public void update(SolarSystem system) { solarSystemDao.update(system);}
    public void update(Spaceship spaceship) { spaceshipDao.update(spaceship);}
    public void update(Inventory inventory) { inventoryDao.update(inventory); }

    public void delete(Player player) { playerDao.delete(player);}
    public void delete(Planet planet) { planetDao.delete(planet);}
    public void delete(SolarSystem system) { solarSystemDao.delete(system);}
    public void delete(Spaceship spaceship) { spaceshipDao.delete(spaceship);}
    public void delete(Inventory inventory) { inventoryDao.delete(inventory);}

    public Player getPlayer() { return this.player; }
    public Planet getPlanet() { return player.getCurrentPlanet(); }
    public SolarSystem getSolarSystem() { return player.getCurrentSystem(); }
    public Spaceship getSpaceship() { return player.getSpaceship(); }
    public Inventory getInventory() { return player.getInventory(); }



//    private static Repository repo;
//
//    private Universe universe;
//    private Player player;
//
//    public static Repository getInstance() {
//        if (repo == null){
//            repo = new Repository();
//        }
//        return repo;
//    }
//
//    private Repository() {
//        universe = new Universe();
//        // Awkward initialization of player's system and planet bc player is initialized before universe.
//        // Player should be initialized, but just to be safe.
//        if (player != null) {
//            // Sets planet to first planet of first solar system in universe.
//            player.setCurrentSystem(universe.getSolarSystems().get(0));
//        }
//    }
//
//    public Universe getUniverse() {
//        return universe;
//    }
//
//    public void setUniverse(Universe universe) {
//        this.universe = universe;
//    }
//
//    public Player getPlayer() {
//        return player;
//    }
//
//    public void setPlayer(Player player) {
//        this.player = player;
//    }
//
//    public List<SolarSystem> getSolarSystems() {
//        return universe.getSolarSystems();
//    }
//
//    public SolarSystem getSolarSystem(String name) {
//        if (name == null) {
//            return getSolarSystems().get(0);
//        }
//        for (SolarSystem solarSystem : getSolarSystems()) {
//            if (solarSystem.getName().equals(name)) {
//                return solarSystem;
//            }
//        }
//        return null;
//    }
//
//    public Market getMarket(String name) {
//        return getSolarSystem(name).getMarket();
//    }
}
