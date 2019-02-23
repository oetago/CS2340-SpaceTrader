package neighbors.com.spacetrader.ui.configuration;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.model.Universe;

public class EditConfigurationViewModel extends ViewModel {

    public static final String TAG = EditConfigurationViewModel.class.getCanonicalName();

    public void savePlayer(Player player) {
        //Currently for now log
        Log.d(TAG, player.toString());
    }

    public void makeUniverse(Universe universe) {
        Log.d(TAG, universe.toString());
    }

}
