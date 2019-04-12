package neighbors.com.spacetrader.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import neighbors.com.spacetrader.model.db.PlayerSave;

/**
 * Player DAO for db
 */
@Dao
public interface PlayerDao {

    /**
     * Inserts player into db
     * @param player the player to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PlayerSave player);

    /**
     * Updates player in the db
     * @param player the player to update
     */
    @Update
    void update(PlayerSave player);

    /**
     * Deletes player in the db
     * @param player the player to delete
     */
    @Delete
    void delete(PlayerSave player);

    /**
     * Gets the player from the db
     * @return the player
     */
    @Query("SELECT * FROM playersave")
    PlayerSave getPlayer();
}
