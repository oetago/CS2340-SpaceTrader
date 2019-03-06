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
