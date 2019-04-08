package neighbors.com.spacetrader.ui.market;

import android.app.Application;

import neighbors.com.spacetrader.model.Market;
import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.ui.base.BaseViewModel;


/**
 * Class to serve between the MarketActivity and the model
 */
public class MarketViewModel extends BaseViewModel {
    private static final String TAG = MarketViewModel.class.getCanonicalName();
    private Player player;

    /**
     * Creates an instance of MarketViewModel
     * @param application the application
     */
    public MarketViewModel(Application application) {
        super(application);
        player = repository.getPlayer();
    }


    /**
     * Gets the market by name from the database
     * @param name the name of the market
     * @return the Market we desire
     */
    public Market getMarket(String name) {
        return repository.getMarket(name);
    }

    /**
     * Gets the player from the database to use in the game
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get Players credits as a string
     *
     * @return player's credits
     */
    public String getPlayerCreditsString() {
        if (player != null) {
            return player.getCredits() + "";
        }
        return "";
    }

}
