package neighbors.com.spacetrader.ui.base;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import neighbors.com.spacetrader.model.Repository;

public class BaseViewModel extends AndroidViewModel {

    protected Repository repository;

    public BaseViewModel(Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public void save() {
        repository.save();
    }
}
