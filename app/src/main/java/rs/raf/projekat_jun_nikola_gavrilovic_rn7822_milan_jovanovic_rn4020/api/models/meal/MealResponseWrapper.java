package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal;

import java.util.List;

public class MealResponseWrapper {
    private List<MealResponse> meals;

    public MealResponseWrapper() {
    }

    public List<MealResponse> getMeals() {
        return meals;
    }

    public void setMeals(List<MealResponse> meals) {
        this.meals = meals;
    }
}
