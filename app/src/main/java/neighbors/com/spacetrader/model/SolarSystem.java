package neighbors.com.spacetrader.model;

import java.util.LinkedList;
import java.util.List;



public class SolarSystem {


    private String name;
    private TechLevel techLvl;
    private List<Planet> planets;
    private Resources resource;
    private Coord location;

    public SolarSystem(String name, Coord location) {
        this.name = name;
        this.location = location;
        LinkedList<Planet> passPlanets = new LinkedList<>();
        passPlanets.add(new Planet(name));
        this.techLvl = TechLevel.getRandom(); //TODO make Techlvl and enum with Color See UniverseActivity getColor()
        this.resource = Resources.getRandom();

    }


    public SolarSystem(String name, TechLevel techLvl, Resources resource, LinkedList<Planet> planets, Coord location) {
        this.name = name;
        this.techLvl = techLvl;
        this.resource = resource;
        this.planets = planets;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public TechLevel getTechLvl() {
        return techLvl;
    }

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
                ", techLvl= " + techLvl.getName() +
                ", resources=" + resource.getName() +
                ", location=" + location.toString() +
                '}';
    }
}
