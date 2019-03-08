package neighbors.com.spacetrader.model;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Planet {
    private static Random myRand = new Random(); // Shared resource because why not
    private String name;
    private TechLevel planetTechLevel;
    private Map<Good, Integer> sellPrices;
    private Map<Good, Integer> buyPrices;

    public Planet(String name, TechLevel techLevel) {
        this.name = name;
        this.planetTechLevel = techLevel;
        populateBuySellPrices();
    }

    private void populateBuySellPrices() {
        // Populates buy and sell prices for goods
        sellPrices = new HashMap<>(Good.values().length);
        buyPrices = new HashMap<>(Good.values().length);
        for (Good good : Good.values()) {
            // Set sell prices
            if (good.canSell(planetTechLevel.getLevel())) {
                // Sell price = BP + IPL*(TL - MTLU) + VAR
                int sellPrice = good.getBasePrice()
                        + good.getIncreasePerLevel()*(planetTechLevel.getLevel() - good.getMinTechLevelToUse())
                        + myRand.nextInt(good.getVar()) - (good.getVar() / 2);
                sellPrices.put(good, sellPrice);
            }
            // Set buy prices
            if (good.canBuy(planetTechLevel.getLevel())) {
                // Sell price = BP + IPL*(TL - MTLP) + VAR
                int buyPrice = good.getBasePrice()
                        + good.getIncreasePerLevel()*(planetTechLevel.getLevel() - good.getMinTechLevelToProduce())
                        + myRand.nextInt(good.getVar()) - (good.getVar() / 2);
                buyPrices.put(good, buyPrice);
            }
        }
    }

    public String getName() {
        return name;
    }

    public TechLevel getPlanetTechLevel() { return planetTechLevel; }

    public Map<Good, Integer> getSellPrices() { return sellPrices; }

    public Map<Good, Integer> getBuyPrices() { return buyPrices; }

    public void setName(String name) {
        this.name = name;
    }



}
