package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.DetailedMealResponseWrapper;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.MealResponseWrapper;

public interface MealService {
    @GET("/api/json/v1/1/filter.php")
    Call<MealResponseWrapper> fetchMealsByCategory(@Query("c") String category);

    @GET("/api/json/v1/1/lookup.php")
    Call<DetailedMealResponseWrapper> fetchMealById(@Query("i") String id);
}
