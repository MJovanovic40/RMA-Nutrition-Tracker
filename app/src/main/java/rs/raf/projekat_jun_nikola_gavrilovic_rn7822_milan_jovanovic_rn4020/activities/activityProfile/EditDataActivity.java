package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activityProfile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.AppState;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.entities.UserEntity;

public class EditDataActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText ageEditText;
    private EditText heightEditText;
    private EditText weightEditText;
    private EditText genderEditText;
    private EditText activityLevelEditText;
    private Button saveButton;

    UserEntity user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdata);

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        String username = sharedPreferences.getString("User", "");

        user = AppState.getInstance().getDb().userDao().findByUsername(username);

        usernameEditText = findViewById(R.id.usernameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        heightEditText = findViewById(R.id.heightEditText);
        weightEditText = findViewById(R.id.weightEditText);
        genderEditText = findViewById(R.id.genderEditText);
        activityLevelEditText = findViewById(R.id.activityLevelEditText);
        saveButton = findViewById(R.id.saveButton);

        usernameEditText.setText(user.getUsername());
        ageEditText.setText(String.valueOf(user.getAge()));
        heightEditText.setText(String.valueOf(user.getHeight()));
        weightEditText.setText(String.valueOf(user.getWeight()));
        genderEditText.setText(user.getGender());
        activityLevelEditText.setText(user.getActivityLevel());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        String username = usernameEditText.getText().toString().trim();
        String age = ageEditText.getText().toString().trim();
        String height = heightEditText.getText().toString().trim();
        String weight = weightEditText.getText().toString().trim();
        String gender = genderEditText.getText().toString().trim();
        String activityLevel = activityLevelEditText.getText().toString().trim();

        // TODO dodati logiku za dodavanje podataka u bazu
        user.setUsername(username);
        user.setAge(Integer.parseInt(age));
        user.setHeight(Integer.parseInt(height));
        user.setWeight(Float.parseFloat(weight));
        user.setGender(gender);
        user.setActivityLevel(activityLevel);

        AppState.getInstance().getDb().userDao().update(user);

        Toast.makeText(this, "Izmene uspesno sacuvane", Toast.LENGTH_SHORT).show();
        finish();
    }
}
