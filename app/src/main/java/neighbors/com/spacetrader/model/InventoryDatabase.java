package neighbors.com.spacetrader.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public abstract class InventoryDatabase extends RoomDatabase {
    private static InventoryDatabase instance;

    public abstract InventoryDao inventoryDao();

    public static synchronized InventoryDatabase getInstance(Context context) {
        if (instance== null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    InventoryDatabase.class, "inventory_database")
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
        private InventoryDao inventoryDao;

        private PopulateDbAsyncTask(InventoryDatabase db) { inventoryDao = db.inventoryDao(); }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
