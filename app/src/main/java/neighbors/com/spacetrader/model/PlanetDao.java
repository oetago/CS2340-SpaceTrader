package neighbors.com.spacetrader.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PlanetDao {

    @Insert
    void insert(Planet planet);

    @Update
    void update(Planet planet);

    @Delete
    void delete(Planet planet);

    @Query("SELECT * FROM planet_table")
    List<Planet> getAllPlanets();

    @Query("SELECT * FROM planet_table WHERE planetName=:name")
    Planet getPlanetByName(String name);


}
