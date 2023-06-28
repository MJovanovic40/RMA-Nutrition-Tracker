package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activityAddFood;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.AppState;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.models.Food;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.calorie.CalorieResponse;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.DetailedMealResponseWrapper;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.MealResponse;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.MealResponseWrapper;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.providers.CalorieProvider;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.providers.MealProvider;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.entities.MealEntity;

public class AddFoodViewModel extends ViewModel {
    private MutableLiveData<List<Food>> foodListLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Food>> getFoodListLiveData() {
        return foodListLiveData;
    }

    public void fetchData(String type) {
        if (type.equalsIgnoreCase("database")) {
            // Fetch data from the database and update the LiveData
            List<MealEntity> savedMenu = AppState.getInstance().getDb().mealDao().findAll();
            List<Food> foodList = new ArrayList<>();
            for (MealEntity meal : savedMenu) {
                foodList.add(new Food(String.valueOf(meal.getId()), meal.getStrMeal(), meal.getStrMealThumb(), meal.getCalories()));
            }
            foodListLiveData.setValue(foodList);
        } else if (type.equalsIgnoreCase("api")) {
            // Fetch data from the API and update the LiveData
            MealProvider mealProvider = new MealProvider();
            CalorieProvider calorieProvider = new CalorieProvider();
            List<Food> foodList = new ArrayList<>();

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
                                if (response.body() == null || response.body().getMeals() == null) {
                                    return;
                                }

                                calorieProvider.getCalorieService().fetchCaloriesForMeal(response.body().getMeals().get(0).getSastojci()).enqueue(new Callback<List<CalorieResponse>>() {
                                    @Override
                                    public void onResponse(Call<List<CalorieResponse>> call, Response<List<CalorieResponse>> response) {
                                        if (response.body() == null) {
                                            return;
                                        }
                                        float calories = 0;
                                        for (CalorieResponse calorieResponse : response.body()) {
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
                    foodListLiveData.setValue(foodList);
                }

                @Override
                public void onFailure(Call<MealResponseWrapper> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
        }
    }
}
