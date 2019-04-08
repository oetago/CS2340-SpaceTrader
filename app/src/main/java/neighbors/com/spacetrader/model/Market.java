package neighbors.com.spacetrader.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import androidx.annotation.VisibleForTesting;

/**
 * Info holder to handle market transactions in {@link neighbors.com.spacetrader.ui.market.MarketActivity}
 */
public class Market {
    private Map<Good, Integer> sellPrices;
    private Map<Good, Integer> buyPrices;
    private List<Good> goods;

    /**
     * Creates an instance of Market for a certain TechLevel
     * @param techLevel the techLevel of the planet the market is on
     */
    public Market(TechLevel techLevel) {
        sellPrices = new HashMap<>(Good.values().length);
        buyPrices = new HashMap<>(Good.values().length);
        goods = new ArrayList<>();
        populate(techLevel);
    }

    private void populate(TechLevel techLevel) {
        Random random = new Random();
        for (Good good : Good.values()) {
            if (good.canSell(techLevel.getLevel())) {
                int sellPrice = calculateSellPrice(good, techLevel, random);
                sellPrices.put(good, sellPrice);
            }

            if (good.canBuy(techLevel.getLevel())) {
                int buyPrice = calculateBuyPrice(good, techLevel, random);
                buyPrices.put(good, buyPrice);
                goods.add(good);
            }
        }
    }

    /**
     * Sell price = BP + IPL*(TL - MTLU) + VAR
     *
     * @param good      The good to calculate
     * @param techLevel the techlevel to use
     * @param random    a random to use
     * @return the calculated sell price of a good
     */
    private int calculateSellPrice(Good good, TechLevel techLevel, Random random) {
        return good.getBasePrice()
                + good.getIncreasePerLevel() * (techLevel.getLevel() - good.getMinTechLevelToUse())
                + random.nextInt(good.getVar()) - (good.getVar() / 2);
    }

    /**
     * Buy price = BP + IPL*(TL - MTLP) + VAR
     *
     * @param good      The good to calculate
     * @param techLevel the techlevel to use
     * @param random    a random to use
     * @return the calculated buy price of a good
     */
    private int calculateBuyPrice(Good good, TechLevel techLevel, Random random) {
        return good.getBasePrice()
                + good.getIncreasePerLevel() * (techLevel.getLevel() - good.getMinTechLevelToProduce())
                + random.nextInt(good.getVar()) - (good.getVar() / 2);
    }

    /**
     * Gets the number of available goods in the market
     * @return the number of goods in the market
     */
    public int goodsCount() {
        return goods.size();
    }

    /**
     * Gets the Good at a certain position in the list
     * of Goods in the market
     * @param position the position to retrieve
     * @return the Good at the given position
     */
    public Good getGood(int position) {
        return goods.get(position);
    }

    /**
     * Gets the sell price of a given good
     * @param good the Good to retrieve the sell price of
     * @return the sell price of the given Good
     */
    public Integer getGoodSellPrice(Good good) {
        return sellPrices.get(good);
    }

    /**
     * Gets the buy price of a given Good
     * @param good the Good to retrieve the buy price of
     * @return the buy price of the given Good
     */
    public Integer getGoodBuyPrice(Good good) {
        return buyPrices.get(good);
    }

    @VisibleForTesting
    public void setGoodBuyPrices(Map<Good, Integer> buyPrices) {
        this.buyPrices = buyPrices;
    }
}
