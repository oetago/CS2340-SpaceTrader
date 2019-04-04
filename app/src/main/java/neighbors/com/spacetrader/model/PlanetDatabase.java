package neighbors.com.spacetrader.model;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Planet.class}, version = 1)
public abstract class PlanetDatabase extends RoomDatabase {

    private static PlanetDatabase instance;

    public abstract PlanetDao planetDao();

    public static synchronized PlanetDatabase getInstance(Context context) {
        if (instance== null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PlanetDatabase.class, "player_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PlanetDao planetDao;

        private PopulateDbAsyncTask(PlanetDatabase db) { planetDao = db.planetDao(); }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
