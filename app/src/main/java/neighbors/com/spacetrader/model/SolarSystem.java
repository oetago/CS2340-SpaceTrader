package neighbors.com.spacetrader.model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;


public class SolarSystem {

    private ArrayList<String> techList = new ArrayList<String>(Arrays.asList("Pre-Agriculture",
            "Agriculture", "Medieval", "Renaissance", "Early Industrial", "Industrial",
            "Post-Industrial", "Hi-Tech"));
    private String name;
    private String techLvl;
    private LinkedList<Planet> planets;
    private Resources resource;
    private Coord location;

    public SolarSystem(String name, String techLvl, Resources resource, LinkedList<Planet> planets, Coord location) {
        this.name = name;
        this.techLvl = techLvl;
        this.resource = resource;
        this.planets = planets;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    class Coord {
        int x;
        int y;

        public boolean equals(Object o) {
            Coord c = (Coord) o;
            return c.x == x && c.y == y;
        }

        public Coord(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }

    public String getTechLvl() {
        return techLvl;
    }

    public Resources getResource() {
        return resource;
    }

    public LinkedList<Planet> getPlanets() {
        return planets;
    }

    public Coord getLocation() {
        return location;
    }

    public void addPlanet(Planet newPlanet) {
        planets.add(newPlanet);
    }
}
