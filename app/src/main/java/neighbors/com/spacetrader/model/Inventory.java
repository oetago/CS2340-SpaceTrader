package neighbors.com.spacetrader.model;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private int maxInventory;
    private int currentInventory;
    private Map<Good, Integer> inventory;

    public Inventory(int maxInventory) {
        this.maxInventory = maxInventory;
        currentInventory = 0;
        inventory = new HashMap<>();
    }

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
}