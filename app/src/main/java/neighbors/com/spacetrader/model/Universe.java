package neighbors.com.spacetrader.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Universe model
 */
public class Universe {

    private final List<SolarSystem> solarSystems;

    /**
     * Instantiates new universe
     */
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

    /**
     * Instantiates universe with predefined solar systems
     * @param solarSystems solar systems with which to populate the universe
     */
    public Universe(List<SolarSystem> solarSystems) {
        this.solarSystems = solarSystems;
    }

    /**
     * Adds solar system to universe
     * @param newSolarSystem solar system to be added
     */
    public void addSolarSystem(SolarSystem newSolarSystem) {
        solarSystems.add(newSolarSystem);
    }

    /**
     * Gets solar systems in universe
     * @return list of solar systems in universe
     */
    public List<SolarSystem> getSolarSystems() {
        return Collections.unmodifiableList(solarSystems);
    }

    /**
     * Represents universe as string
     * @return universe as a string
     */
    @NotNull
    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (SolarSystem system: solarSystems) {
            toReturn.append(system.toString()).append("\'");
        }
        return toReturn.toString();
    }

    /**
     * Gets planets in universe
     * @return list of planets in universe
     */
    public List<Planet> getPlanets() {
        List<Planet> planets = new ArrayList<>();
        for (SolarSystem solarSystem : solarSystems) {
            planets.addAll(solarSystem.getPlanets());
        }
        return planets;
    }
}
