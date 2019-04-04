package neighbors.com.spacetrader.model;

public class Planet {

    private String planetName;
    private TechLevel techLevel;

    transient private Market market;

    public Planet(String planetName, TechLevel techLevel) {
        this.planetName = planetName;
        this.techLevel = techLevel;
        market = new Market(techLevel);
    }


    public String getName() {
        return planetName;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public Market getMarket() {
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
}
