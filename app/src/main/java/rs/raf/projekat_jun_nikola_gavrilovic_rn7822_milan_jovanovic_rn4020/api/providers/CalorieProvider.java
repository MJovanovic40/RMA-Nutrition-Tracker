package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.providers;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.services.CalorieService;

public class CalorieProvider {

    private CalorieService calorieService;

    public CalorieProvider() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.api-ninjas.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        calorieService = retrofit.create(CalorieService.class);
    }

    public CalorieService getCalorieService() {
        return calorieService;
    }

    public void setCalorieService(CalorieService calorieService) {
        this.calorieService = calorieService;
    }
}
