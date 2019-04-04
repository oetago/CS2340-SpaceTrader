package neighbors.com.spacetrader.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {SolarSystem.class}, version = 1, exportSchema = false)
public abstract class SolarSystemDatabase extends RoomDatabase {

    private static SolarSystemDatabase instance;

    public abstract SolarSystemDao solarSystemDao();

    public static synchronized SolarSystemDatabase getInstance(Context context) {
        if (instance== null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SolarSystemDatabase.class, "player_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
