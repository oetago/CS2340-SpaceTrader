package neighbors.com.spacetrader.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = SolarSystem.class,
                    parentColumns = "name",
                    childColumns = "planetName",
                    onDelete = ForeignKey.CASCADE),
        tableName = "planet_table")
public class Planet {

    @PrimaryKey
    private String planetName;
    @Embedded
    private TechLevel planetTechLevel;
    @Ignore
    private Market market;

    public Planet(String name, TechLevel techLevel) {
        this.planetName = name;
        this.planetTechLevel = techLevel;
        market = new Market(techLevel);
    }


    public String getName() {
        return planetName;
    }

    public TechLevel getPlanetTechLevel() {
        return planetTechLevel;
    }

    public Market getMarket() {
        return market;
    }

    public void setName(String name) {
        this.planetName = name;
    }


}
