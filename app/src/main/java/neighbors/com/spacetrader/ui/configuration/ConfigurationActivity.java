package neighbors.com.spacetrader.ui.configuration;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import neighbors.com.spacetrader.R;
import neighbors.com.spacetrader.model.Difficulty;
import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.model.SkillCategory;
import neighbors.com.spacetrader.model.Universe;


/**
 * Activity to allow user to create new Player with stats, and difficulty
 */
public class ConfigurationActivity extends AppCompatActivity {

    private EditConfigurationViewModel editConfigurationViewModel;
    private Spinner difficultySpinner;
    private EditText nameField;
    private int[] skillsArray = new int[4];
    private Button accept_button, nPilot, pPilot, nEngineer, pEngineer, nTrader, pTrader, nFighter, pFighter;
    private int availablePoints = 16;
    private int pilotPoints, fighterPoints, engineerPoints, traderPoints = 0;
    private TextView displayPoints, displayPilot, displayEngineer, displayFighter, displayTrader;
    private Universe world;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        editConfigurationViewModel = ViewModelProviders.of(this).get(EditConfigurationViewModel.class);

        difficultySpinner = findViewById(R.id.difficulty_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, Player.legalDifficulty);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        nameField = findViewById(R.id.name_field); //Instantiates name field

        displayPoints = findViewById(R.id.point_display);
        displayPoints.setText("Available Skill Points: " + availablePoints);

        displayPilot = findViewById(R.id.pilot_points);
        displayPilot.setText("Pilot: " + pilotPoints);

        displayTrader = findViewById(R.id.trader_points);
        displayTrader.setText("Trader: " + traderPoints);

        displayEngineer = findViewById(R.id.engineer_points);
        displayEngineer.setText("Engineer: " + engineerPoints);

        displayFighter = findViewById(R.id.fighter_points);
        displayFighter.setText("Fighter: " + fighterPoints);

        nPilot = findViewById(R.id.nPilot);
        nPilot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pilotPoints == 0) {
                    Toast.makeText(ConfigurationActivity.this, "Can't have negative points", Toast.LENGTH_LONG).show();
                } else {
                    pilotPoints--;
                    availablePoints++;
                }
                displayPoints.setText("Available Skill Points: " + availablePoints);
                displayPilot.setText("Pilot: " + pilotPoints);
            }
        });

        pPilot = findViewById(R.id.pPilot);
        pPilot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (availablePoints == 0) {
                    Toast.makeText(ConfigurationActivity.this, "Can't have more than 16", Toast.LENGTH_LONG).show();
                } else {
                    pilotPoints++;
                    availablePoints--;
                }
                displayPoints.setText("Available Skill Points: " + availablePoints);
                displayPilot.setText("Pilot: " + pilotPoints);
            }
        });

        nTrader = findViewById(R.id.nTrader);
        nTrader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (traderPoints == 0) {
                    Toast.makeText(ConfigurationActivity.this, "Can't have negative points", Toast.LENGTH_LONG).show();
                } else {
                    traderPoints--;
                    availablePoints++;
                }
                displayPoints.setText("Available Skill Points: " + availablePoints);
                displayTrader.setText("Trader: " + traderPoints);

            }
        });

        pTrader = findViewById(R.id.pTrader);
        pTrader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (availablePoints == 0) {
                    Toast.makeText(ConfigurationActivity.this, "Can't have more than 16", Toast.LENGTH_LONG).show();
                } else {
                    traderPoints++;
                    availablePoints--;
                }
                displayPoints.setText("Available Skill Points: " + availablePoints);
                displayTrader.setText("Trader: " + traderPoints);
            }
        });

        nFighter = findViewById(R.id.nFighter);
        nFighter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fighterPoints == 0) {
                    Toast.makeText(ConfigurationActivity.this, "Can't have negative points", Toast.LENGTH_LONG).show();
                } else {
                    fighterPoints--;
                    availablePoints++;
                }
                displayPoints.setText("Available Skill Points: " + availablePoints);
                displayFighter.setText("Fighter: " + fighterPoints);
            }
        });

        pFighter = findViewById(R.id.pFighter);
        pFighter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (availablePoints == 0) {
                    Toast.makeText(ConfigurationActivity.this, "Can't have more than 16", Toast.LENGTH_LONG).show();
                } else {
                    fighterPoints++;
                    availablePoints--;
                }
                displayPoints.setText("Available Skill Points: " + availablePoints);
                displayFighter.setText("Fighter: " + fighterPoints);
            }
        });

        nEngineer = findViewById(R.id.nEngineer);
        nEngineer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (engineerPoints == 0) {
                    Toast.makeText(ConfigurationActivity.this, "Can't have negative points", Toast.LENGTH_LONG).show();
                } else {
                    engineerPoints--;
                    availablePoints++;
                }
                displayPoints.setText("Available Skill Points: " + availablePoints);
                displayEngineer.setText("Engineer: " + engineerPoints);
            }
        });

        pEngineer = findViewById(R.id.pEngineer);
        pEngineer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (availablePoints == 0) {
                    Toast.makeText(ConfigurationActivity.this, "Can't have more than 16", Toast.LENGTH_LONG).show();
                } else {
                    engineerPoints++;
                    availablePoints--;
                }
                displayPoints.setText("Available Skill Points: " + availablePoints);
                displayEngineer.setText("Engineer: " + engineerPoints);
            }
        });

        accept_button = findViewById(R.id.accept_button); //Instantiates accept button

        accept_button.setOnClickListener(new View.OnClickListener() { //Sets listener for accept button and what to do on click
            @Override
            public void onClick(View v) {
                world = new Universe();
                savePlayer();
            }
        });
    }

    /**
     * Talk with viewModel to save Player
     */
    private void savePlayer() {
        Player player = new Player();
        player.setCharacterName(nameField.getText().toString());
        player.setDifficulty(Difficulty.values()[difficultySpinner.getSelectedItemPosition()]);
        player.assignSkillPoints(SkillCategory.PILOT, pilotPoints);
        player.assignSkillPoints(SkillCategory.FIGHTER, fighterPoints);
        player.assignSkillPoints(SkillCategory.TRADER, traderPoints);
        player.assignSkillPoints(SkillCategory.ENGINEER, engineerPoints);
        editConfigurationViewModel.savePlayer(player);
        editConfigurationViewModel.makeUniverse(world);
    }

}
