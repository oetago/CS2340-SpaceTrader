package neighbors.com.spacetrader.model;

public class Repository {
    private static Repository repo;

    private Universe universe;
    private Player player;

    public static Repository getInstance() {
        if (repo == null){
            repo = new Repository();
        }
        return repo;
    }

    private Repository() {
        universe = new Universe();
        // Awkward initialization of player's system and planet bc player is initialized before universe.
        // Player should be initialized, but just to be safe.
        if (player != null) {
            // Sets planet to first planet of first solar system in universe.
            player.setCurrentSystem(universe.getSolarSystems().get(0));
        }
    }

    public Universe getUniverse() {
        return universe;
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
