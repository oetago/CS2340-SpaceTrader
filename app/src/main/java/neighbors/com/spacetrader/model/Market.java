package neighbors.com.spacetrader.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Infoholder to handel market transactions in {@link neighbors.com.spacetrader.ui.market.MarketActivity}
 */
public class Market {
    private Map<Good, Integer> sellPrices;
    private Map<Good, Integer> buyPrices;
    private List<Good> goods;

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

    public int goodsCount() {
        return goods.size();
    }

    public Good getGood(int position) {
        return goods.get(position);
    }

    public Integer getGoodSellPrice(Good good) {
        return sellPrices.get(good);
    }

    public Integer getGoodBuyPrice(Good good) {
        return buyPrices.get(good);
    }
}
