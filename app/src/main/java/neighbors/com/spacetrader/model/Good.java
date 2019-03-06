package neighbors.com.spacetrader.model;


public enum Good {
    WATER(0, 0, 2, 30, 3, 4, RPEvent.DROUGHT, Resources.LOTSOFWATER, Resources.DESERT, 30, 50),
    FURS(0, 0, 0, 250, 10, 10, RPEvent.COLD, Resources.RICHFAUNA, Resources.LIFELESS, 230, 280),
    FOOD(1, 0, 1, 100, 5 ,5, RPEvent.CROPFAIL, Resources.POORSOIL, Resources.POORSOIL, 90, 160),
    ORE(2, 2, 3, 350, 20, 10, RPEvent.WAR, Resources.MINERALRICH, Resources.MINERALPOOR, 350, 420),
    GAMES(3, 1, 6, 250, -10, 5, RPEvent.BORDEOM, Resources.ARTISTIC, null, 160, 270),
    FIREARMS(3, 1, 5, 1250, -75, 100, RPEvent.WAR, Resources.WARLIKE, null, 600, 1100),
    MEDICINE(4, 1, 6, 650, -20, 10, RPEvent.PLAGUE, Resources.LOTSOFHERBS, null, 400, 700),
    MACHINES(4, 3, 5, 900, -30, 5, RPEvent.LACKOFWORKERS, null, null, 600, 800),
    NARCOTICS(5, 0, 5, 3500, -125, 150, RPEvent.BORDEOM, Resources.WEIRDMUSHROOMS, null, 2000, 3000),
    ROBOTS(6, 4, 7, 5000, -150, 100, RPEvent.LACKOFWORKERS, null, null, 3500, 5000);

    private final int minTechLevelToProduce;      //Minimum Tech Level to Produce this resource (You can't buy on planets below this level)
    private final int minTechLevelToUse;          //Minimum Tech Level to Use this resource (You can't sell on planets below this level)
    private final int producedMostlyAtTechLevel;  //Tech Level which produces the most of this item
    private final int basePrice;                  //Base price of good
    private final int increasePerPriceLevel;      //Price increase per tech level
    private final int var;                        //Variance is the maximum percentage that the price can vary above or below the base
    private final RPEvent priceIncreaseEvent;     //Radical price increase event, when this even happens on a planet, the price may increase astronomically
    private final Resources lowPriceCondition;    //When this condition is present, the price of this resource is unusually low //TODO Same as highPriceCondition. Set both to null for "never" for now
    private final Resources highPriceCondition;   //When this condition is present, the resource is expensive //TODO Some goods do not have a highPriceCondition. In that case, should we set this var to null? Or what?
    private final int minSpacePrice;              //Min price offered in space trade with random trader (not on a planet)
    private final int maxSpacePrice;              // Max price offered in space trade with random trader (not on a planet)

    Good(int MTLP, int MTLU, int TTP, int BP, int IPL, int VAR, RPEvent IE, Resources CR, Resources ER, int MTL, int MTH) {
        this.minTechLevelToProduce = MTLP;
        this.minTechLevelToUse = MTLU;
        this.producedMostlyAtTechLevel = TTP;
        this.basePrice = BP;
        this.increasePerPriceLevel = IPL;
        this.var = VAR;
        this.priceIncreaseEvent = IE;
        this.lowPriceCondition = CR;
        this.highPriceCondition = ER;
        this.minSpacePrice = MTL;
        this.maxSpacePrice = MTH;
    }

    public int getMinTechLevelToProduce() { return minTechLevelToProduce; }
    public int getMinTechLevelToUse() { return minTechLevelToUse; }
    public int getProducedMostlyAtTechLevel() { return producedMostlyAtTechLevel; }
    public int getBasePrice() { return basePrice; }
    public int getIncreasePerPriceLevel() { return increasePerPriceLevel; }
    public int getVar() { return var; }
    public RPEvent getPriceIncreaseEvent() { return priceIncreaseEvent; }
    public Resources getLowPriceCondition() { return lowPriceCondition; }
    public Resources getHighPriceCondition() { return highPriceCondition; }
    public int getMinSpacePrice() { return minSpacePrice; }
    public int getMaxSpacePrice() { return maxSpacePrice; }

    public boolean canBuy(int techLevel) {
        return this.minTechLevelToProduce <= techLevel;
    }

    public boolean canSell(int techLevel) {
        return this.minTechLevelToUse <= techLevel;
    }

}
