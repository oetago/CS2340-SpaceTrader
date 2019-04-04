package neighbors.com.spacetrader.model;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SpaceshipDao {
    @Insert
    void insert(Spaceship ship);

    @Update
    void update(Spaceship ship);

    @Delete
    void delete(Spaceship ship);

    @Query("SELECT * FROM spaceship_table")
    Spaceship getSpaceship();
}
