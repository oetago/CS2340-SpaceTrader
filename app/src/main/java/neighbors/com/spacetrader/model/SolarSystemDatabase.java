package neighbors.com.spacetrader.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {SolarSystem.class}, version = 1)
public abstract class SolarSystemDatabase extends RoomDatabase {

    private static SolarSystemDatabase instance;

    public abstract SolarSystemDao solarSystemDao();

    public static synchronized SolarSystemDatabase getInstance(Context context) {
        if (instance== null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SolarSystemDatabase.class, "player_database")
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
        private SolarSystemDao solarSystemDao;

        private PopulateDbAsyncTask(SolarSystemDatabase db) {  solarSystemDao = db.solarSystemDao(); }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
