package neighbors.com.spacetrader.ui.configuration;

import android.content.Intent;
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
import neighbors.com.spacetrader.model.SkillType;
import neighbors.com.spacetrader.ui.universe.UniverseActivity;


/**
 * Activity to allow user to create new Player with stats, and difficulty
 */
public class ConfigurationActivity extends AppCompatActivity {

    private EditConfigurationViewModel viewModel;
    private Spinner difficultySpinner;
    private EditText nameField;

    private Button acceptButton, nPilot, pPilot, nEngineer, pEngineer, nTrader, pTrader, nFighter, pFighter;
    private TextView displayPoints, displayPilot, displayEngineer, displayFighter, displayTrader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        viewModel = ViewModelProviders.of(this).get(EditConfigurationViewModel.class);

        difficultySpinner = findViewById(R.id.difficulty_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, Difficulty.stringValues());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        nameField = findViewById(R.id.name_field);

        displayPoints = findViewById(R.id.point_display);
        displayPilot = findViewById(R.id.pilot_points);
        displayTrader = findViewById(R.id.trader_points);
        displayEngineer = findViewById(R.id.engineer_points);
        displayFighter = findViewById(R.id.fighter_points);
        setDefaultPointTextViews();

        nPilot = findViewById(R.id.nPilot);
        pPilot = findViewById(R.id.pPilot);

        nTrader = findViewById(R.id.nTrader);
        pTrader = findViewById(R.id.pTrader);

        nFighter = findViewById(R.id.nFighter);
        pFighter = findViewById(R.id.pFighter);

        nEngineer = findViewById(R.id.nEngineer);
        pEngineer = findViewById(R.id.pEngineer);

        setNegativeOnClicks();
        setPositiveOnClicks();

        acceptButton = findViewById(R.id.accept_button); //Instantiates accept button

        acceptButton.setOnClickListener(new View.OnClickListener() { //Sets listener for accept button and what to do on click
            @Override
            public void onClick(View v) {
                if (nameField.getText().toString().isEmpty()) {
                    Toast.makeText(ConfigurationActivity.this, "Player name can't be empty!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (viewModel.getAvailablePoints() == 0) {
                    savePlayer();
                    startActivity(new Intent(ConfigurationActivity.this, UniverseActivity.class));
                } else {
                    Toast.makeText(ConfigurationActivity.this, "Must assign all 16 points!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    /**
     * Sets all the textViews for skills on onCreate
     */
    private void setDefaultPointTextViews() {
        displayPoints.setText(getString(R.string.available_skill_points, viewModel.getAvailablePoints()));
        displayPilot.setText(getString(R.string.skill_config_text, SkillType.PILOT.getName(), viewModel.getSkillPoints(SkillType.PILOT)));
        displayEngineer.setText(getString(R.string.skill_config_text, SkillType.ENGINEER.getName(), viewModel.getSkillPoints(SkillType.ENGINEER)));
        displayTrader.setText(getString(R.string.skill_config_text, SkillType.TRADER.getName(), viewModel.getSkillPoints(SkillType.TRADER)));
        displayFighter.setText(getString(R.string.skill_config_text, SkillType.FIGHTER.getName(), viewModel.getSkillPoints(SkillType.FIGHTER)));
    }

    /**
     * Set onClickListeners for decrement skill buttons
     */
    private void setNegativeOnClicks() {
        nPilot.setOnClickListener(new NegativeOnClickListener(SkillType.PILOT, displayPilot));
        nTrader.setOnClickListener(new NegativeOnClickListener(SkillType.TRADER, displayTrader));
        nFighter.setOnClickListener(new NegativeOnClickListener(SkillType.FIGHTER, displayFighter));
        nEngineer.setOnClickListener(new NegativeOnClickListener(SkillType.ENGINEER, displayEngineer));
    }

    /**
     * Set onClickListeners for increment skill buttons
     */
    private void setPositiveOnClicks() {
        pPilot.setOnClickListener(new PositiveOnClickListener(SkillType.PILOT, displayPilot));
        pTrader.setOnClickListener(new PositiveOnClickListener(SkillType.TRADER, displayTrader));
        pFighter.setOnClickListener(new PositiveOnClickListener(SkillType.FIGHTER, displayFighter));
        pEngineer.setOnClickListener(new PositiveOnClickListener(SkillType.ENGINEER, displayEngineer));
    }

    /**
     * Talk with viewModel to save Player
     */
    private void savePlayer() {
        Player player = new Player();
        player.setCharacterName(nameField.getText().toString());
        player.setDifficulty(Difficulty.values()[difficultySpinner.getSelectedItemPosition()]);
        player.setSkillPoints(viewModel.getSkills());
        viewModel.savePlayer(player);
    }

    /**
     * Base onClick to hold the skillType and TextView to display
     */
    private class BaseOnClickListener {
        SkillType skillType;
        TextView display;

        BaseOnClickListener(SkillType skillType, TextView display) {
            this.skillType = skillType;
            this.display = display;
        }

        void updateTextView(String message) {
            if (message != null) {
                Toast.makeText(ConfigurationActivity.this, message, Toast.LENGTH_LONG).show();
            }
            displayPoints.setText(getString(R.string.available_skill_points,
                    viewModel.getAvailablePoints()));
            display.setText(getString(R.string.skill_config_text, skillType.getName(), viewModel.getSkillPoints(skillType)));
        }

    }

    /**
     * Will decrement skill and update views
     */
    private class NegativeOnClickListener extends BaseOnClickListener implements View.OnClickListener {

        NegativeOnClickListener(SkillType skillType, TextView display) {
            super(skillType, display);
        }

        @Override
        public void onClick(View v) {
            String message = viewModel.decrementSkill(skillType);
            updateTextView(message);
        }

    }

    /**
     * Will increment skill and update views
     */
    private class PositiveOnClickListener extends BaseOnClickListener implements View.OnClickListener {

        PositiveOnClickListener(SkillType skillType, TextView display) {
            super(skillType, display);
        }

        @Override
        public void onClick(View v) {
            String message = viewModel.incrementSkill(skillType);
            updateTextView(message);
        }

    }

}
