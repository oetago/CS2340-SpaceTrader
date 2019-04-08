package neighbors.com.spacetrader.ui.universe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.florent37.shapeofview.shapes.CircleView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import neighbors.com.spacetrader.R;
import neighbors.com.spacetrader.model.RandomEvent;
import neighbors.com.spacetrader.model.SolarSystem;
import neighbors.com.spacetrader.ui.market.MarketActivity;

@SuppressWarnings("ALL")
public class UniverseActivity extends AppCompatActivity {

    private static final String EXTRA_SOLAR_SYSTEM_NAME = "solar_system_name";

    private UniverseViewModel viewModel;
    private SolarSystem solarSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe);
        viewModel = ViewModelProviders.of(this).get(UniverseViewModel.class);
        solarSystem = viewModel.getSolarSystem();
        setupActionBar();
        displayUniverse();
    }

    /**
     * Sets up actionbar with name solarsystem name
     */
    private void setupActionBar() {
        getSupportActionBar().setTitle(solarSystem.getName());
    }

    /**
     * Draw all the SolarSystems for the view
     */
    private void displayUniverse() {
        LinearLayout linearLayout = findViewById(R.id.layout);
        for (final SolarSystem system : viewModel.getSolarSystems()) {
            CircleView display = getCircleView();
            setLayout(system, display);
            linearLayout.addView(display);
            display.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewModel.getPlayerFuel() > 0) {
                        displaySolarSystemClickDialog(system);
                    } else {
                        Toast.makeText(UniverseActivity.this, "Not enough fuel to travel!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }

    /**
     * Display dialog when SolarSystem is clicked on
     *
     * @param system the SolarSystem to get the info from
     */
    private void displaySolarSystemClickDialog(final SolarSystem system) {
        new AlertDialog.Builder(this)
                .setTitle(system.getName())
                .setMessage(getSolarSystemMessage(system))
                .setPositiveButton("Travel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel.usePlayerFuel();
                        viewModel.setPlayerPlanet(system.getName());
                        RandomEvent nextEvent = viewModel.getEvent();
                        Intent intent = new Intent(UniverseActivity.this, nextEvent.getActivity());
                        intent.putExtra(EXTRA_SOLAR_SYSTEM_NAME, system.getName()); //Code to add name extra for next activity
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    /**
     * Message for SolarSystem onClick dialog
     *
     * @param system the SolarSystem to get the info from
     * @return the String message to be displayed
     */
    private String getSolarSystemMessage(SolarSystem system) {
        return "Fuel level: " + viewModel.getPlayerFuel() + "\nTechLvl: " + system.getTechLevelName() + "\n" +
                "Resource: " + system.getResourceName();
    }

    /**
     * Sets the layout and display coordinates
     *
     * @param system  system youre working with
     * @param display circle view youre working with
     */
    private void setLayout(SolarSystem system, CircleView display) {
        display.setBorderColor(system.getColor());
        LinearLayout.LayoutParams layout = (LinearLayout.LayoutParams) display.getLayoutParams();
        layout.height = 50;
        layout.width = 50;
        display.setX(system.getLocationX() * 5 + 200);
        display.setY(system.getLocationY() * 6 + 300);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.universe_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.market_menu_button) {
            Intent intent = new Intent(UniverseActivity.this, MarketActivity.class);
            intent.putExtra(MarketActivity.EXTRA_SOLAR_SYSTEM_NAME, solarSystem.getName());
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.save();
    }
}
