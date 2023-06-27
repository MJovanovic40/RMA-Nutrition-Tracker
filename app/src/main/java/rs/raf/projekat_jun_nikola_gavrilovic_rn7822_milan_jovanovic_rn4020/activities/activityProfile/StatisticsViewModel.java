package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activityProfile;

import androidx.lifecycle.ViewModel;
import java.util.List;
import java.util.Map;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.entities.MealEntity;

public class StatisticsViewModel extends ViewModel {

    private List<MealEntity> meals;
    private Map<Integer, Integer> mealCountData;
    private Map<Integer, Float> caloriesData;

    public void setMeals(List<MealEntity> meals) {
        this.meals = meals;
    }

    public List<MealEntity> getMeals() {
        return meals;
    }

    public void setMealCountData(Map<Integer, Integer> mealCountData) {
        this.mealCountData = mealCountData;
    }

    public Map<Integer, Integer> getMealCountData() {
        return mealCountData;
    }

    public void setCaloriesData(Map<Integer, Float> caloriesData) {
        this.caloriesData = caloriesData;
    }

    public Map<Integer, Float> getCaloriesData() {
        return caloriesData;
    }
}

