package neighbors.com.spacetrader.ui.universe;


import android.app.Application;
import android.content.Context;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import neighbors.com.spacetrader.model.RandomEvent;
import neighbors.com.spacetrader.model.Repository;
import neighbors.com.spacetrader.model.SolarSystem;
import neighbors.com.spacetrader.model.Universe;

public class UniverseViewModel extends AndroidViewModel {
    private final String TAG = UniverseViewModel.class.getCanonicalName();
    private Repository repo;

    public UniverseViewModel(@NonNull Application application) {
        super(application);
        repo = new Repository(application);
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
