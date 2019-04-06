package neighbors.com.spacetrader.model;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import neighbors.com.spacetrader.model.db.PlayerSave;

@Database(entities = {PlayerSave.class}, version = 1, exportSchema = false)
public abstract class PlayerDatabase extends RoomDatabase {

    private static PlayerDatabase instance;

    public abstract PlayerDao playerDao();

    public static PlayerDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PlayerDatabase.class, "player_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
