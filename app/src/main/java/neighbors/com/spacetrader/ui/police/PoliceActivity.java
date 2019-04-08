package neighbors.com.spacetrader.ui.police;

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

public class PoliceActivity extends AppCompatActivity {

    private static final String EXTRA_SOLAR_SYSTEM_NAME = "solar_system_name";
    private final String tFail = "Your smooth talking fails and the cop confiscates your contraband";
    private final String tSucc = "You talk your way out of a ticket";
    private final String rFail = "The cop catches you and gives you a ticket";
    private final String rSucc = "You manage to escape";
    private PoliceViewModel viewModel;
    private String solarSystemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);
        viewModel = ViewModelProviders.of(this).get(PoliceViewModel.class);
        solarSystemName = getIntent().getStringExtra(EXTRA_SOLAR_SYSTEM_NAME);

        Button runButton = findViewById(R.id.run);
        Button talkButton = findViewById(R.id.talk);
        TextView message = findViewById(R.id.message);
        message.setText(RandomEvent.POLICE.getMessage());

        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewModel.run()) {
                    display("SUCCESS!", rSucc);
                } else {
                    display("FAILURE!", rFail);
                }
            }
        });

        talkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewModel.talk()) {
                    display("SUCCESS!", tSucc);
                } else {
                    display("FAILURE!", tFail);
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
                        Intent intent = new Intent(PoliceActivity.this, UniverseActivity.class);
                        intent.putExtra(EXTRA_SOLAR_SYSTEM_NAME, solarSystemName); //Code to add name extra for next activity
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .create()
                .show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.save();
    }
}
