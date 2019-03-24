package neighbors.com.spacetrader.ui.market;

import androidx.lifecycle.ViewModel;
import neighbors.com.spacetrader.model.Market;
import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.model.Repository;


public class MarketViewModel extends ViewModel {
    private static final String TAG = MarketViewModel.class.getCanonicalName();
    private Repository repo;
    private Player player;

    public MarketViewModel() {
        repo = Repository.getInstance();
        player = repo.getPlayer();

    }

    public Market getMarket(String name) {
        return repo.getMarket(name);
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
