package neighbors.com.spacetrader.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Spaceship.class}, version = 1)
public abstract class SpaceshipDatabase extends RoomDatabase {
    private static SpaceshipDatabase instance;

    public abstract SpaceshipDao spaceshipDao();

    public static synchronized SpaceshipDatabase getInstance(Context context) {
        if (instance== null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SpaceshipDatabase.class, "spaceship_database")
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
        private SpaceshipDao spaceshipDao;

        private PopulateDbAsyncTask(SpaceshipDatabase db) { spaceshipDao = db.spaceshipDao(); }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
