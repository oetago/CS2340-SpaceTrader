package neighbors.com.spacetrader.model;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;


@Entity(tableName = "solar_system_table")
public class SolarSystem {

    @PrimaryKey
    @NonNull
    private String name;
    @TypeConverters(DataConverters.class)
    private TechLevel techLevel;

    @TypeConverters(DataConverters.class)
    private List<Planet> planets;

    @TypeConverters(DataConverters.class)
    private Resources resource;

    @TypeConverters(DataConverters.class)
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
        return planets.get(0).getMarket();
    }
}
