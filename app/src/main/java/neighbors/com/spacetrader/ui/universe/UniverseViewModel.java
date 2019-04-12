package neighbors.com.spacetrader.ui.universe;


import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import neighbors.com.spacetrader.model.RandomEvent;
import neighbors.com.spacetrader.model.SolarSystem;
import neighbors.com.spacetrader.ui.base.BaseViewModel;

class UniverseViewModel extends BaseViewModel {
    private final String TAG = UniverseViewModel.class.getCanonicalName();

    /**
     * Instantiates view model to handle universe
     * @param application application running the view model
     */
    public UniverseViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Gets list of solar systems in universe
     * @return list of solar systems in universe
     */
    public List<SolarSystem> getSolarSystems() {
        return repository.getSolarSystems();
    }

    /**
     * Gets current solar system player is on
     * @return solar system player is on
     */
    public SolarSystem getSolarSystem() {
        String name = repository.getCurrentPlanetName();
        return repository.getSolarSystem(name);
    }

    /**
     * Triggers a random event
     * @return random event that was triggered
     */
    public RandomEvent getEvent() {
        return RandomEvent.decideEvent();
    }

    /**
     * Gets player fuel level
     * @return fuel level of player ship
     */
    public int getPlayerFuel() {
        return repository.getFuel();
    }

    /**
     * Uses player fuel when travelling
     */
    public void usePlayerFuel() {
        repository.useFuel();
    }

    /**
     * Sets player planet by name
     * @param name name of new player planet
     */
    public void setPlayerPlanet(String name) {
        repository.setCurrentPlanetName(name);
    }
}
