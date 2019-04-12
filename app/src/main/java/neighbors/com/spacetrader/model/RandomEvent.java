package neighbors.com.spacetrader.model;

import java.util.Random;

import neighbors.com.spacetrader.ui.pirate.PirateActivity;
import neighbors.com.spacetrader.ui.police.PoliceActivity;
import neighbors.com.spacetrader.ui.universe.UniverseActivity;


/**
 * Random Event Enum
 */
public enum RandomEvent {
    NO_EVENT("You travel safely...", UniverseActivity.class),
    PIRATE("A pirate approaches...", PirateActivity.class),
    POLICE("You hear sirens behind you...", PoliceActivity.class);
//    DAMAGE("An asteroid impacts your ship...", DamageActivity.class);

    private final String message;
    private final Class activity;

    RandomEvent(String m, Class a) {
        message = m;
        activity = a;
    }

    /**
     * When traveling, decides if a random event will occur
     *
     * @return the random event decided upon
     */
    public static RandomEvent decideEvent() {
        int decider = new Random().nextInt(100);
        if ((decider >= 1) && (decider < 35)) {
            return PIRATE;
        } else if ((decider >= 35) && (decider < 60)) {
            return POLICE;
        } else {
            return NO_EVENT;
        }
    }

    /**
     * Gets random event message
     * @return message for random event
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the type of random event
     * @return class of random event
     */
    public Class getActivity() {
        return activity;
    }
}
