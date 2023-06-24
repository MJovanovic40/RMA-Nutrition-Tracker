package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activitySavedFoodEdit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;

public class EditFoodActivity extends AppCompatActivity {

    private ImageView foodImageView;
    private EditText foodNameEditText;
    private EditText categoryEditText;
    private EditText oblastEditText;
    private EditText caloriesEditText;
    private EditText instructionsEditText;
    private EditText tagsEditText;
    private EditText videoLinkEditText;
    private EditText ingredientsEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editfood);

        foodImageView = findViewById(R.id.foodImageView);
        foodNameEditText = findViewById(R.id.foodNameEditText);
        categoryEditText = findViewById(R.id.categoryEditText);
        oblastEditText = findViewById(R.id.oblastEditText);
        caloriesEditText = findViewById(R.id.caloriesEditText);
        instructionsEditText = findViewById(R.id.instructionsEditText);
        tagsEditText = findViewById(R.id.tagsEditText);
        videoLinkEditText = findViewById(R.id.videoLinkEditText);
        ingredientsEditText = findViewById(R.id.ingredientsEditText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Napraviti logiku da se sve izmeni u bazi
                onBackPressed();
                Toast.makeText(EditFoodActivity.this, "Izmene uspesno sacuvane!", Toast.LENGTH_SHORT).show();
            }
        });

        foodImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // TODO Napraviti logiku za izmenu slike
            }
        });
    }
}
