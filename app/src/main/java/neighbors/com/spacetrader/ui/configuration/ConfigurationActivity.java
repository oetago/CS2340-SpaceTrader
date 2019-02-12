package neighbors.com.spacetrader.ui.configuration;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import neighbors.com.spacetrader.R;

/**
 * Activity to allow user to create new Player with stats, and difficulty
 */
public class ConfigurationActivity extends AppCompatActivity {

    private EditConfigurationViewModel editConfigurationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        editConfigurationViewModel = ViewModelProviders.of(this).get(EditConfigurationViewModel.class);

        //save player with editConfigurationViewModel.save(Player)
    }

}
