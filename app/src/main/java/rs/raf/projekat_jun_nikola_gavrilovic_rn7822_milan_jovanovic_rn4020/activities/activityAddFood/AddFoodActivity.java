package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activityAddFood;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.AppState;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activityAddFood.models.ChosenFoodModel;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.adapter.FoodAdapter;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.models.Food;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.calorie.CalorieResponse;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.DetailedMealResponseWrapper;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.MealResponse;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.MealResponseWrapper;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.providers.CalorieProvider;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.providers.MealProvider;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.entities.MealEntity;

public class AddFoodActivity extends AppCompatActivity implements FoodAdapter.OnFoodClickListener {

    private Spinner kategorijaSpinner;
    private TextView dateButton;
    private Spinner vrstaPretrageSpinner;
    private RecyclerView svaHranaRecyclerView;
    private TextView odabranaHranaTextView;
    private Button odaberiButton;

    private FoodAdapter foodAdapter;
    private List<Food> foodList;

    private Calendar selectedDate;

    private float chosenCalories = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfood);

        kategorijaSpinner = findViewById(R.id.kategorijaSpinner);
        dateButton = findViewById(R.id.dateButton);
        vrstaPretrageSpinner = findViewById(R.id.vrstaPretrageSpinner);
        svaHranaRecyclerView = findViewById(R.id.svaHranaRecyclerView);
        odabranaHranaTextView = findViewById(R.id.odabranaHranaTextView);
        odaberiButton = findViewById(R.id.odaberiButton);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.meal_categories,
                android.R.layout.simple_spinner_item
        );
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kategorijaSpinner.setAdapter(categoryAdapter);

        ArrayAdapter<CharSequence> storageTypeAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.food_storage_type,
                android.R.layout.simple_spinner_item
        );
        storageTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vrstaPretrageSpinner.setAdapter(storageTypeAdapter);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String dateString = sdf.format(new Date());
        dateButton.setText(dateString);
        selectedDate = Calendar.getInstance();


        foodList = new ArrayList<>();
        /*foodList.add(new Food("Hamburger", "Brza hrana", 500));
        foodList.add(new Food("Salata", "Zdrava hrana", 200));
        foodList.add(new Food("Pica", "Italijanska hrana", 800));*/

        foodAdapter = new FoodAdapter(foodList, this);
        svaHranaRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        svaHranaRecyclerView.setAdapter(foodAdapter);

        fetchData(vrstaPretrageSpinner.getSelectedItem().toString());

        odaberiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("foodName", odabranaHranaTextView.getText().toString());
                data.putExtra("category", kategorijaSpinner.getSelectedItem().toString());
                data.putExtra("calories", chosenCalories);
                data.putExtra("date", selectedDate.getTime().getTime());

                setResult(RESULT_OK, data);
                finish();
            }
        });

        vrstaPretrageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fetchData(vrstaPretrageSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }

    @Override
    public void onFoodClick(Food food) {
        odabranaHranaTextView.setText(food.getIme());
        chosenCalories = food.getCalories();
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddFoodActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectedDate = Calendar.getInstance();
                selectedDate.set(Calendar.YEAR, year);
                selectedDate.set(Calendar.MONTH, monthOfYear);
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String dateString = sdf.format(selectedDate.getTime());
                dateButton.setText(dateString);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    private void fetchData(String type) {
        if(type.equalsIgnoreCase("database")){
            List<MealEntity> savedMenu = AppState.getInstance().getDb().mealDao().findAll();
            foodList = new ArrayList<>();
            for(MealEntity meal: savedMenu) {
                foodList.add(new Food(String.valueOf(meal.getId()), meal.getStrMeal(), meal.getStrMealThumb(), meal.getCalories()));
            }
            foodAdapter.setItems(foodList);
            foodAdapter.notifyDataSetChanged();
        } else if(type.equalsIgnoreCase("api")) {
            MealProvider mealProvider = new MealProvider();
            CalorieProvider calorieProvider = new CalorieProvider();
            foodList = new ArrayList<>();
            mealProvider.getMealService().fetchMealsByCategory("Beef").enqueue(new Callback<MealResponseWrapper>() {
                @Override
                public void onResponse(Call<MealResponseWrapper> call, Response<MealResponseWrapper> response) {
                    MealResponseWrapper mealResponseWrapper = response.body();
                    if (mealResponseWrapper == null || mealResponseWrapper.getMeals() == null) {
                        return;
                    }

                    for (MealResponse c : mealResponseWrapper.getMeals()) {
                        Food food = new Food(c.getIdMeal(), c.getStrMeal(), c.getStrMealThumb(), 0f);

                        mealProvider.getMealService().fetchMealById(food.getId()).enqueue(new Callback<DetailedMealResponseWrapper>() {
                            @Override
                            public void onResponse(Call<DetailedMealResponseWrapper> call, Response<DetailedMealResponseWrapper> response) {
                                if(response.body() == null || response.body().getMeals() == null){
                                    return;
                                }

                                calorieProvider.getCalorieService().fetchCaloriesForMeal(response.body().getMeals().get(0).getSastojci()).enqueue(new Callback<List<CalorieResponse>>() {
                                    @Override
                                    public void onResponse(Call<List<CalorieResponse>> call, Response<List<CalorieResponse>> response) {
                                        if(response.body() == null){
                                            return;
                                        }
                                        float calories = 0;
                                        for(CalorieResponse calorieResponse: response.body()){
                                                calories += calorieResponse.getCalories();
                                        }
                                        food.setCalories(calories);
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

                        foodList.add(food);
                    }
                    foodAdapter.setItems(foodList);
                    foodAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<MealResponseWrapper> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
        }
    }
}
