package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.adapter.FoodAdapter;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.foodActivity.FoodActivity;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.enteties.Food;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.MealResponse;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.MealResponseWrapper;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.providers.MealProvider;

public class CategoryFoodActivity extends AppCompatActivity {

    private RecyclerView foodRecyclerView;
    private FoodAdapter foodAdapter;
    private EditText searchEditText;
    private List<Food> foodList;
    private TextView categoryTitleTextView;

    private MealProvider mealProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryfood);

        mealProvider = new MealProvider();

        String categoryTitle = getIntent().getStringExtra("categoryName");

        categoryTitleTextView = findViewById(R.id.categoryTitleTextView);
        categoryTitleTextView.setText(categoryTitle);

        foodRecyclerView = findViewById(R.id.foodRecyclerView);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchEditText = findViewById(R.id.searchEditText);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                performSearch(editable.toString());
            }
        });

        foodList = new ArrayList<>();
        foodAdapter = new FoodAdapter(foodList, new FoodAdapter.OnFoodClickListener() {
            @Override
            public void onFoodClick(Food food) {
                Intent intent = new Intent(CategoryFoodActivity.this, FoodActivity.class);
                intent.putExtra("foodName", food.getIme());
                startActivity(intent);
                Toast.makeText(CategoryFoodActivity.this, "Nestooooooo", Toast.LENGTH_SHORT).show();
            }
        });
        foodRecyclerView.setAdapter(foodAdapter);

        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String searchQuery = searchEditText.getText().toString().trim();
                performSearch(searchQuery);
                return true;
            }
            return false;
        });

        mealProvider.getMealService().fetchMealsByCategory(categoryTitle).enqueue(new Callback<MealResponseWrapper>() {
            @Override
            public void onResponse(Call<MealResponseWrapper> call, Response<MealResponseWrapper> response) {
                MealResponseWrapper mealResponseWrapper = response.body();
                if (mealResponseWrapper == null) {
                    return;
                }

                for (MealResponse c : mealResponseWrapper.getMeals()) {
                    Food food = new Food(c.getStrMeal(), "No desc");
                    foodList.add(food);
                }
                updateAdapter(foodList);
            }

            @Override
            public void onFailure(Call<MealResponseWrapper> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    private void updateAdapter(List<Food> foodList) {
        foodAdapter.setItems(foodList);
    }

    private void performSearch(String searchQuery) {
        if (foodList == null) return;

        List<Food> satisfiedFoodList = new ArrayList<>();

        for (Food f : foodList) {
            if (f.getIme().toLowerCase().startsWith(searchQuery.toLowerCase())) {
                satisfiedFoodList.add(f);
            }
        }

        updateAdapter(satisfiedFoodList);
    }
}
