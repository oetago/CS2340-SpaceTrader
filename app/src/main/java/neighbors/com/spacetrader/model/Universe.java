package neighbors.com.spacetrader.model;
import java.util.LinkedList;
import java.util.List;

public class Universe {

    private List<SolarSystem> solarSystems;

    public Universe() {
        this(new LinkedList<SolarSystem>());
    }

    public Universe(List<SolarSystem> solarSystems) {
        this.solarSystems = solarSystems;
    }

    public void addSolarSystem(SolarSystem newSolarSystem) {
        solarSystems.add(newSolarSystem);
    }

    public List<SolarSystem> getSolarSystems() {
        return solarSystems;
    }
}
