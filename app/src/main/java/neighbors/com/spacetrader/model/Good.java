package neighbors.com.spacetrader.model;


import com.google.gson.annotations.SerializedName;

/**
 * Class used to represent the various Goods the planets can use
 * and produce for buying and selling
 */
public enum Good {
    WATER("Water", 0, 0, 2, 30, 3, 4, RPEvent.DROUGHT, Resources.LOTSOFWATER, Resources.DESERT, 30, 50),
    FURS("Furs", 0, 0, 0, 250, 10, 10, RPEvent.COLD, Resources.RICHFAUNA, Resources.LIFELESS, 230, 280),
    FOOD("Food", 1, 0, 1, 100, 5, 5, RPEvent.CROPFAIL, Resources.POORSOIL, Resources.POORSOIL, 90, 160),
    ORE("Ore", 2, 2, 3, 350, 20, 10, RPEvent.WAR, Resources.MINERALRICH, Resources.MINERALPOOR, 350, 420),
    GAMES("Games", 3, 1, 6, 250, -10, 5, RPEvent.BOREDOM, Resources.ARTISTIC, null, 160, 270),
    FIREARMS("Firearms", 3, 1, 5, 1250, -75, 100, RPEvent.WAR, Resources.WARLIKE, null, 600, 1100), @SerializedName("MEDICINE")
    MEDICINE("Medicine", 4, 1, 6, 650, -20, 10, RPEvent.PLAGUE, Resources.LOTSOFHERBS, null, 400, 700), @SerializedName("MACHINES")
    MACHINES("Machines", 4, 3, 5, 900, -30, 5, RPEvent.LACKOFWORKERS, null, null, 600, 800), @SerializedName("NARCOTICS")
    NARCOTICS("Narcotics", 5, 0, 5, 3500, -125, 150, RPEvent.BOREDOM, Resources.WEIRDMUSHROOMS, null, 2000, 3000), @SerializedName("ROBOTS")
    ROBOTS("Robots", 6, 4, 7, 5000, -150, 100, RPEvent.LACKOFWORKERS, null, null, 3500, 5000);

    private final int minTechLevelToProduce;      //Minimum Tech Level to Produce this resource (You can't buy on planets below this level)
    private final int minTechLevelToUse;          //Minimum Tech Level to Use this resource (You can't sell on planets below this level)
    private final int producedMostlyAtTechLevel;  //Tech Level which produces the most of this item
    private final int basePrice;                  //Base price of good
    private final int increasePerLevel;           //Price increase per tech level
    private final int var;                        //Variance is the maximum percentage that the price can vary above or below the base
    private final RPEvent priceIncreaseEvent;     //Radical price increase event, when this even happens on a planet, the price may increase astronomically
    private final Resources lowPriceCondition;    //When this condition is present, the price of this resource is unusually low //TODO Same as highPriceCondition. Set both to null for "never" for now
    private final Resources highPriceCondition;   //When this condition is present, the resource is expensive //TODO Some goods do not have a highPriceCondition. In that case, should we set this var to null? Or what?
    private final int minSpacePrice;              //Min price offered in space trade with random trader (not on a planet)
    private final int maxSpacePrice;              // Max price offered in space trade with random trader (not on a planet)
    private final String name;

    Good(String name, int MTLP, int MTLU, int TTP, int BP, int IPL, int VAR, RPEvent IE, Resources CR, Resources ER, int MTL, int MTH) {
        this.minTechLevelToProduce = MTLP;
        this.minTechLevelToUse = MTLU;
        this.producedMostlyAtTechLevel = TTP;
        this.basePrice = BP;
        this.increasePerLevel = IPL;
        this.var = VAR;
        this.priceIncreaseEvent = IE;
        this.lowPriceCondition = CR;
        this.highPriceCondition = ER;
        this.minSpacePrice = MTL;
        this.maxSpacePrice = MTH;
        this.name = name;
    }

    /**
     * Gets the minimum tech level required to produce a good
     * @return min tech level
     */
    public int getMinTechLevelToProduce() {
        return minTechLevelToProduce;
    }

    /**
     * Gets the minimum tech level required to use a good
     * @return min tech level
     */
    public int getMinTechLevelToUse() {
        return minTechLevelToUse;
    }

    /**
     * Gets the tech level which produces mostly this good
     * @return the tech level
     */
    public int getProducedMostlyAtTechLevel() {
        return producedMostlyAtTechLevel;
    }

    /**
     * Gets the base price of a good
     * @return the base price
     */
    public int getBasePrice() {
        return basePrice;
    }

    /**
     * Gets the price increase per tech level for a good
     * @return the price increase per level
     */
    public int getIncreasePerLevel() {
        return increasePerLevel;
    }

    /**
     * Gets the variance for a good
     * @return the variance
     */
    public int getVar() {
        return var;
    }

    /**
     * Gets the Event that would cause a price increase for a
     * good on a planet
     * @return the Event that would cause the price increase
     */
    public RPEvent getPriceIncreaseEvent() {
        return priceIncreaseEvent;
    }

    /**
     * Gets the Resource condition that would cause a Good
     * to be low price on a planet
     * @return the corresponding Resource condition
     */
    public Resources getLowPriceCondition() {
        return lowPriceCondition;
    }

    /**
     * Gets the Resource condtion that would cause a Good
     * to be high price on a planet
     * @return the corresponding Resource condition
     */
    public Resources getHighPriceCondition() {
        return highPriceCondition;
    }

    /**
     * Gets the minimum price of a Good in space
     * @return the minimum price
     */
    public int getMinSpacePrice() {
        return minSpacePrice;
    }

    /**
     * Gets the maximum price of a Good in space
     * @return the maximum price
     */
    public int getMaxSpacePrice() {
        return maxSpacePrice;
    }

    /**
     * Gets the name of a Good
     * @return the name of the Good
     */
    public String getName() {
        return name;
    }

    /**
     * Checks whether or not a Good can be bought at the
     * given TechLevel
     * @param techLevel the TechLevel of a planet
     * @return whether or not a Good can be bought at the TechLevel
     */
    public boolean canBuy(int techLevel) {
        return this.minTechLevelToProduce <= techLevel;
    }

    /**
     * Checks if a Good can be sold at the given TechLevel
     * @param techLevel the TechLevel of a planet
     * @return whether or not a Good can be sold at the TechLevel
     */
    public boolean canSell(int techLevel) {
        return this.minTechLevelToUse <= techLevel;
    }

}
