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

/**
 * Activity to allow user to create new Player with stats, and difficulty
 */
public class ConfigurationActivity extends AppCompatActivity {

    private EditConfigurationViewModel editConfigurationViewModel;
    private Spinner difficultySpinner;
    private EditText nameField;
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

        accept_button = findViewById(R.id.accept_button); //Instantiates accept button

        accept_button.setOnClickListener(new View.OnClickListener() { //Sets listener for accept button and what to do on click
            @Override
            public void onClick(View v) {
                String pName = nameField.getText().toString();
                Difficulty pDifficulty = (Difficulty) difficultySpinner.getSelectedItem();

                Toast.makeText(ConfigurationActivity.this, pName, Toast.LENGTH_SHORT).show(); //Making sure text is correct

            }
        });


        //save player with editConfigurationViewModel.save(Player)
    }

}
