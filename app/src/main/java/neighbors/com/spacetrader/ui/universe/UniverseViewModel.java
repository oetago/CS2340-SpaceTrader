package neighbors.com.spacetrader.ui.universe;


import java.util.List;

import androidx.lifecycle.ViewModel;
import neighbors.com.spacetrader.model.RandomEvent;
import neighbors.com.spacetrader.model.Repository;
import neighbors.com.spacetrader.model.SolarSystem;
import neighbors.com.spacetrader.model.Universe;

public class UniverseViewModel extends ViewModel {
    private final String TAG = UniverseViewModel.class.getCanonicalName();
    private Repository repo;

    public UniverseViewModel() {
        repo = Repository.getInstance();
    }

    public Universe getUniverse() {
        return repo.getUniverse();
    }

    public List<SolarSystem> getSolarSystems() {
        return repo.getSolarSystems();
    }

    public SolarSystem getSolarSystem(String name) {
        return repo.getSolarSystem(name);
    }

    public RandomEvent getEvent() {
        return RandomEvent.decideEvent();
    }

    public int getPlayerFuel() {
        return repo.getPlayer().getFuel();
    }

    public void usePlayerFuel() {
        repo.getPlayer().useFuel();
    }

}
