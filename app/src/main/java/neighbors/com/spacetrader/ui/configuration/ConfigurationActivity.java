package neighbors.com.spacetrader.ui.configuration;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import neighbors.com.spacetrader.R;
import neighbors.com.spacetrader.model.Difficulty;
import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.model.SkillCategory;


/**
 * Activity to allow user to create new Player with stats, and difficulty
 */
public class ConfigurationActivity extends AppCompatActivity {

    private EditConfigurationViewModel editConfigurationViewModel;
    private Spinner difficultySpinner;
    private EditText nameField;
    private EditText editPilot;
    private EditText editFighter;
    private EditText editTrader;
    private EditText editEngineer;
    private int[] skillsArray = new int[4];
    private Button accept_button;

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

        editPilot = findViewById(R.id.edit_pilot);
        editFighter = findViewById(R.id.edit_fighter);
        editTrader = findViewById(R.id.edit_trader);
        editEngineer = findViewById(R.id.edit_engineer);

        accept_button = findViewById(R.id.accept_button); //Instantiates accept button

        accept_button.setOnClickListener(new View.OnClickListener() { //Sets listener for accept button and what to do on click
            @Override
            public void onClick(View v) {
                if (getPlayerSkillValuesAndValidate()) {
                    savePlayer();
                }
            }
        });
    }

    /**
     * Get player skills and verify from edit Texts
     *
     * @return true if valid, false if not valid
     */
    private boolean getPlayerSkillValuesAndValidate() {
        skillsArray[0] = getIntFromEditText(editPilot);
        skillsArray[1] = getIntFromEditText(editFighter);
        skillsArray[2] = getIntFromEditText(editTrader);
        skillsArray[3] = getIntFromEditText(editEngineer);

        int totalSkill = 0;
        for (int i = 0; i < 4; i++) {
            if (skillsArray[i] < 0) {
                //throw new NumberFormatException("Skill level cannot be less than 0");
            } else if (skillsArray[i] > 16) {
                //throw new NumberFormatException("Skill level cannot be more than 16");
                Toast.makeText(ConfigurationActivity.this, "Skill level cannot be more than 16", Toast.LENGTH_SHORT).show();
                return false;
            }
            totalSkill += skillsArray[i];
        }

        if (totalSkill != 16) {
            Toast.makeText(ConfigurationActivity.this, "Total skills level must add to 16", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    /**
     * Returns an int from EditText
     *
     * @param editText the editText to get int from
     * @return and int
     */
    private int getIntFromEditText(EditText editText) {
        try {
            return Integer.parseInt(editText.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(ConfigurationActivity.this, "Skill level must be an integer", Toast.LENGTH_LONG).show();
        }
        return 0;
    }

    /**
     * Talk with viewModel to save Player
     */
    private void savePlayer() {
        Player player = new Player();
        player.setCharacterName(nameField.getText().toString());
        player.setDifficulty(Difficulty.values()[difficultySpinner.getSelectedItemPosition()]);
        player.assignSkillPoints(SkillCategory.PILOT, skillsArray[0]);
        player.assignSkillPoints(SkillCategory.FIGHTER, skillsArray[1]);
        player.assignSkillPoints(SkillCategory.TRADER, skillsArray[2]);
        player.assignSkillPoints(SkillCategory.ENGINEER, skillsArray[3]);
        editConfigurationViewModel.savePlayer(player);
    }

}
