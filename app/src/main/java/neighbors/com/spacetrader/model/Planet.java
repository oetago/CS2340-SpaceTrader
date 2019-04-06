package neighbors.com.spacetrader.model;

import androidx.annotation.NonNull;

public class Planet {
    private String planetName;

    private TechLevel techLevel;

    private Resources resource;

    private Coord location;

    private int x;
    private int y;

    transient private Market market;

    public Planet(String planetName, TechLevel techLevel, Resources resource, int x, int y) {
        this.planetName = planetName;
        this.techLevel = techLevel;
        this.resource = resource;
        location = new Coord(x, y);
    }

    public String getName() {
        return planetName;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

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
