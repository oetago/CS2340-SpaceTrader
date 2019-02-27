package neighbors.com.spacetrader.model;
import java.util.Random;

public enum Resources {

    NOSPECIALRESOURCES("No Special Resources"),
    MINERALRICH("Mineral Rich"),
    MINERALPOOR("Mineral Poor"),
    DESERT("Desert"),
    LOTSOFWATER("Lots of Water"),
    RICHSOIL("Rich Soil"),
    POORSOIL("Poor Soil"),
    RICHFAUNA("Rich Fauna"),
    LIFELESS("Lifeless"),
    WEIRDMUSHROOMS("Weird Mushrooms"),
    LOTSOFHERBS("Lots of Herbs"),
    ARTISTIC("Artistic"),
    WARLIKE("Warlike");


    private final String name;

    Resources(String pname) {
        name = pname;
    }

    public String getName() {
        return name;
    }

    public static Resources getRandom() {
        Random rand = new Random();
        int resInt = rand.nextInt(100);
        if (resInt >= 97) {
            return WARLIKE;
        } else if (resInt >= 94) {
            return ARTISTIC;
        } else if (resInt >= 90) {
            return LOTSOFHERBS;
        } else if (resInt >= 86) {
            return WEIRDMUSHROOMS;
        } else if (resInt >= 82) {
            return LIFELESS;
        } else if (resInt >= 77) {
            return RICHFAUNA;
        } else if (resInt >= 71) {
            return POORSOIL;
        } else if (resInt >= 63) {
            return RICHSOIL;
        } else if (resInt >= 55) {
            return LOTSOFWATER;
        } else if (resInt >= 46) {
            return DESERT;
        } else if (resInt >= 33) {
            return MINERALPOOR;
        } else if (resInt >= 20) {
            return MINERALRICH;
        } else {
            return NOSPECIALRESOURCES;
        }
    }

}
