package neighbors.com.spacetrader.ui.universe;


import java.util.List;

import androidx.lifecycle.ViewModel;
import neighbors.com.spacetrader.model.SolarSystem;
import neighbors.com.spacetrader.model.Universe;

public class UniverseViewModel extends ViewModel {
    private final String TAG = UniverseViewModel.class.getCanonicalName();
    private Universe universe;

    public UniverseViewModel() {
        universe = new Universe();
    }

    public Universe getUniverse() {
        return universe;
    }

    public List<SolarSystem> getSolarSystems() {
        return universe.getSolarSystems();
    }
}
