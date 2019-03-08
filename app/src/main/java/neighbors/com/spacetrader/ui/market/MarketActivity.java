package neighbors.com.spacetrader.ui.market;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;
import neighbors.com.spacetrader.R;

public class MarketActivity extends AppCompatActivity {
    private MarketViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        viewModel = ViewModelProviders.of(this).get(MarketViewModel.class);
    }
}
