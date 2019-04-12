package neighbors.com.spacetrader.ui.market;

import neighbors.com.spacetrader.model.Good;
import neighbors.com.spacetrader.model.Inventory;
import neighbors.com.spacetrader.model.Market;
import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.model.TransactionResponse;

/**
 * Handles transactions for market
 */
public class TransactionProcessor {

    private final Market market;
    private final Player player;
    private final Inventory inventory;

    /**
     * Creates instance of transaction processor
     * @param market market for which transactions will be processed
     * @param player player for which transactions will be processed
     */
    public TransactionProcessor(Market market, Player player) {
        this.market = market;
        this.player = player;
        inventory = player.getInventory();
    }

    /**
     * Handles player sell transaction
     *
     * @param good     good to sell
     * @param quantity quantity of good to sell
     * @return response if the transaction failed or not
     */
    public TransactionResponse sellGood(Good good, int quantity) {
        if (!inventory.hasEnoughOfGood(good, quantity)) {
            return TransactionResponse.NOT_ENOUGH_ITEM;
        }

        Integer price = market.getGoodSellPrice(good);
        if (price != null) {
            player.addCredits(quantity * price);
            inventory.remove(good, quantity);
            return TransactionResponse.COMPLETED;
        }
        return TransactionResponse.ERROR;
    }

    /**
     * Handles player buy transaction.
     *
     * @param good     good to purchase
     * @param quantity quantity of good to purchase
     * @return response if the transaction failed or not
     */
    public TransactionResponse buyGood(Good good, Integer quantity) {
        if (!inventory.hasEnoughSpaceForGood(quantity)) {
            return TransactionResponse.NOT_ENOUGH_SPACE;
        }

        Integer goodPrice = market.getGoodBuyPrice(good);
        if (goodPrice != null) {
            int totalCost = quantity * goodPrice;
            if (player.hasEnoughCredits(totalCost)) {
                return TransactionResponse.NOT_ENOUGH_MONEY;
            }
            player.removeCredits(totalCost);
            inventory.add(good, quantity);
            return TransactionResponse.COMPLETED;
        }
        return TransactionResponse.ERROR;
    }


}
