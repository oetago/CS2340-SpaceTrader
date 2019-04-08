package neighbors.com.spacetrader.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class Universe {

    private final List<SolarSystem> solarSystems;


    public Universe() {
        this(new LinkedList<SolarSystem>());
        Set<Coord> solarSystemLocations = new HashSet<>();
        Set<String> solarSystemNames = new HashSet<>();
        Random rand = new Random();
        int UNIVERSESIZE = 10;
        for (int i = 0; i < UNIVERSESIZE; i++) {
            boolean uniqueCoord = false;
            boolean uniqueName = false;
            Coord coordinate = new Coord(0, 0);
            String solarName = null;
            // random choosing of Coord
            while (!uniqueCoord) {
                Coord location = new Coord(rand.nextInt(151), rand.nextInt(101));
                if (solarSystemLocations.contains(location)) {
                    uniqueCoord = false;
                } else {
                    uniqueCoord = true;
                    solarSystemLocations.add(location);
                    coordinate = location;
                }
            }
            // random choosing of name of SolarSystem
            while (!uniqueName) {
                ArrayList<String> solarNames = new ArrayList<>(Arrays.asList("Aldea", "Andevian",
                        "Antedi", "Balosnee", "Baratas", "Daled", "Damast",
                        "Davlos", "Deneb", "Deneva", "Devidia",
                        "Draylon", "Drema", "Endor", "Quator",
                        "Rakhar", "Ran", "Regulas", "Relva",
                        "Rhymus", "Rochani", "Rubicum", "Rutia", "Sarpeidon",
                        "Sefalla", "Seltrice", "Sigma", "Sol",
                        "Somari", "Stakoron", "Styris", "Talani",
                        "Tamus", "Tantalos", "Tanuga", "Tarchannen",
                        "Terosa", "Thera", "Titan", "Torin",
                        "Triacus", "Turkana", "Tyrus", "Umberlee",
                        "Utopia", "Vadera", "Vagra", "Vandor",
                        "Ventax", "Xenon", "Xerxes", "Yew", "Yojimbo",
                        "Zalkon", "Zuul"));
                String name = solarNames.get(rand.nextInt(solarNames.size()));
                if (solarSystemNames.contains(name)) {
                    uniqueName = false;
                } else {
                    uniqueName = true;
                    solarSystemNames.add(name);
                    solarName = name;
                }

            }
            solarSystems.add(new SolarSystem(solarName, coordinate));
        }
    }


    public Universe(List<SolarSystem> solarSystems) {
        this.solarSystems = solarSystems;
    }

    public void addSolarSystem(SolarSystem newSolarSystem) {
        solarSystems.add(newSolarSystem);
    }

    public List<SolarSystem> getSolarSystems() {
        return Collections.unmodifiableList(solarSystems);
    }

    @Override
    public String toString() {
        String toReturn = "";
        for (SolarSystem system: solarSystems) {
            toReturn += system.toString() + "\'";
        }
        return toReturn;
    }

    public List<Planet> getPlanets() {
        List<Planet> planets = new ArrayList<>();
        for (SolarSystem solarSystem : solarSystems) {
            planets.addAll(solarSystem.getPlanets());
        }
        return planets;
    }
}
