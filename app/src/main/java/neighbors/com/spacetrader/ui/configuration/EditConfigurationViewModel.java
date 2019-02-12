package neighbors.com.spacetrader.ui.configuration;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import neighbors.com.spacetrader.model.Player;

public class EditConfigurationViewModel extends ViewModel {

    public static final String TAG = EditConfigurationViewModel.class.getCanonicalName();

    public void savePlayer(Player player) {
        //Currently for now log
        Log.d(TAG, player.toString());
    }

}
