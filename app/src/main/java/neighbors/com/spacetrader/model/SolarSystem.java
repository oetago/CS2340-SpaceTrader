package neighbors.com.spacetrader.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Solar system model
 */
public class SolarSystem {

    private final String name;
    private TechLevel techLevel;
    private List<Planet> planets;
    private Resources resource;
    private final Coord location;

    /**
     * Creates solar system instance
     * @param name name of solar system
     * @param location coordinates of solar system
     */
    public SolarSystem(String name, Coord location) {
        this.name = name;
        this.location = location;
        this.techLevel = TechLevel.getRandom();
        this.resource = Resources.getRandom();
        planets = new ArrayList<>();
        planets.add(new Planet(name, techLevel, resource, location.x, location.y));
    }

    /**
     * Creates solar system instance
     * @param planet planet to be in solar system
     */
    public SolarSystem(Planet planet) {
        this.name = planet.getName();
        this.location = planet.getLocation();
        this.techLevel = planet.getTechLevel();
        this.resource = planet.getResource();
        planets = new ArrayList<>();
        planets.add(planet);
    }

    /**
     * Gets name of solar system
     * @return name of solar system
     */
    public String getName() {
        return name;
    }

    /**
     * Sets planets in solar system
     * @param planets list of planets in solar system
     */
    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    /**
     * Gets tech level of solar system
     * @return tech level of solar system
     */
    public TechLevel getSystemTechLevel() { return techLevel; }

    /**
     * Gets resource modifier in solar system
     * @return resource modifier in solar system
     */
    public Resources getResource() {
        return resource;
    }

    /**
     * Sets resource modifier in solar system
     * @param resource new resource modifier
     */
    public void setResource(Resources resource) {
        this.resource = resource;
    }

    /**
     * Gets list of planets in solar system
     * @return list of planets in solar system
     */
    public List<Planet> getPlanets() {
        return Collections.unmodifiableList(planets);
    }

    /**
     * Gets solar system location
     * @return coordinates of solar system
     */
    public Coord getLocation() {
        return location;
    }

    /**
     * Adds planet to solar system
     * @param newPlanet planet to be added to solar system
     */
    public void addPlanet(Planet newPlanet) {
        planets.add(newPlanet);
    }

    /**
     * Converts solar system to string
     * @return solar system as string
     */
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

    /**
     * Gets tech level as a string
     * @return name of tech level
     */
    public String getTechLevelName() {
        return techLevel.getTechname();
    }

    /**
     * Gets tech level as a number
     * @return number representing tech level
     */
    public TechLevel getTechLevel() {
        return techLevel;
    }

    /**
     * Sets tech level
     * @param techLevel new solar system tech level
     */
    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    /**
     * Gets resource modifier
     * @return solar system resource modifier
     */
    public String getResourceName() {
        return resource.getName();
    }

    /**
     * Gets market on first planet in system
     * @return market in solar system
     */
    public Market getMarket() {
        //Java doesn't have extension functions
        return planets.get(0).getMarket();
    }

    /**
     * Gets x coordinate of solar system
     * @return x coordinate of solar system
     */
    public int getLocationX() {
        return location.x;
    }

    /**
     * Gets y coordinate of solar system
     * @return y coordinate of solar system
     */
    public int getLocationY() {
        return location.y;
    }

    /**
     * Gets tech level color
     * @return tech level color
     */
    public int getColor() {
        return techLevel.getColor();
    }
}
