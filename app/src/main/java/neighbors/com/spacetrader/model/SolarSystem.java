package neighbors.com.spacetrader.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SolarSystem {

    private final String name;
    private TechLevel techLevel;
    private List<Planet> planets;
    private Resources resource;
    private final Coord location;

    public SolarSystem(String name, Coord location) {
        this.name = name;
        this.location = location;
        this.techLevel = TechLevel.getRandom();
        this.resource = Resources.getRandom();
        planets = new ArrayList<>();
        planets.add(new Planet(name, techLevel, resource, location.x, location.y));
    }

    public SolarSystem(Planet planet) {
        this.name = planet.getName();
        this.location = planet.getLocation();
        this.techLevel = planet.getTechLevel();
        this.resource = planet.getResource();
        planets = new ArrayList<>();
        planets.add(planet);
    }

    public String getName() {
        return name;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    public TechLevel getSystemTechLevel() { return techLevel; }

    public Resources getResource() {
        return resource;
    }

    public void setResource(Resources resource) {
        this.resource = resource;
    }

    public List<Planet> getPlanets() {
        return Collections.unmodifiableList(planets);
    }

    public Coord getLocation() {
        return location;
    }

    public void addPlanet(Planet newPlanet) {
        planets.add(newPlanet);
    }

    @NotNull
    @Override
    public String toString() {
        return "SolarSystem{" +
                "systemName='" + name + '\'' +
                ", techLvl= " + techLevel.getTechname() +
                ", resources=" + resource.getName() +
                ", location=" + location.toString() +
                '}';
    }

    public String getTechLevelName() {
        return techLevel.getTechname();
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    public String getResourceName() {
        return resource.getName();
    }

    public Market getMarket() {
        //Java doesn't have extension functions
        return planets.get(0).getMarket();
    }

    public int getLocationX() {
        return location.x;
    }

    public int getLocationY() {
        return location.y;
    }

    public int getColor() {
        return techLevel.getColor();
    }
}
