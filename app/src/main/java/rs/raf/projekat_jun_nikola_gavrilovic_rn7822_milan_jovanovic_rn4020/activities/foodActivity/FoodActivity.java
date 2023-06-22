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

        // TODO: dohvati food data i popuni sve
        if(intent != null){
            foodNameTextView.setText(intent.getStringExtra("foodName"));
        }

        categoryTextView.setText("Category");
        oblastTextView.setText("Field");
        instructionsTextView.setText("Preparation Instructions");
        tagsTextView.setText("Tags");
        videoLinkTextView.setText("Video Link");
        ingredientsTextView.setText("Ingredients");

        saveButton.setOnClickListener(v -> {
            // TODO: odradi saveButton akciju
        });
    }
}

