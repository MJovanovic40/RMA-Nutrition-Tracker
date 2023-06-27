package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activitySavedFoodEdit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.AppState;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.entities.MealEntity;

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

    private boolean needsRepaint = false;

    @Override
    public void onResume() {
        super.onResume();
        if(needsRepaint){
            recreate();
            needsRepaint = false;
        }

    }

    @Override
    public void onPause(){
        super.onPause();
        needsRepaint = true;
    }

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

        MealEntity meal = AppState.getInstance().getDb().mealDao().find(Integer.parseInt(getIntent().getStringExtra("menuFoodId")));

        Picasso.get().load(meal.getStrMealThumb()).into(foodImageView);
        foodNameTextView.setText(meal.getStrMeal());
        categoryTextView.setText(meal.getMealCategory());
        oblastTextView.setText(meal.getStrArea());

        String strCalories = meal.getCalories() + " Calories";
        caloriesTextView.setText(strCalories);

        instructionsTextView.setText(meal.getStrInstructions());
        tagsTextView.setText(meal.getStrTags());
        videoLinkTextView.setText(meal.getStrYoutube());
        ingredientsTextView.setText(meal.getSastojci());

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavedFoodEditActivity.this, EditFoodActivity.class);
                intent.putExtra("foodName", foodNameTextView.getText());
                intent.putExtra("menuFoodId", getIntent().getStringExtra("menuFoodId"));
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppState.getInstance().getDb().mealDao().delete(meal);

                onBackPressed();
                Toast.makeText(SavedFoodEditActivity.this, "Hrana obrisana iz meni-a!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

