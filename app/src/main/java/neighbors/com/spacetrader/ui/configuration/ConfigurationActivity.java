package neighbors.com.spacetrader.ui.configuration;

import android.graphics.Bitmap;
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
                String pName = nameField.getText().toString();

                // Gets values of player skills
                // Pilot
                try {
                    skillsArray[0] = Integer.parseInt(editPilot.getText().toString());
                }
                catch (NumberFormatException e) {
                    Toast.makeText(ConfigurationActivity.this, "Skill level must be an integer", Toast.LENGTH_LONG).show();
                }
                // Fighter
                try {
                    skillsArray[1] = Integer.parseInt(editFighter.getText().toString());
                }
                catch (NumberFormatException e) {
                    Toast.makeText(ConfigurationActivity.this, "Fighter skill level must be an integer", Toast.LENGTH_LONG).show();
                }
                // Trader
                try {
                    skillsArray[2] = Integer.parseInt(editTrader.getText().toString());
                }
                catch (NumberFormatException e) {
                    Toast.makeText(ConfigurationActivity.this, "Trader skill level must be an integer", Toast.LENGTH_LONG).show();
                }
                // Engineer
                try {
                    skillsArray[3] = Integer.parseInt(editEngineer.getText().toString());
                }
                catch (NumberFormatException e) {
                    Toast.makeText(ConfigurationActivity.this, "Engineer skill level must be an integer", Toast.LENGTH_LONG).show();
                }

                int totalSkill = 0;
                for (int i = 0; i < 4; i++) {
                    if (skillsArray[i] < 0) {
                        //throw new NumberFormatException("Skill level cannot be less than 0");
                    } else if (skillsArray[i] > 16) {
                        //throw new NumberFormatException("Skill level cannot be more than 16");
                        Toast.makeText(ConfigurationActivity.this, "Skill level cannot be more than 16", Toast.LENGTH_SHORT).show();
                    }
                    totalSkill += skillsArray[i];
                }

                boolean correctSkills = false;
                if (totalSkill == 16) {
                    Toast.makeText(ConfigurationActivity.this, "Skills Allocated Correctly", Toast.LENGTH_SHORT).show();
                    correctSkills = true;
                } else {
                    Toast.makeText(ConfigurationActivity.this, "Total skills level must add to 16", Toast.LENGTH_LONG).show();
                }

                Difficulty pDifficulty = (Difficulty) difficultySpinner.getSelectedItem();

                if (correctSkills) {
                    String pPlayer =  pName + ", " +
                            "Pilot: " + skillsArray[0] +
                            " Fighter: " + skillsArray[1] +
                            " Trader: " + skillsArray[2] +
                            " Engineer: " + skillsArray[3];

                    Toast.makeText(ConfigurationActivity.this, pPlayer, Toast.LENGTH_SHORT).show(); //Making sure text is correct
                } else {
                    Toast.makeText(ConfigurationActivity.this, "Incorrect Entry", Toast.LENGTH_SHORT);
                }
            }
        });


        //save player with editConfigurationViewModel.save(Player)
    }

}
