package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activitySavedFoodEdit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.AppState;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activitySaveFood.SaveFoodActivity;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.HomeActivity;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.entities.MealEntity;

public class EditFoodActivity extends AppCompatActivity implements IPickResult {

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

    private String mealImage;



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

        MealEntity meal = AppState.getInstance().getDb().mealDao().find(Integer.parseInt(getIntent().getStringExtra("menuFoodId")));

        mealImage = meal.getStrMealThumb();
        Picasso.get().load(mealImage).into(foodImageView);

        foodNameEditText.setText(meal.getStrMeal());
        categoryEditText.setText(meal.getMealCategory());
        oblastEditText.setText(meal.getStrArea());

        caloriesEditText.setText(String.valueOf(meal.getCalories()));
        instructionsEditText.setText(meal.getStrInstructions());
        tagsEditText.setText(meal.getStrTags());
        videoLinkEditText.setText(meal.getStrYoutube());
        ingredientsEditText.setText(meal.getSastojci());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meal.setStrMealThumb(mealImage);
                meal.setStrMeal(foodNameEditText.getText().toString());
                meal.setMealCategory(categoryEditText.getText().toString());
                meal.setStrArea(oblastEditText.getText().toString());
                meal.setStrInstructions(instructionsEditText.getText().toString());
                meal.setStrTags(tagsEditText.getText().toString());
                meal.setStrYoutube(videoLinkEditText.getText().toString());

                AppState.getInstance().getDb().mealDao().update(meal);

                //onBackPressed();
                //startActivity(new Intent(EditFoodActivity.this, HomeActivity.class));
                finish();
                Toast.makeText(EditFoodActivity.this, "Izmene uspesno sacuvane!", Toast.LENGTH_SHORT).show();
            }
        });

        foodImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                PickImageDialog.build(new PickSetup()).show(EditFoodActivity.this);
            }
        });
    }

    @Override
    public void onPickResult(PickResult r) {
            if (r.getError() == null) {
                Picasso.get().load(r.getUri()).into(foodImageView);
                mealImage = r.getUri().toString();
            } else {
                Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
            }
    }
}
