package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models.Category;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.category.CategoryResponse;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.category.CategoryResponseWrapper;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.providers.MealProvider;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<Category>> categoryListLiveData;

    public HomeViewModel() {
        categoryListLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Category>> getCategoryListLiveData() {
        return categoryListLiveData;
    }

    public void fetchCategories() {
        MealProvider mealProvider = new MealProvider();
        mealProvider.getCategoryService().fetchAllCategories().enqueue(new Callback<CategoryResponseWrapper>() {
            @Override
            public void onResponse(Call<CategoryResponseWrapper> call, Response<CategoryResponseWrapper> response) {
                CategoryResponseWrapper categoryResponses = response.body();
                if (categoryResponses != null) {
                    List<Category> categories = new ArrayList<>();
                    for (CategoryResponse c : categoryResponses.getCategories()) {
                        Category category = new Category(c.getStrCategoryThumb(), c.getStrCategory(), c.getStrCategoryDescription());
                        categories.add(category);
                    }
                    categoryListLiveData.setValue(categories);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponseWrapper> call, Throwable t) {
            }
        });
    }

}
