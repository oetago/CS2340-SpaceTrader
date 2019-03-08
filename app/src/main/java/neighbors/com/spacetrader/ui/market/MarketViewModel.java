package neighbors.com.spacetrader.ui.market;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.ViewModel;
import neighbors.com.spacetrader.model.Good;
import neighbors.com.spacetrader.model.Planet;
import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.model.Repository;
import neighbors.com.spacetrader.model.Spaceship;


public class MarketViewModel extends ViewModel {
    private static final String TAG = MarketViewModel.class.getCanonicalName();
    private Repository repo;
    private Player player;
    private Spaceship ship;
    private Planet planet;

    private Map<Good, Integer> sellPrices;
    private Map<Good, Integer> buyPrices;
    private Map<Good, Integer> inventory;

    public MarketViewModel() {
        getInstanceData();
    }

    private void getInstanceData() {
        // Gets repo for game data and initializes parameters needed for market.
        // Some might be unnecessary
        repo = Repository.getInstance();
        player = repo.getPlayer();
        if (player != null) {
            ship = player.getSpaceship();
            inventory = ship.getInventory();
            planet = player.getCurrentPlanet();
            sellPrices = planet.getSellPrices();
            buyPrices = planet.getBuyPrices();
        }
    }

    /**
     * Handles player sell transaction
     *
     * @return boolean true if transaction successful, false if unsuccessful
     * @param sGood good to sell
     * @param quant quantity of good to sell
     */
    public boolean sellGood(Good sGood, Integer quant) {
        // Only completes purchase if system has sell price for good
        Integer sPrice = sellPrices.get(sGood);
        if (sPrice != null) {
            // Tries to sell the amount specified
            boolean success = this.editInventory(sGood, -quant);
            if (success) {
                player.updateCredits(quant * sPrice);
                this.saveState(); // Updates values to repo
                return true;
            } else {
                // Returns false when selling more than player has
                return false;
            }
        } else {
            // Return false when planet will not buy good from player
            return false;
        }
    }

    /**
     * Handles player buy transaction.
     *
     * @return boolean true if transaction successful, false if unsuccessful
     * @param pGood good to purchase
     * @param quant quantity of good to purchase
     */
    public boolean buyGood(Good pGood, Integer quant) {
        // Only completes purchase if player has enough credits and system has buy price for good
        Integer bPrice = buyPrices.get(pGood);
        if (bPrice != null) {
            int transAmt = quant * bPrice;
            int playerCredits = player.getCredits();
            if (transAmt > playerCredits) {
                // Return false when transaction amount is greater than player credits
                return false;
            } else {
                // Adds goods to player inventory
                boolean success = this.editInventory(pGood, quant);
                if (success) {
                    player.updateCredits(transAmt);
                    this.saveState(); // Saves updated values to repo
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            // Return false when good is not available for purchase
            return false;
        }
    }



    /**
     * Allows inventory to be edited one item at a time, overwriting old quantities
     *
     * @return boolean true if edit was successful false if edit failed
     * @param editGood - the good whose quantity will be edited
     * @param dQuant - the difference in quantity of goods
     */
    private boolean editInventory(Good editGood, Integer dQuant) {
        Integer cQuant = inventory.get(editGood);
        // Checks that good being edited is valid
        if (cQuant != null) {
            // Adds change in inventory to current inventory quantity
            if ((cQuant + dQuant) > -1) {
                // Checks that cargo is not full
                if ((ship.getMaxCargo() - ship.getCurrentCargo() - dQuant) < 0) {
                    // Returns false if the quantity put in the cargo will exceed ship capacity
                    return false;
                } else {
                    inventory.put(editGood, cQuant + dQuant);
                    ship.setCurrentCargo(dQuant);
                    return true;
                }

            } else {
                // Returns false if quantity causes inventory to go negative
                return false;
            }
        } else {
            // Checks that good edited is not in inventory and is non-negative
            if (dQuant > -1) {
                // Even if adding good that is not currently present, check inventory capacity
                if ((ship.getMaxCargo() - ship.getCurrentCargo() - dQuant) < 0) {
                    return false;
                } else {
                    inventory.put(editGood, dQuant);
                    return true;
                }
            } else {
                // Returns false if the dQuant is < 0
                return false;
            }
        }
    }

    // Saves player to repo
    private void saveState() {
        ship.setInventory(inventory);
        player.setSpaceship(ship);
        repo.setPlayer(player);
    }

    // Gets all good sell prices
    public Map<Good, Integer> getAllSellPrices() { return sellPrices; }

    /**
     * Gets price of one good as an integer.
     *
     * @return sell price of good queried, null if planet will not buy
     * @param qGood good whose price to be queried
     */
    public Integer getSellPrice(Good qGood) { return sellPrices.get(qGood); }

    // Gets all good buy prices
    public Map<Good, Integer> getAllBuyPrices() { return buyPrices; }

    /**
     * Gets price of one good as an integer. Returns null if good is not sold.
     *
     * @return buy price of good queried, null if not for purchase
     * @param qGood good whose price to be queried
     */
    public Integer getBuyPrice(Good qGood) { return buyPrices.get(qGood); }

    // Gets ship inventory from player. Redundant here but might be useful for MarketView.
    public Map<Good, Integer> getShipInventory() { return repo.getPlayer().getSpaceship().getInventory(); }


}
