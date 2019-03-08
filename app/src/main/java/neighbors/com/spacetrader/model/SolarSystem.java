package neighbors.com.spacetrader.model;

import java.util.LinkedList;
import java.util.List;



public class SolarSystem {

    private String name;
    private TechLevel techLevel;
    private List<Planet> planets;
    private Resources resource;
    private Coord location;

    public SolarSystem(String name, Coord location) {
        this.name = name;
        this.location = location;
        this.techLevel = TechLevel.getRandom();
        this.resource = Resources.getRandom();
        List<Planet> passPlanets = new LinkedList<>();
        passPlanets.add(new Planet(name, techLevel));
    }


    public SolarSystem(String name, TechLevel techLvl, Resources resource, LinkedList<Planet> planets, Coord location) {
        this.name = name;
        this.techLevel = techLvl;
        this.resource = resource;
        this.planets = planets;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public TechLevel getSystemTechLevel() { return techLevel; }

    public Resources getResource() {
        return resource;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public Coord getLocation() {
        return location;
    }

    public void addPlanet(Planet newPlanet) {
        planets.add(newPlanet);
    }

    @Override
    public String toString() {
        return "SolarSystem{" +
                "systemName='" + name + '\'' +
                ", techLvl= " + techLevel.getName() +
                ", resources=" + resource.getName() +
                ", location=" + location.toString() +
                '}';
    }
}
