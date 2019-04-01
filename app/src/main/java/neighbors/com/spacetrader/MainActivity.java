package neighbors.com.spacetrader;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.model.Repository;
import neighbors.com.spacetrader.ui.configuration.ConfigurationActivity;
import neighbors.com.spacetrader.ui.universe.UniverseActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repo = new Repository(getApplication());
        List<Player> currentPlayer = repo.getAllSavedGames();
        if (currentPlayer.size() == 0) {
            Intent intent = new Intent(this, ConfigurationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, UniverseActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
            startActivity(intent);
        }



    }
}
