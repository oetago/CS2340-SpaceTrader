package neighbors.com.spacetrader.model;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import neighbors.com.spacetrader.model.db.PlayerSave;

/**
 * Database to hold player data
 */
@Database(entities = {PlayerSave.class}, version = 2, exportSchema = false)
public abstract class PlayerDatabase extends RoomDatabase {

    private static PlayerDatabase instance;

    /**
     * Abstract method for creating a player data access object
     * @return data access object for a given player
     */
    public abstract PlayerDao playerDao();

    /**
     * Gets player data from database
     * @param context the game the player is in
     * @return the player data as an instance
     */
    public static PlayerDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PlayerDatabase.class, "player_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
