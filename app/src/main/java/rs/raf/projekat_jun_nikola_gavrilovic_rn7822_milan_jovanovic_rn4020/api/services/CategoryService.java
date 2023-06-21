package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.CategoryResponse;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.CategoryResponseWrapper;

public interface CategoryService {

    @GET("/api/json/v1/1/categories.php")
    Call<CategoryResponseWrapper> fetchAllCategories();
}
