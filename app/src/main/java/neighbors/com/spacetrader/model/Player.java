package neighbors.com.spacetrader.model;

/**
 * Player Model to hold data throughout the game
 */
public class Player {
    private String name;
    private int pilotPoints;
    private int fighterPoints;
    private int engineerPoints;
    private int traderPoints;

    public Player() {
        this("", 0, 0, 0,0);
    }

    public Player(String pName, int pPoints, int fPoints, int tPoints, int ePoints) {
        name = pName;
        pilotPoints = pPoints;

    }
}
