package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.AppState;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.models.Food;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.entities.MealEntity;

public class MenuViewModel extends ViewModel {
    private MutableLiveData<List<Food>> foodListLiveData;

    public MenuViewModel() {
        foodListLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Food>> getFoodListLiveData() {
        return foodListLiveData;
    }

    public void loadMenuItems() {
        List<Food> menuItems = getMenuItems();
        foodListLiveData.setValue(menuItems);
    }

    private List<Food> getMenuItems() {
        List<MealEntity> savedMenu = AppState.getInstance().getDb().mealDao().findAll();
        List<Food> foodList = new ArrayList<>();
        for(MealEntity meal: savedMenu) {
            foodList.add(new Food(String.valueOf(meal.getId()), meal.getStrMeal(), meal.getStrMealThumb(), meal.getCalories()));
        }
        return foodList;

    }
}
