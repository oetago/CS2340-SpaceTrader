package neighbors.com.spacetrader.ui.base;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import neighbors.com.spacetrader.model.Repository;

/**
 * Class used to save player
 */
public class BaseViewModel extends AndroidViewModel {

    protected final Repository repository;

    /**
     * Creates an instance of BaseViewModel to use to save a player
     * @param application the application
     */
    protected BaseViewModel(Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    /**
     * Saves the player into the database for future use
     */
    public void save() {
        repository.save();
    }
}
