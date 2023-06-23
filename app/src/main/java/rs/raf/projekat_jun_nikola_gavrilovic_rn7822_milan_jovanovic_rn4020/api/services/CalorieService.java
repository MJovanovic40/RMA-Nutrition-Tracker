package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.calorie.CalorieResponse;

public interface CalorieService {
    @Headers({
            "X-Api-Key: nBv3rWzRmnvOl4JIX2BAgQ==IfG36H4xHeFQtgW9"
    })
    @GET("/v1/nutrition")
    public Call<List<CalorieResponse>> fetchCaloriesForMeal(@Query("query") String mealName);
}
