package neighbors.com.spacetrader.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SolarSystemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SolarSystem system);

    @Delete
    void delete(SolarSystem system);

    @Update
    void update(SolarSystem system);

    @Query("SELECT * FROM solar_system_table")
    List<SolarSystem> getSolarSystem();
}
