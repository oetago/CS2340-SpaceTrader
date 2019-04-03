package neighbors.com.spacetrader.model;

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

    private String name;
    private Inventory inventory;
    private int cost;
    private int fuel;

    Spaceship(int maxCargo, String name, int cost) {
        this.name = name;
        this.cost = cost;
        inventory = new Inventory(maxCargo);
        fuel = 5;
    }

    public int getMaxCargo() {
        return inventory.getMaxInventory();
    }

    public int getCurrentCargo() {
        return inventory.getCurrentInventory();
    }

    public String getName() { return name; }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory newInventory) {
        this.inventory = newInventory;
    }

    public int getCost() { return cost;}

    public int getQuantity(Good good) {
        return inventory.getQuantity(good);
    }

    public int getFuel() {
        return fuel;
    }

    public void useFuel() {
        fuel--;
    }
}
