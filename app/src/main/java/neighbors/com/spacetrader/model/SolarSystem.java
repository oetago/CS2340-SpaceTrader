package neighbors.com.spacetrader.model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.List;


public class SolarSystem {

    private ArrayList<String> techList = new ArrayList<String>(Arrays.asList("Pre-Agriculture",
            "Agriculture", "Medieval", "Renaissance", "Early Industrial", "Industrial",
            "Post-Industrial", "Hi-Tech"));
    private String name;
    private String techLvl;
    private List<Planet> planets;
    private Resources resource;
    private Coord location;

    public SolarSystem(String name, Coord location) {
        this.name = name;
        this.location = location;
        LinkedList<Planet> passPlanets = new LinkedList<>();
        passPlanets.add(new Planet(name));
        Random rand = new Random();
        int techInt = rand.nextInt(8);
        this.techLvl = techList.get(techInt);
        int resInt = rand.nextInt(100);
        if (resInt >= 97) {
            resource = Resources.WARLIKE;
        } else if (resInt >= 94) {
            resource = Resources.ARTISTIC;
        } else if (resInt >= 90) {
            resource = Resources.LOTSOFHERBS;
        } else if (resInt >= 86) {
            resource = Resources.WEIRDMUSHROOMS;
        } else if (resInt >= 82) {
            resource = Resources.LIFELESS;
        } else if (resInt >= 77) {
            resource = Resources.RICHFAUNA;
        } else if (resInt >= 71) {
            resource = Resources.POORSOIL;
        } else if (resInt >= 63) {
            resource = Resources.RICHSOIL;
        } else if (resInt >= 55) {
            resource = Resources.LOTSOFWATER;
        } else if (resInt >= 46) {
            resource = Resources.DESERT;
        } else if (resInt >= 33) {
            resource = Resources.MINERALPOOR;
        } else if (resInt >= 20) {
            resource = Resources.MINERALRICH;
        } else {
            resource = Resources.NOSPECIALRESOURCES;
        }
    }


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

    public String getTechLvl() {
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
                ", techLvl=" + techList.lastIndexOf(techLvl) + ": " + techLvl +
                ", resources=" + resource.getName() +
                ", location=" + location.toString() +
                '}';
    }
}
