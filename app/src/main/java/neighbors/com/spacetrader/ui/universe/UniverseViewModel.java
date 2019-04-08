package neighbors.com.spacetrader.ui.universe;


import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import neighbors.com.spacetrader.model.RandomEvent;
import neighbors.com.spacetrader.model.SolarSystem;
import neighbors.com.spacetrader.ui.base.BaseViewModel;

class UniverseViewModel extends BaseViewModel {
    private final String TAG = UniverseViewModel.class.getCanonicalName();

    public UniverseViewModel(@NonNull Application application) {
        super(application);
    }

    public List<SolarSystem> getSolarSystems() {
        return repository.getSolarSystems();
    }

    public SolarSystem getSolarSystem() {
        String name = repository.getCurrentPlanetName();
        return repository.getSolarSystem(name);
    }

    public RandomEvent getEvent() {
        return RandomEvent.decideEvent();
    }

    public int getPlayerFuel() {
        return repository.getFuel();
    }

    public void usePlayerFuel() {
        repository.useFuel();
    }

    public void setPlayerPlanet(String name) {
        repository.setCurrentPlanetName(name);
    }
}
