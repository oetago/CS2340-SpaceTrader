package neighbors.com.spacetrader.model;


public class Planet {
    private String name;
    private TechLevel planetTechLevel;
    private Market market;

    public Planet(String name, TechLevel techLevel) {
        this.name = name;
        this.planetTechLevel = techLevel;
        market = new Market(techLevel);
    }


    public String getName() {
        return name;
    }

    public TechLevel getPlanetTechLevel() {
        return planetTechLevel;
    }

    public Market getMarket() {
        return market;
    }

    public void setName(String name) {
        this.name = name;
    }


}
