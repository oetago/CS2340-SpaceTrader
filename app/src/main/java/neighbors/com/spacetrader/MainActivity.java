package neighbors.com.spacetrader;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import neighbors.com.spacetrader.model.Repository;
import neighbors.com.spacetrader.ui.configuration.ConfigurationActivity;
import neighbors.com.spacetrader.ui.universe.UniverseActivity;

/**
 * Main activity used to direct start of the app
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Repository repository = Repository.getInstance(this);
        if (repository.isFirstTime()) {
            Intent intent = new Intent(this, ConfigurationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, UniverseActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
