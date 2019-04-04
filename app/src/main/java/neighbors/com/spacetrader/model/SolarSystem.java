package neighbors.com.spacetrader.model;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "solar_system_table")
public class SolarSystem {

    @PrimaryKey
    private String name;
    @Embedded
    private TechLevel techLevel;
    @Ignore
    private List<Planet> planets;
    @Embedded
    private Resources resource;
    @Embedded
    private Coord location;

    public SolarSystem(String name, Coord location) {
        this.name = name;
        this.location = location;
        this.techLevel = TechLevel.getRandom();
        this.resource = Resources.getRandom();
        planets = new ArrayList<>();
        planets.add(new Planet(name, techLevel));
    }


    public SolarSystem(String name, TechLevel techLvl, Resources resource, List<Planet> planets, Coord location) {
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

    public String getSystemTechLevelName() {
        return techLevel.getName();
    }

    public String getResourceName() {
        return resource.getName();
    }

    public Market getMarket() {
        return planets.get(0).getMarket();
    }
}
