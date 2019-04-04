package neighbors.com.spacetrader.model;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "planet_table")
public class Planet {

    @PrimaryKey
    @NonNull
    private String planetName;

    @TypeConverters(DataConverters.class)
    private TechLevel techLevel;

    @TypeConverters(DataConverters.class)
    private Resources resource;

    @TypeConverters(DataConverters.class)
    private Coord location;

    transient private Market market;

    public Planet(@NotNull String planetName, TechLevel techLevel) {
        this.planetName = planetName;
        this.techLevel = techLevel;
        market = new Market(techLevel);
    }


    public String getName() {
        return planetName;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public Market getMarket() {
        return market;
    }

    public void setName(String name) {
        this.planetName = name;
    }

    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(@NonNull String planetName) {
        this.planetName = planetName;
    }

    public Resources getResource() {
        return resource;
    }

    public void setResource(Resources resource) {
        this.resource = resource;
    }

    public Coord getLocation() {
        return location;
    }

    public void setLocation(Coord location) {
        this.location = location;
    }

    public void setMarket(Market market) {
        this.market = market;
    }
}
