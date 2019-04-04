package neighbors.com.spacetrader.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Planet.class}, version = 2, exportSchema = false)
public abstract class PlanetDatabase extends RoomDatabase {

    private static PlanetDatabase instance;

    public static PlanetDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PlanetDatabase.class, "player_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract PlanetDao planetDao();
}
