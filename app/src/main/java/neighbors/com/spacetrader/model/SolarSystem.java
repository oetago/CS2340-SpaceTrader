package neighbors.com.spacetrader.model;
import java.util.Arrays;
import java.util.ArrayList;

public class SolarSystem {

    private ArrayList<String> techList = new ArrayList<String>(Arrays.asList("Pre-Agriculture",
            "Agriculture", "Medieval", "Renaissance", "Early Industrial", "Industrial",
            "Post-Industrial", "Hi-Tech"));
    private String name;
    private String techLvl;
    private ArrayList<Planet> planets;
    private Resources resource;
    private int x;
    private int y;


    public SolarSystem(String name, String techLvl, Resources resource, ArrayList<Planet> planets, int x, int y) {
        this.name = name;
        this.techLvl = techLvl;
        this.resource = resource;
        this.planets = planets;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public String getTechLvl() {
        return techLvl;
    }

    public Resources getResource() {
        return resource;
    }

    public ArrayList<Planet> getPlanets() {
        return planets;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addPlanet(Planet newPlanet) {
        planets.add(newPlanet);
    }
}
