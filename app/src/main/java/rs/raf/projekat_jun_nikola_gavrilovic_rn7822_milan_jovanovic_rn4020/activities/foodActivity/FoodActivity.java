package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.foodActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;

public class FoodActivity extends AppCompatActivity {

    private TextView foodNameTextView;
    private TextView categoryTextView;
    private TextView oblastTextView;
    private ImageView foodImageView;
    private TextView instructionsTextView;
    private TextView tagsTextView;
    private TextView videoLinkTextView;
    private TextView ingredientsTextView;
    private Button saveButton;
    private TextView caloriesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        Intent intent = getIntent();

        foodNameTextView = findViewById(R.id.foodNameTextView);
        categoryTextView = findViewById(R.id.categoryTextView);
        oblastTextView = findViewById(R.id.oblastTextView);
        foodImageView = findViewById(R.id.foodImageView);
        instructionsTextView = findViewById(R.id.instructionsTextView);
        tagsTextView = findViewById(R.id.tagsTextView);
        videoLinkTextView = findViewById(R.id.videoLinkTextView);
        ingredientsTextView = findViewById(R.id.ingredientsTextView);
        saveButton = findViewById(R.id.saveButton);
        caloriesTextView = findViewById(R.id.caloriesTextView);

        // TODO: dohvati food data i popuni sve
        if(intent != null){
            foodNameTextView.setText(intent.getStringExtra("foodName"));
        }

        proveriPolja();


        saveButton.setOnClickListener(v -> {
            // TODO: odradi saveButton akciju
        });
    }

    public void proveriPolja(){
        if(categoryTextView.getText().equals("")){
            categoryTextView.setText("Not available");
        }
        if(oblastTextView.getText().equals("")){
            oblastTextView.setText("Not available");
        }
        if(caloriesTextView.getText().equals("")){
            caloriesTextView.setText("Not available");
        }
        if(instructionsTextView.getText().equals("")){
            instructionsTextView.setText("Not available");
        }
        if(tagsTextView.getText().equals("")){
            tagsTextView.setText("Not available");
        }
        if(videoLinkTextView.getText().equals("")){
            videoLinkTextView.setText("Not available");
        }
        if(ingredientsTextView.getText().equals("")){
            ingredientsTextView.setText("Not available");
        }
    }
}

