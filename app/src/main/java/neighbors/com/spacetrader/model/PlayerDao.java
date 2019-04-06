package neighbors.com.spacetrader.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import neighbors.com.spacetrader.model.db.PlayerSave;

@Dao
public interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PlayerSave player);

    @Update
    void update(PlayerSave player);

    @Delete
    void delete(PlayerSave player);

    @Query("SELECT * FROM playersave")
    PlayerSave getPlayer();
}
