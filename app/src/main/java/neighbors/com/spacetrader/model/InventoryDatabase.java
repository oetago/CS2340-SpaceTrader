package neighbors.com.spacetrader.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Inventory.class}, version = 1, exportSchema = false)
@TypeConverters(DataConverters.class)
public abstract class InventoryDatabase extends RoomDatabase {
    private static InventoryDatabase instance;

    public abstract InventoryDao inventoryDao();

    public static InventoryDatabase getInstance(Context context) {
        if (instance== null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    InventoryDatabase.class, "inventory_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
