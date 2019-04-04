package neighbors.com.spacetrader.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SolarSystemDao {
    @Insert
    void insert(SolarSystem system);

    @Delete
    void delete(SolarSystem system);

    @Update
    void update(SolarSystem system);

    @Query("SELECT * FROM solar_system_table")
    SolarSystem getSolarSystem();
}
