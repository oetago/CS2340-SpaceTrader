package neighbors.com.spacetrader;

import org.junit.Test;
import org.junit.Before;

import java.nio.file.Watchable;

import neighbors.com.spacetrader.model.Good;
import neighbors.com.spacetrader.model.Inventory;
import neighbors.com.spacetrader.model.Market;
import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.model.TechLevel;
import neighbors.com.spacetrader.model.TransactionResponse;
import neighbors.com.spacetrader.ui.market.TransactionProcessor;

import static org.junit.Assert.assertEquals;

public class SellGoodTests {
    private Player player;
    private Market market;
    TransactionProcessor processor;

    @Before
    public void setup() {
        player = new Player();
        market = new Market(TechLevel.MEDEIVAL);
        processor = new TransactionProcessor(market, player);
    }


    @Test
    public void checkResponseWithEnoughInInventoryTest() {
        Inventory inventory = player.getInventory();
        inventory.add(Good.WATER, 10);

        TransactionResponse response = processor.sellGood(Good.WATER, 5);
        assertEquals(response, TransactionResponse.COMPLETED);
    }

    @Test
    public void checkInventoryWithEnoughInInventoryTest() {
        Inventory inventory = player.getInventory();
        inventory.add(Good.WATER, 10);
        int quantity = inventory.getQuantity(Good.WATER);

        processor.sellGood(Good.WATER, 5);
        assertEquals(inventory.getQuantity(Good.WATER), quantity - 5);
    }

    @Test
    public void checkCreditsWithEnoughInInventoryTest() {
        Inventory inventory = player.getInventory();
        inventory.add(Good.WATER, 10);

        int currentCredits = player.getCredits();
        int price = market.getGoodSellPrice(Good.WATER);

        processor.sellGood(Good.WATER, 5);
        assertEquals(player.getCredits(), currentCredits + 5 * price);
    }

    @Test
    public void checkResponseWithoutEnoughInInventoryTest() {
        Inventory inventory = player.getInventory();
        inventory.add(Good.WATER, 5);

        TransactionResponse response = processor.sellGood(Good.WATER, 500);
        assertEquals(response, TransactionResponse.NOT_ENOUGH_ITEM);
    }

    @Test
    public void checkCreditsWithoutEnoughInInventoryTest() {
        Inventory inventory = player.getInventory();
        inventory.add(Good.WATER, 5);

        int currentCredits = player.getCredits();

        processor.sellGood(Good.WATER, 500);
        assertEquals(player.getCredits(), currentCredits);
    }

    @Test
    public void checkInventoryWithoutEnoughInInventoryTest() {
        Inventory inventory = player.getInventory();
        inventory.add(Good.WATER, 5);
        int quantity = inventory.getQuantity(Good.WATER);

        processor.sellGood(Good.WATER, 500);
        assertEquals(inventory.getQuantity(Good.WATER), quantity);
    }

    @Test
    public void checkEverythingEmptyInventory() {
        player.setInventory(new Inventory(player.getSpaceship().getMaxCargo()));
        Inventory inventory = player.getInventory();
        int playerCreditsBefore = player.getCredits();


        TransactionResponse response = processor.sellGood(Good.WATER, 10);

        assertEquals(response, TransactionResponse.NOT_ENOUGH_ITEM); //Checks for correct response
        assertEquals(inventory.getQuantity(Good.WATER), 0); //Checks to make sure cargo is still empty
        assertEquals(playerCreditsBefore, player.getCredits()); //Checks to make sure credits don't change
    }

    @Test
    public void checkEverythingGoodNotProducedTest() {
        Inventory inventory = player.getInventory();
        inventory.add(Good.ROBOTS, 10);
        int playerCreditsBefore = player.getCredits();

        TransactionResponse response = processor.sellGood(Good.ROBOTS, 5);

        assertEquals(response, TransactionResponse.ERROR); //Checks for correct response
        assertEquals(inventory.getQuantity(Good.ROBOTS), 10); //Checks to make sure cargo didn't change
        assertEquals(playerCreditsBefore, player.getCredits()); //Checks to make sure credits don't change
    }
}
