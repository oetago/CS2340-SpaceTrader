package neighbors.com.spacetrader.ui.market;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import neighbors.com.spacetrader.R;
import neighbors.com.spacetrader.model.Good;

public class MarketActivity extends AppCompatActivity {
    private MarketViewModel viewModel;
    private TextView creditDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        viewModel = ViewModelProviders.of(this).get(MarketViewModel.class);
        createView();
        creditDisplay = findViewById(R.id.credits);
        creditDisplay.setText("Available credits: " + String.valueOf(viewModel.getPlayer().getCredits()));
    }

    private void createView() {
        RecyclerView mainView = findViewById(R.id.items);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mainView.setLayoutManager(manager);
        Map<Good, Integer> buyGoods = viewModel.getAllBuyPrices();
        Map<Good, Integer> sellGoods = viewModel.getAllSellPrices();
        MarketAdapter adapter = new MarketAdapter(buyGoods, sellGoods, this);
        mainView.setAdapter(adapter);
    }

    public MarketViewModel getViewModel() {
        return viewModel;
    }

    public void updateCredits() {
        creditDisplay.setText("Available credits: " + String.valueOf(viewModel.getPlayer().getCredits()));
    }
}
