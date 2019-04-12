package neighbors.com.spacetrader.model;

import androidx.annotation.NonNull;

public class Planet {
    private String planetName;

    private TechLevel techLevel;

    private Resources resource;

    private Coord location;

    private int x;
    private int y;

    private transient Market market;

    /**
     * Creates instance of Planet object
     * @param planetName name of planet
     * @param techLevel technology level of planet
     * @param resource special resources on planet
     * @param x x coordinate of planet in universe
     * @param y y coordinate of planet in universe
     */
    public Planet(String planetName, TechLevel techLevel, Resources resource, int x, int y) {
        this.planetName = planetName;
        this.techLevel = techLevel;
        this.resource = resource;
        location = new Coord(x, y);
    }

    /**
     * Gets name of planet
     * @return name of planet
     */
    public String getName() {
        return planetName;
    }

    /**
     * Gets x coordinate of planet
     * @return x coordinate of planet
     */
    public int getX() {
        return x;
    }

    /**
     * Sets x coordinate of planet
     * @param x new x coordinate of planet
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets y coordinate of planet
     * @return y coordinate of planet
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y coordinate of planet
     * @param y new y coordinate of planet
     */
    public void setY(int y) {
        this.y = y;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public Market getMarket() {
        if (market == null) {
            this.market = new Market(techLevel);
        }
        return market;
    }

    public void setName(String name) {
        this.planetName = name;
    }

    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(@NonNull String planetName) {
        this.planetName = planetName;
    }

    public Resources getResource() {
        return resource;
    }

    public void setResource(Resources resource) {
        this.resource = resource;
    }

    public Coord getLocation() {
        return location;
    }

    public void setLocation(Coord location) {
        this.location = location;
    }

    public void setMarket(Market market) {
        this.market = market;
    }
}
