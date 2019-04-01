package neighbors.com.spacetrader.ui.universe;


import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.model.Repository;
import neighbors.com.spacetrader.model.SolarSystem;
import neighbors.com.spacetrader.model.Universe;

public class UniverseViewModel extends AndroidViewModel {
    private Repository repo;
    private Player player;


    public UniverseViewModel(Application application)
    {
        super(application);
        repo = new Repository(application);
        player = repo.getAllSavedGames().get(0);
    }

    public Universe getUniverse() { return player.getUniverse(); }

    public List<SolarSystem> getSolarSystems() {
        return getUniverse().getSolarSystems();
    }

    public SolarSystem getSolarSystem() {
        return player.getSolarSystem();
    }

    public void saveSolarSystem(SolarSystem system) { player.setCurrentSystem(system); repo.update(player);}

}
