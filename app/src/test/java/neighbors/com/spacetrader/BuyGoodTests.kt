package neighbors.com.spacetrader

import io.mockk.every
import io.mockk.mockk
import neighbors.com.spacetrader.model.*
import neighbors.com.spacetrader.ui.market.TransactionProcessor
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BuyGoodTests {

    private val player = mockk<Player>(relaxed = true)
    private val market = Market(TechLevel.AGRICULTURE)
    private val inventory = Inventory(5)
    lateinit var processor: TransactionProcessor

    @Before
    fun setup() {
        every { player.inventory } returns inventory
        market.setGoodBuyPrices(mutableMapOf(Pair(Good.FIREARMS, 5)))
        processor = TransactionProcessor(market, player)
    }

    @Test
    fun `Test buy good when not enough space`() {
        val response = processor.buyGood(Good.FIREARMS, 10)
        assertEquals(TransactionResponse.NOT_ENOUGH_SPACE, response)
    }

    @Test
    fun `Test good price is null`() {
        market.setGoodBuyPrices(mutableMapOf())
        val response = processor.buyGood(Good.FIREARMS, 1)
        assertEquals(TransactionResponse.ERROR, response)
    }

    @Test
    fun `Test not enough money`() {
        player.credits = 0
        val response = processor.buyGood(Good.FIREARMS, 1)
        assertEquals(TransactionResponse.NOT_ENOUGH_MONEY, response)
    }

    @Test
    fun `Test all works!`() {
        val player = Player()
        player.credits = 1000
        market.setGoodBuyPrices(mutableMapOf(Pair(Good.FIREARMS, 5)))
        processor = TransactionProcessor(market, player)
        val response = processor.buyGood(Good.FIREARMS, 1)
        assertEquals(TransactionResponse.COMPLETED, response)
        assertEquals(player.inventory.inventory[Good.FIREARMS], 1)
        assertEquals(995, player.credits)
    }

}