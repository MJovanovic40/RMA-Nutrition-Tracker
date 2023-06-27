package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activitySavedFoodEdit;

import androidx.lifecycle.ViewModel;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.entities.MealEntity;

public class EditFoodViewModel extends ViewModel {

    private MealEntity mealEntity;

    public void setMealEntity(MealEntity mealEntity) {
        this.mealEntity = mealEntity;
    }

    public MealEntity getMealEntity() {
        return mealEntity;
    }
}
