package neighbors.com.spacetrader.ui.universe;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import neighbors.com.spacetrader.R;
import neighbors.com.spacetrader.model.SolarSystem;

public class UniverseActivity extends AppCompatActivity {
    private UniverseViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe);
        viewModel = ViewModelProviders.of(this).get(UniverseViewModel.class);
        MaterialDialog thing = new MaterialDialog(this);
        displayUniverse();
    }

    private void displayUniverse() {
        LinearLayout linearLayout = findViewById(R.id.layout);
        for (SolarSystem system : viewModel.getUniverse().getSolarSystems()) {
            SystemAdapter sAdapter = new SystemAdapter(system, this);
            linearLayout.addView(sAdapter.getView());
        }

    }

}
