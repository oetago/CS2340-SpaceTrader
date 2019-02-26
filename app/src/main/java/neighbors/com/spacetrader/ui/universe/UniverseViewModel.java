package neighbors.com.spacetrader.ui.universe;


import androidx.lifecycle.ViewModel;
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
}
