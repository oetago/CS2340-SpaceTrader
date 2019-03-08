package neighbors.com.spacetrader.model;
import java.util.Map;
import java.util.HashMap;

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

    private int maxCargo; //TODO Assign cargo capacity and cost to each ship and add constructors to each enum
    private int currentCargo;
    private String name;
    private Map<Good, Integer> inventory = new HashMap<>();
    private int cost;

    Spaceship(int maxCargo, String name, int cost) {
        this.maxCargo = maxCargo;
        this.currentCargo = 0;
        this.name = name;
        this.cost = cost;
    }

    public int getMaxCargo() { return maxCargo; }
    public int getCurrentCargo() { return currentCargo; }
    public String getName() { return name; }
    public Map<Good, Integer> getInventory() { return inventory; }
    public void setInventory(Map<Good, Integer> newInventory) { this.inventory = newInventory; }
    public void setCurrentCargo(int del) { currentCargo += del; }
    public int getCost() { return cost;}

}
