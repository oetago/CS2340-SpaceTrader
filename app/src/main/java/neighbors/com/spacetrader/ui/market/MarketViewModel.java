package neighbors.com.spacetrader.ui.market;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import neighbors.com.spacetrader.model.Market;
import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.model.Repository;


public class MarketViewModel extends AndroidViewModel {
    private static final String TAG = MarketViewModel.class.getCanonicalName();
    private Repository repo;
    private Player player;

    public MarketViewModel(Application application) {
        super(application);
        repo = new Repository(application);
        player = repo.getAllSavedGames().get(0);

    }

    public Market getMarket(String name) {
        return player.getMarket();
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
