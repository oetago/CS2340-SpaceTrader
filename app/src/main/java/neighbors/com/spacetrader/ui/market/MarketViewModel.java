package neighbors.com.spacetrader.ui.market;

import androidx.lifecycle.ViewModel;
import neighbors.com.spacetrader.model.Market;
import neighbors.com.spacetrader.model.Planet;
import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.model.Repository;
import neighbors.com.spacetrader.model.TechLevel;


public class MarketViewModel extends ViewModel {
    private static final String TAG = MarketViewModel.class.getCanonicalName();
    private Repository repo;
    private Planet planet;
    private Player player;

    public MarketViewModel() {
        repo = Repository.getInstance();
        player = repo.getPlayer();
        if (player != null) {
            //TODO Fix repo.getPlanet(id)
            planet = new Planet("Endor", TechLevel.INDUSTRIAL);
        }
    }

    public Market getMarket() {
        return planet.getMarket();
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
