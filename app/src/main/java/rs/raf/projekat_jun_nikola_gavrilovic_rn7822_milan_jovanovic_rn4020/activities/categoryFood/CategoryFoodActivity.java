package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.adapter.FoodAdapter;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.foodActivity.FoodActivity;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.calorie.CalorieResponse;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.DetailedMealResponse;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.DetailedMealResponseWrapper;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.providers.CalorieProvider;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.models.Food;
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
    private CalorieProvider calorieProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryfood);

        mealProvider = new MealProvider();
        calorieProvider = new CalorieProvider();

        String origin = getIntent().getStringExtra("origin");

        categoryTitleTextView = findViewById(R.id.categoryTitleTextView);
        categoryTitleTextView.setText("Meal List");

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
                intent.putExtra("foodId", food.getId());

                startActivity(intent);
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

        switch (origin) {
            case "category":
                String categoryTitle = getIntent().getStringExtra("categoryName");
                if(categoryTitle == null){
                    Toast.makeText(this, "Invalid configuration sent.", Toast.LENGTH_SHORT).show();
                    return;
                }
                mealProvider.getMealService().fetchMealsByCategory(categoryTitle).enqueue(new Callback<MealResponseWrapper>() {
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
                                            updateAdapter(foodList);
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
                        updateAdapter(foodList);
                    }

                    @Override
                    public void onFailure(Call<MealResponseWrapper> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });

                break;
            case "search":
                String searchType = getIntent().getStringExtra("searchType");
                String searchQuery = getIntent().getStringExtra("searchQuery");
                if(searchType == null || searchQuery == null) {
                    Toast.makeText(this, "Invalid configuration sent.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(searchType.equals("Meal name")){
                    mealProvider.getMealService().fetchMealsByName(searchQuery).enqueue(new Callback<DetailedMealResponseWrapper>() {
                        @Override
                        public void onResponse(Call<DetailedMealResponseWrapper> call, Response<DetailedMealResponseWrapper> response) {
                            if(response.body() == null || response.body().getMeals() == null) {
                                return;
                            }
                            for(DetailedMealResponse meal: response.body().getMeals()){
                                Food food = new Food(meal.getIdMeal(), meal.getStrMeal(), meal.getStrMealThumb(), 0f);

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
                                        updateAdapter(foodList);
                                    }

                                    @Override
                                    public void onFailure(Call<List<CalorieResponse>> call, Throwable t) {

                                    }
                                });

                                foodList.add(food);
                            }
                            updateAdapter(foodList);
                        }

                        @Override
                        public void onFailure(Call<DetailedMealResponseWrapper> call, Throwable t) {

                        }
                    });
                } else if(searchType.equals("Ingredient")) {
                    mealProvider.getMealService().fetchMealsByIngredient(searchQuery).enqueue(new Callback<MealResponseWrapper>() {
                        @Override
                        public void onResponse(Call<MealResponseWrapper> call, Response<MealResponseWrapper> response) {
                            if(response.body() == null || response.body().getMeals() == null) {
                                return;
                            }
                            for(MealResponse meal: response.body().getMeals()){
                                Food food = new Food(meal.getIdMeal(), meal.getStrMeal(), meal.getStrMealThumb(), 0f);

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
                                                updateAdapter(foodList);
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
                            updateAdapter(foodList);
                        }

                        @Override
                        public void onFailure(Call<MealResponseWrapper> call, Throwable t) {

                        }
                    });
                }
                updateAdapter(foodList);
                break;
            case "filter":
                String filterType = getIntent().getStringExtra("oblast");
                String filterQuery = getIntent().getStringExtra("search");
                String sign = getIntent().getStringExtra("filterZnak");

                String minCalStr = getIntent().getStringExtra("minimumCals");
                float minCal = minCalStr != null && !minCalStr.isEmpty() ? Float.parseFloat(minCalStr) : 0;

                String maxCalStr = getIntent().getStringExtra("maximumCals");
                float maxCal = maxCalStr != null && !minCalStr.isEmpty() ? Float.parseFloat(maxCalStr) : 0;

                String tagsStr = getIntent().getStringExtra("tag");

                List<String> tags = new ArrayList<>();

                if(!tagsStr.isEmpty()){
                    String[] components = tagsStr.split(",");

                    for(String s: components) {
                        tags.add(s);
                    }
                }

                boolean sortAbecedno = getIntent().getBooleanExtra("sortAbecedno", false);

                if(filterType == null || filterQuery == null) {
                    Toast.makeText(this, "Invalid configuration sent", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(filterType.equals("Kategorija")){
                    mealProvider.getMealService().fetchMealsByCategory(filterQuery).enqueue(new Callback<MealResponseWrapper>() {
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

                                        if(!tags.isEmpty()) {
                                            if (response.body().getMeals().get(0).getStrTags() != null) {
                                                List<String> mealTags = new ArrayList<>(Arrays.asList(response.body().getMeals().get(0).getStrTags().split(",")));
                                                for (String s : tags) {
                                                    if (mealTags.stream().noneMatch(s::equalsIgnoreCase)) {
                                                        foodList.remove(food);
                                                        updateAdapter(foodList);
                                                        return;
                                                    }
                                                }
                                            } else {
                                                foodList.remove(food);
                                                updateAdapter(foodList);
                                                return;
                                            }
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
                                                if(!isFilteredByCalories(food, minCal, maxCal, sign)) {
                                                    foodList.remove(food);
                                                }
                                                updateAdapter(foodList);
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
                            if(sortAbecedno) {
                                Collections.sort(foodList, new Comparator<Food>() {
                                    @Override
                                    public int compare(Food food, Food t1) {
                                        return food.getIme().compareTo(t1.getIme());
                                    }
                                });
                            }
                            updateAdapter(foodList);
                        }

                        @Override
                        public void onFailure(Call<MealResponseWrapper> call, Throwable t) {
                            System.out.println(t.getMessage());
                        }
                    });
                } else if(filterType.equals("Sastojci")) {
                    mealProvider.getMealService().fetchMealsByIngredient(filterQuery).enqueue(new Callback<MealResponseWrapper>() {
                        @Override
                        public void onResponse(Call<MealResponseWrapper> call, Response<MealResponseWrapper> response) {
                            if(response.body() == null || response.body().getMeals() == null) {
                                return;
                            }
                            for(MealResponse meal: response.body().getMeals()){
                                Food food = new Food(meal.getIdMeal(), meal.getStrMeal(), meal.getStrMealThumb(), 0f);
                                mealProvider.getMealService().fetchMealById(food.getId()).enqueue(new Callback<DetailedMealResponseWrapper>() {
                                    @Override
                                    public void onResponse(Call<DetailedMealResponseWrapper> call, Response<DetailedMealResponseWrapper> response) {
                                        if(response.body() == null || response.body().getMeals() == null){
                                            return;
                                        }

                                        if(!tags.isEmpty()) {
                                            if (response.body().getMeals().get(0).getStrTags() != null) {
                                                List<String> mealTags = new ArrayList<>(Arrays.asList(response.body().getMeals().get(0).getStrTags().split(",")));
                                                for (String s : tags) {
                                                    if (mealTags.stream().noneMatch(s::equalsIgnoreCase)) {
                                                        foodList.remove(food);
                                                        updateAdapter(foodList);
                                                        return;
                                                    }
                                                }
                                            } else {
                                                foodList.remove(food);
                                                updateAdapter(foodList);
                                                return;
                                            }
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
                                                if(!isFilteredByCalories(food, minCal, maxCal, sign)){
                                                    foodList.remove(food);
                                                }
                                                updateAdapter(foodList);
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
                            if(sortAbecedno) {
                                Collections.sort(foodList, new Comparator<Food>() {
                                    @Override
                                    public int compare(Food food, Food t1) {
                                        return food.getIme().compareTo(t1.getIme());
                                    }
                                });
                            }
                            updateAdapter(foodList);
                        }

                        @Override
                        public void onFailure(Call<MealResponseWrapper> call, Throwable t) {

                        }
                    });
                } else if(filterType.equals("Oblasti")) {
                    mealProvider.getMealService().fetchMealsByArea(filterQuery).enqueue(new Callback<MealResponseWrapper>() {
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

                                        if(!tags.isEmpty()) {
                                            if (response.body().getMeals().get(0).getStrTags() != null) {
                                                List<String> mealTags = new ArrayList<>(Arrays.asList(response.body().getMeals().get(0).getStrTags().split(",")));
                                                for (String s : tags) {
                                                    if (mealTags.stream().noneMatch(s::equalsIgnoreCase)) {
                                                        foodList.remove(food);
                                                        updateAdapter(foodList);
                                                        return;
                                                    }
                                                }
                                            } else {
                                                foodList.remove(food);
                                                updateAdapter(foodList);
                                                return;
                                            }
                                        }
                                        /*if(response.body().getMeals().get(0).getStrTags() != null && tags.size() > 0){
                                            List<String> mealTags = new ArrayList<>(Arrays.asList(response.body().getMeals().get(0).getStrTags().split(",")));
                                            for(String s: tags) {
                                                if(!mealTags.contains(s)){
                                                    return;
                                                }
                                            }
                                        } else if(tags.size() <= 0){
                                            return;
                                        }*/

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
                                                if(!isFilteredByCalories(food, minCal, maxCal, sign)) {

                                                    foodList.remove(food);
                                                }
                                                updateAdapter(foodList);
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

                            if(sortAbecedno) {
                                Collections.sort(foodList, new Comparator<Food>() {
                                    @Override
                                    public int compare(Food food, Food t1) {
                                        return food.getIme().compareTo(t1.getIme());
                                    }
                                });
                            }
                            updateAdapter(foodList);
                        }

                        @Override
                        public void onFailure(Call<MealResponseWrapper> call, Throwable t) {
                            System.out.println(t.getMessage());
                        }
                    });
                }
                break;
        }

    }

    private void updateAdapter(List<Food> foodList) {
        foodAdapter = new FoodAdapter(foodList, new FoodAdapter.OnFoodClickListener() {
            @Override
            public void onFoodClick(Food food) {
                Intent intent = new Intent(CategoryFoodActivity.this, FoodActivity.class);
                intent.putExtra("foodName", food.getIme());
                intent.putExtra("foodId", food.getId());
                System.out.println(food.getId());
                startActivity(intent);
            }
        });
        foodRecyclerView.setAdapter(foodAdapter);
        //foodAdapter.setItems(foodList);
    }

    private boolean isFilteredByCalories(Food food, float minVal, float maxVal, String sign) {
        switch(sign) {
            case ">":
                return minVal > food.getCalories();
            case "<":
                return minVal < food.getCalories();
            case ">= =<":
                return food.getCalories() >= minVal && food.getCalories() <= maxVal;
        }
        return false;
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
