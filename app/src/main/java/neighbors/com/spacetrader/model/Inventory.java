package neighbors.com.spacetrader.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class used to represent the inventory of a Spaceship
 */
public class Inventory {
    private int maxInventory;
    private int currentInventory;
    private Map<Good, Integer> inventory;

    /**
     * Creates an instance of Inventory with an already existent inventory map
     * @param maxInventory the maximum amount of items allowed in the inventory
     * @param inventory the inventory to copy over
     */
    public Inventory(int maxInventory, Map<Good, Integer> inventory) {
        this.maxInventory = maxInventory;
        Set<Good> keys = inventory.keySet();
        for (Good key : keys) {
            currentInventory += inventory.get(key);
        }
        this.inventory = inventory;
    }

    /**
     * Creates an instance of Inventory with an empty inventory
     * @param maxInventory the maximum amount of items allowed in the inventory
     */
    public Inventory(int maxInventory) {
        this(maxInventory, new HashMap<Good, Integer>());
    }

    public Map<Good, Integer> getInventory() { return Collections.unmodifiableMap(this.inventory); }
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

    /**
     * Gets the maximum number of items allowed in the inventory
     * @return the max number of items
     */
    public int getMaxInventory() {
        return maxInventory;
    }

    /**
     * Gets the number of items currently in the inventory
     * @return
     */
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
            //Usually use get or default but android api is old
            int goodCount = inventory.get(good);
            inventory.put(good, goodCount - quantity);
            currentInventory -= quantity;
        }
    }

    /**
     * Gets the number of a certain good currently in the inventory
     * @param good the good to check for
     * @return the quantity of a certain good in the inventory
     */
    public int getQuantity(Good good) {
        if (inventory.containsKey(good)) {
            return inventory.get(good);
        }
        return 0;
    }

    /**
     * Adds a quantity of a good to the inventory
     * @param good Good to add
     * @param quantity How many of the good we are adding
     */
    public void add(Good good, Integer quantity) {
        int goodCount = 0;
        if (inventory.containsKey(good)) {
            goodCount = inventory.get(good);
        }
        inventory.put(good, goodCount + quantity);
        currentInventory += quantity;
    }

    /**
     * Sets the current number of items in inventory to
     * a certain number
     * @param currentInventory the quantity to set your current
     *                         inventory to
     */
    public void setCurrentInventory(int currentInventory) {
        this.currentInventory = currentInventory;
    }

    /**
     * Sets the inventory to a different inventory
     * @param inventory the new inventory to use
     */
    public void setInventory(Map<Good, Integer> inventory) {
        this.inventory = inventory;
    }

    /**
     * Sets the maximum number of items allowed in the inventory
     * @param maxInventory the new number of max items
     */
    public void setMaxInventory(int maxInventory) {
        this.maxInventory = maxInventory;
    }
}
