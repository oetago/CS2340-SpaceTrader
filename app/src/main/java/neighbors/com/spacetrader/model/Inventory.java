package neighbors.com.spacetrader.model;

import java.util.HashMap;
import java.util.Map;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "inventory_table")
public class Inventory {

    @PrimaryKey
    private int id;

    private int maxInventory;
    private int currentInventory;
    @TypeConverters(DataConverters.class)
    private Map<Good, Integer> inventory;

    public Inventory(int maxInventory) {
        this.maxInventory = maxInventory;
        currentInventory = 0;
        inventory = new HashMap<>();
        id = 1;
    }

    public Map<Good, Integer> getInventory() { return this.inventory; }
    /**
     * Checks if inventory has less than the quantity provided
     *
     * @param good     The good to check
     * @param quantity The quantity to check with
     * @return true if has >= quantity; false if has < quantity;
     */
    public boolean hasEnoughOfGood(Good good, int quantity) {
        if (inventory.containsKey(good)) {
            int currentQuantity = inventory.get(good);
            return quantity <= currentQuantity;
        }
        return false;
    }

    /**
     * Checks if inventory has enough space for quantity provided
     *
     * @param quantity The quantity to check with
     * @return true if  (currentInventory + quantity) <= maxInventory false otherwise
     */
    public boolean hasEnoughSpaceForGood(Integer quantity) {
        return (currentInventory + quantity) <= maxInventory;
    }

    public int getMaxInventory() {
        return maxInventory;
    }

    public int getCurrentInventory() {
        return currentInventory;
    }

    /**
     * Removes goods from inventory
     *
     * @param good     the Good to remove
     * @param quantity the quantity to remove
     */
    public void remove(Good good, int quantity) {
        if (inventory.containsKey(good)) {
            int goodCount = inventory.get(good);
            inventory.put(good, goodCount - quantity);
            currentInventory -= quantity;
        }
    }

    public int getQuantity(Good good) {
        if (inventory.containsKey(good)) {
            return inventory.get(good);
        }
        return 0;
    }

    public void add(Good good, Integer quantity) {
        int goodCount = 0;
        if (inventory.containsKey(good)) {
            goodCount = inventory.get(good);
        }
        inventory.put(good, goodCount + quantity);
        currentInventory += quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrentInventory(int currentInventory) {
        this.currentInventory = currentInventory;
    }

    public void setInventory(Map<Good, Integer> inventory) {
        this.inventory = inventory;
    }

    public void setMaxInventory(int maxInventory) {
        this.maxInventory = maxInventory;
    }
}
