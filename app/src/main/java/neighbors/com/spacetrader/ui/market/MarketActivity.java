package neighbors.com.spacetrader.ui.market;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import neighbors.com.spacetrader.R;

/**
 * Activity class to handle displaying and interacting with the market
 * on the planet the player is on
 */
public class MarketActivity extends AppCompatActivity implements MarketAdapter.MarketViewUpdate {
    public static final String EXTRA_SOLAR_SYSTEM_NAME = "solar_system_name";

    private MarketViewModel viewModel;
    private TextView creditDisplay;
    private String solarSystemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        viewModel = ViewModelProviders.of(this).get(MarketViewModel.class);
        creditDisplay = findViewById(R.id.credits);
        solarSystemName = getIntent().getStringExtra(EXTRA_SOLAR_SYSTEM_NAME);
        createView();
        updateCredits();
    }

    /**
     * Initialize all the views for the Market
     */
    private void createView() {
        RecyclerView mainView = findViewById(R.id.items);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mainView.setLayoutManager(manager);
        MarketAdapter adapter = new MarketAdapter(this, viewModel.getMarket(solarSystemName), viewModel.getPlayer(), this);
        mainView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.save();
    }

    /**
     * Update the credits being displayed
     */
    @Override
    public void updateCredits() {
        creditDisplay.setText(getString(R.string.available_credits, viewModel.getPlayerCreditsString()));
    }
}
