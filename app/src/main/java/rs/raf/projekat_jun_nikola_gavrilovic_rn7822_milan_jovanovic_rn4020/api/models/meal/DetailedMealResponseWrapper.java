package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal;

import java.util.List;

public class DetailedMealResponseWrapper {
    private List<DetailedMealResponse> meals;

    public DetailedMealResponseWrapper() {
    }

    public List<DetailedMealResponse> getMeals() {
        return meals;
    }

    public void setMeals(List<DetailedMealResponse> meals) {
        this.meals = meals;
    }
}
