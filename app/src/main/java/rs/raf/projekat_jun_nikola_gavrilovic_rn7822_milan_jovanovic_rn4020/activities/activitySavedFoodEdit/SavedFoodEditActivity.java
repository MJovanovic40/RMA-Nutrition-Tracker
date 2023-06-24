package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activitySavedFoodEdit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;

public class SavedFoodEditActivity extends AppCompatActivity {

    private ImageView foodImageView;
    private TextView foodNameTextView;
    private TextView categoryTextView;
    private TextView oblastTextView;
    private TextView caloriesTextView;
    private TextView instructionsTextView;
    private TextView tagsTextView;
    private TextView videoLinkTextView;
    private TextView ingredientsTextView;
    private Button editButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savedfoodedit);

        foodImageView = findViewById(R.id.foodImageView);
        foodNameTextView = findViewById(R.id.foodNameTextView);
        categoryTextView = findViewById(R.id.categoryTextView);
        oblastTextView = findViewById(R.id.oblastTextView);
        caloriesTextView = findViewById(R.id.caloriesTextView);
        instructionsTextView = findViewById(R.id.instructionsTextView);
        tagsTextView = findViewById(R.id.tagsTextView);
        videoLinkTextView = findViewById(R.id.videoLinkTextView);
        ingredientsTextView = findViewById(R.id.ingredientsTextView);
        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavedFoodEditActivity.this, EditFoodActivity.class);
                intent.putExtra("foodName", foodNameTextView.getText());
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Ukloniti item iz baze
                onBackPressed();
                Toast.makeText(SavedFoodEditActivity.this, "Hrana obrisana iz meni-a!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

