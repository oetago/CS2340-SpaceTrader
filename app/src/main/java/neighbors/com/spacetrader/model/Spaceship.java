package neighbors.com.spacetrader.model;
import java.util.Map;
import java.util.HashMap;

public enum Spaceship {
    FLEA(10, "Flea", 10),
    GNAT(10, "Gnat", 10),
    FIREFLY(10, "Firefly", 10),
    MOSQUITO(10, "Mosquito", 10),
    BUMBLEBEE(10, "Bumblebee", 10),
    BEETLE(10, "Beetle", 10),
    HORNET(10, "Hornet", 10),
    GRASSHOPPER(10, "Grasshopper", 10),
    TERMITE(10, "Termite", 10),
    WASP(10, "Wasp", 10);

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
    public int getCost() { return cost;}

}
