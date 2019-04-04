package neighbors.com.spacetrader.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface InventoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Inventory inventory);

    @Update
    void update(Inventory inventory);

    @Delete
    void delete(Inventory inventory);

    @Query("SELECT * FROM inventory_table")
    Inventory getInventory();
}
