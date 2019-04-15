package neighbors.com.spacetrader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.internal.matchers.Null;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import neighbors.com.spacetrader.model.Good;
import neighbors.com.spacetrader.model.Inventory;

@RunWith(JUnit4.class)
public class InventoryRemoveTests {

    private int maxInventory;
    private Inventory testInv;

    @Before
    public void setup() {
        maxInventory = 32;
        testInv = new Inventory(maxInventory);

        // Populate inventory
        testInv.add(Good.WATER, 5);
        testInv.add(Good.MEDICINE, 10);
        testInv.add(Good.MACHINES, 15);
    }

    @Test
    public void removeGoodEmpty() {
        boolean removed = testInv.remove(Good.ROBOTS, 20);
        assertThat(removed, is(false));
    }

    @Test
    public void removeGoodNegative() {
        boolean removed = testInv.remove(Good.WATER,-4);
        assertThat(removed, is(false));
    }

    @Test
    public void removeGoodGreater() {
        boolean removed = testInv.remove(Good.MEDICINE, 15);
        assertThat(removed, is(false));
    }

    @Test
    public void removeGoodRegularPass() {
        boolean removed = testInv.remove(Good.MACHINES, 14);
        assertThat(removed, is(true));
        int remain = testInv.getQuantity(Good.MACHINES);
        assertThat(remain, is(equalTo(1)));
    }

    @Test
    public void removeGoodRegularQuant() {
        testInv.remove(Good.MACHINES, 14);
        int remain = testInv.getQuantity(Good.MACHINES);
        assertThat(remain, is(equalTo(1)));
    }

    @Test
    public void removeGoodNull() {
        boolean remain = testInv.remove(null, 14);
        assertThat(remain, is(equalTo( false)));
    }


}
