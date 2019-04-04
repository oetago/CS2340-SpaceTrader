package neighbors.com.spacetrader.ui.market;

import android.app.Application;

import neighbors.com.spacetrader.model.Market;
import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.ui.base.BaseViewModel;


public class MarketViewModel extends BaseViewModel {
    private static final String TAG = MarketViewModel.class.getCanonicalName();
    private Player player;

    public MarketViewModel(Application application) {
        super(application);
        player = repository.getPlayer();
    }


    public Market getMarket(String name) {
        return repository.getMarket(name);
    }

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
