package neighbors.com.spacetrader.model;

import java.util.Map;

/**
 * Player's Spaceship data
 */
public enum Spaceship {
    FLEA(10, "Flea", 10),
    GNAT(15, "Gnat", 27),
    FIREFLY(20, "Firefly", 74),
    MOSQUITO(25, "Mosquito", 201),
    BUMBLEBEE(30, "Bumblebee", 546),
    BEETLE(35, "Beetle", 1484),
    HORNET(40, "Hornet", 4034),
    GRASSHOPPER(45, "Grasshopper",6651),
    TERMITE(50, "Termite", 9923),
    WASP(100, "Wasp", 30000);

    private final String name;
    private Inventory inventory;
    private final int cost;
    private int fuel;

    Spaceship(int maxCargo, String name, int cost) {
        this.name = name;
        this.cost = cost;
        inventory = new Inventory(maxCargo);
        fuel = 5;
    }

    /**
     * Gets maximum spaceship cargo
     * @return cargo capacity
     */
    public int getMaxCargo() {
        return inventory.getMaxInventory();
    }

    /**
     * Gets current spaceship cargo level
     * @return spaceship cargo level
     */
    public int getCurrentCargo() {
        return inventory.getCurrentInventory();
    }

    /**
     * Gets name of spaceship
     * @return name of spaceship
     */
    public String getName() { return name; }

    /**
     * Gets spaceship inventory
     * @return inventory of spaceship
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Sets spaceship inventory to something else
     * @param newInventory new spaceship inventory
     */
    public void setInventory(Inventory newInventory) {
        this.inventory = newInventory;
    }

    /**
     * Gets cost of spaceship
     * @return how much a spaceship costs
     */
    public int getCost() { return cost;}

    /**
     * Gets quantity of good in spaceship inventory
     * @param good good whose quantity is requested
     * @return the quantity of the specified good
     */
    public int getQuantity(Good good) {
        return inventory.getQuantity(good);
    }

    /**
     * The spaceship fuel level
     * @return the fuel left in the spaceship
     */
    public int getFuel() {
        return fuel;
    }

    /**
     * Decrements fuel level in spaceship
     */
    public void useFuel() {
        fuel--;
    }

    /**
     * Sets fuel level in spaceship
     * @param fuel new fuel level in spaceship
     */
    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    /**
     * Gets inventory as a map
     * @return map of spaceship inventory as goods to quantities
     */
    public Map<Good, Integer> getInventoryMap() {
        return inventory.getInventory();
    }

    /**
     * Removes quantity of a specified good
     * @param good good to be removed from inventory
     * @param num quantity of specified good to be removed from inventory
     */
    public void remove(Good good, int num) {
        inventory.remove(good, num);
    }
}
