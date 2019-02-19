package neighbors.com.spacetrader.model;
import java.util.ArrayList;

public class Universe {

    private ArrayList<SolarSystem> solarSystems;

    public Universe() {
        this(new ArrayList<SolarSystem>());
    }

    public Universe(ArrayList<SolarSystem> solarSystems) {
        this.solarSystems = solarSystems;
    }

    public void addSolarSystem(SolarSystem newSolarSystem) {
        solarSystems.add(newSolarSystem);
    }

    public ArrayList<SolarSystem> getSolarSystems() {
        return solarSystems;
    }
}
