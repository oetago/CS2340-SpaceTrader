package neighbors.com.spacetrader.ui.universe;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.florent37.shapeofview.shapes.CircleView;

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
        displayUniverse();
    }

    private void displayUniverse() {
        LinearLayout linearLayout = findViewById(R.id.layout);
        for (final SolarSystem system : viewModel.getSolarSystems()) {
            CircleView display = getCircleView();
            setLayout(system, display);
            linearLayout.addView(display);
            display.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MaterialDialog info = new MaterialDialog(UniverseActivity.this);
                    info.title(null, system.getName());
                    info.message(null, "TechLvl: " + system.getSystemTechLevel().getName() + "\n" +
                            "Resource: " + system.getResource().getName(), false, 1F);
                    info.show();
                }
            });
        }

    }

    /**
     * Sets the layout and display coordinates
     *
     * @param system  system youre working with
     * @param display circle view youre working with
     */
    private void setLayout(SolarSystem system, CircleView display) {
        display.setBorderColor(system.getSystemTechLevel().getColor());
        LinearLayout.LayoutParams layout = (LinearLayout.LayoutParams) display.getLayoutParams();
        layout.height = 50;
        layout.width = 50;
        display.setX(system.getLocation().getX() * 5 + 200);
        display.setY(system.getLocation().getY() * 6 + 300);
    }

    /**
     * Set up default circle
     *
     * @return default circle view
     */
    private CircleView getCircleView() {
        CircleView display = new CircleView(this);
        display.setBorderWidth(20);
        display.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        return display;
    }

    /**
     * Returns the Universe ViewModel for other Activities
     * Allows ViewModel to remain private but other methods can access universe
     *
     * @return the ViewModel that contains the Universe
     */
    public UniverseViewModel getUniverseViewModel() {
        return viewModel;
    }

}
