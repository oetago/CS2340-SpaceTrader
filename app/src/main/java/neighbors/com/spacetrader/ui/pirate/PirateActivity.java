package neighbors.com.spacetrader.ui.pirate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import neighbors.com.spacetrader.R;
import neighbors.com.spacetrader.model.RandomEvent;
import neighbors.com.spacetrader.ui.universe.UniverseActivity;


public class PirateActivity extends AppCompatActivity {

    public static final String EXTRA_SOLAR_SYSTEM_NAME = "solar_system_name";
    private final String fRunMessage = "You aren't fast enough and the pirate takes some of your credits";
    private final String fFightMessage = "You sustain heavy damage and have to pay for repairs";
    private final String sRunMessage = "You manage to get away";
    private final String sFightMessage = "You defeat the pirate and take some of his booty";
    private PirateViewModel viewModel;
    private Button fightButton, runButton;
    private TextView message;
    private String solarSystemName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pirate);
        solarSystemName = getIntent().getStringExtra(EXTRA_SOLAR_SYSTEM_NAME);
        viewModel = ViewModelProviders.of(this).get(PirateViewModel.class);

        fightButton = findViewById(R.id.fightButton);
        runButton = findViewById(R.id.runButton);
        message = findViewById(R.id.pmessage);
        message.setText(RandomEvent.PIRATE.getMessage());

        fightButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (viewModel.fight()) {
                    display("SUCCESS!", sFightMessage);
                } else {
                    display("FAILURE!", fFightMessage);
                }
            }
        });

        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewModel.run()) {
                    display("SUCCESS!", sRunMessage);
                } else {
                    display("FAILURE!", fRunMessage);
                }
            }
        });
    }

    private void display(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Travel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(PirateActivity.this, UniverseActivity.class);
                        intent.putExtra(EXTRA_SOLAR_SYSTEM_NAME, solarSystemName); //Code to add name extra for next activity
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        finish();
                        startActivity(intent);
                    }
                })
                .create()
                .show();
    }

}
