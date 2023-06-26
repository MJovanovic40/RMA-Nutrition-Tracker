package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.foodActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activitySaveFood.SaveFoodActivity;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.calorie.CalorieResponse;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.DetailedMealResponse;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.DetailedMealResponseWrapper;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.providers.CalorieProvider;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.providers.MealProvider;

public class FoodActivity extends AppCompatActivity {

    private MealProvider mealProvider = new MealProvider();
    private CalorieProvider calorieProvider = new CalorieProvider();

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

    private String imageLink;

    private float calorieValue;

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

        //Fetch food based on id
        this.mealProvider.getMealService().fetchMealById(intent.getStringExtra("foodId")).enqueue(new Callback<DetailedMealResponseWrapper>() {
            @Override
            public void onResponse(Call<DetailedMealResponseWrapper> call, Response<DetailedMealResponseWrapper> response) {
                assert response.body() != null;
                DetailedMealResponse meal = response.body().getMeals().get(0);

                categoryTextView.setText(meal.getStrCategory());
                oblastTextView.setText(meal.getStrArea());
                //foodImageView.setImageURI(Uri.parse(meal.getStrMealThumb()));
                Picasso.get().load(meal.getStrMealThumb()).into(foodImageView);
                instructionsTextView.setText(meal.getStrInstructions());
                tagsTextView.setText(meal.getStrTags());
                videoLinkTextView.setText(meal.getStrYoutube());
                videoLinkTextView.setMovementMethod(LinkMovementMethod.getInstance());
                ingredientsTextView.setText(meal.getSastojci().replaceAll(", ", ",\n"));

                imageLink = meal.getStrMealThumb();

                calorieProvider.getCalorieService().fetchCaloriesForMeal(meal.getSastojci()).enqueue(new Callback<List<CalorieResponse>>() {
                    @Override
                    public void onResponse(Call<List<CalorieResponse>> call, Response<List<CalorieResponse>> response) {
                        List<CalorieResponse> calorieResponseList = response.body();

                        float calories = 0;

                        if(calorieResponseList == null || calorieResponseList.size() == 0) {
                            return;
                        }

                        for(CalorieResponse calorie: calorieResponseList) {
                            calories += calorie.getCalories();
                        }
                        String caloriesString = String.valueOf(calories) + " Calories";
                        caloriesTextView.setText(caloriesString);
                        calorieValue = calories;
                    }

                    @Override
                    public void onFailure(Call<List<CalorieResponse>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<DetailedMealResponseWrapper> call, Throwable t) {

            }
        });
        /**/

        proveriPolja();

        saveButton.setOnClickListener(v -> {
            Intent intentSave = new Intent(FoodActivity.this, SaveFoodActivity.class);
            if(!(foodNameTextView.getText().equals("")) && imageLink != null){
                intentSave.putExtra("foodName", foodNameTextView.getText());
                intentSave.putExtra("image", imageLink);
                intentSave.putExtra("foodId", getIntent().getStringExtra("foodId"));
                intentSave.putExtra("calories", calorieValue);
            }
            startActivity(intentSave);
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

