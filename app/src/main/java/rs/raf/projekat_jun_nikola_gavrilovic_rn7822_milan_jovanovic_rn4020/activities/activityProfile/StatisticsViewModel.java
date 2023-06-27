package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activityProfile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.Map;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.entities.MealEntity;

public class StatisticsViewModel extends ViewModel {

    private MutableLiveData<List<MealEntity>> mealsLiveData;
    private MutableLiveData<Map<Integer, Integer>> mealCountDataLiveData;
    private MutableLiveData<Map<Integer, Float>> caloriesDataLiveData;

    public StatisticsViewModel() {
        mealsLiveData = new MutableLiveData<>();
        mealCountDataLiveData = new MutableLiveData<>();
        caloriesDataLiveData = new MutableLiveData<>();
    }

    public LiveData<List<MealEntity>> getMealsLiveData() {
        return mealsLiveData;
    }

    public void setMeals(List<MealEntity> meals) {
        mealsLiveData.setValue(meals);
    }

    public LiveData<Map<Integer, Integer>> getMealCountDataLiveData() {
        return mealCountDataLiveData;
    }

    public void setMealCountData(Map<Integer, Integer> mealCountData) {
        mealCountDataLiveData.setValue(mealCountData);
    }

    public LiveData<Map<Integer, Float>> getCaloriesDataLiveData() {
        return caloriesDataLiveData;
    }

    public void setCaloriesData(Map<Integer, Float> caloriesData) {
        caloriesDataLiveData.setValue(caloriesData);
    }
}
