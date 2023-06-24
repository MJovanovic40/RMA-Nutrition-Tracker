package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.AppState;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.adapter.FoodAdapter;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.foodActivity.FoodActivity;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.calorie.CalorieResponse;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.DetailedMealResponse;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.DetailedMealResponseWrapper;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.MealResponse;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.MealResponseWrapper;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.providers.CalorieProvider;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.providers.MealProvider;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.enteties.Food;

public class FilterFragment extends Fragment {

    private EditText minimumCalsEditText;
    private Spinner sortCalsSpinner;
    private EditText maximumCalsEditText;
    private CheckBox sortCheckBox;
    private Spinner tagDropdownMenu;
    private SearchView searchEditText;
    private Spinner filterSpinner;
    private RecyclerView foodRecyclerView;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;

    private static final int PAGE_SIZE = 20;
    private int currentPage = 0;
    private boolean isLastPage = false;
    private boolean isLoading = false;

    private MealProvider mealProvider;
    private CalorieProvider calorieProvider;

    public FilterFragment() {
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        sortCheckBox = view.findViewById(R.id.sortCheckBox);
        searchEditText = view.findViewById(R.id.searchEditText);
        tagDropdownMenu = view.findViewById(R.id.dropdown_menu);
        filterSpinner = view.findViewById(R.id.filterSpinner);
        foodRecyclerView = view.findViewById(R.id.itemRecyclerView);

        minimumCalsEditText = view.findViewById(R.id.minCaloriesEditText);
        sortCalsSpinner = view.findViewById(R.id.filterCalorie);
        maximumCalsEditText = view.findViewById(R.id.maxCaloriesEditText);

        foodList = new ArrayList<>();

        mealProvider = new MealProvider();
        calorieProvider = new CalorieProvider();

        mealProvider.getMealService().fetchMealsByCategory(AppState.getInstance().getCategories().get(0).getNazivKategorije()).enqueue(new Callback<MealResponseWrapper>() {
            @Override
            public void onResponse(Call<MealResponseWrapper> call, Response<MealResponseWrapper> response) {
                if(response.body() == null || response.body().getMeals() == null){
                    return;
                }
                List<MealResponse> meals = response.body().getMeals();

                for(MealResponse meal : meals) {
                    Food food = new Food(meal.getStrMeal(), "", meal.getIdMeal());
                    /*mealProvider.getMealService().fetchMealById(meal.getIdMeal()).enqueue(new Callback<DetailedMealResponseWrapper>() {
                        @Override
                        public void onResponse(Call<DetailedMealResponseWrapper> call, Response<DetailedMealResponseWrapper> response) {
                            if(response.body() == null || response.body().getMeals() == null){
                                return;
                            }
                            calorieProvider.getCalorieService().fetchCaloriesForMeal(response.body().getMeals().get(0).getSastojci()).enqueue(new Callback<List<CalorieResponse>>() {
                                @Override
                                public void onResponse(Call<List<CalorieResponse>> call, Response<List<CalorieResponse>> response) {
                                    if(response.body() == null){
                                        return;
                                    }
                                    float calories = 0;
                                    for(CalorieResponse c : response.body()){
                                        calories += c.getCalories();
                                    }
                                    food.setCalories(calories);
                                    updateAdapter();
                                }

                                @Override
                                public void onFailure(Call<List<CalorieResponse>> call, Throwable t) {

                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<DetailedMealResponseWrapper> call, Throwable t) {

                        }
                    });*/
                    food.setCalories(5f);
                    foodList.add(food);
                }
                updateAdapter();
            }

            @Override
            public void onFailure(Call<MealResponseWrapper> call, Throwable t) {

            }
        });

        foodAdapter = new FoodAdapter(foodList, new FoodAdapter.OnFoodClickListener() {
            @Override
            public void onFoodClick(Food food) {
                Intent intent = new Intent(getContext(), FoodActivity.class);
                intent.putExtra("foodName", food.getIme());
                startActivity(intent);
            }
        });
        foodRecyclerView.setAdapter(foodAdapter);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        List<String> dropdownOptions = new ArrayList<>();
        dropdownOptions.add("Option 1");
        dropdownOptions.add("Option 2");
        dropdownOptions.add("Option 3");
        /** OVDE UBACITI TAGOVE  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/

        ArrayAdapter<String> tagAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                dropdownOptions
        );
        tagAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tagDropdownMenu.setAdapter(tagAdapter);

        List<String> filterOptions = new ArrayList<>();
        filterOptions.add("Kategorija");
        filterOptions.add("Oblasti");
        filterOptions.add("Sastojci");

        ArrayAdapter<String> filterAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                filterOptions
        );
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(filterAdapter);

        List<String> calsOptions = new ArrayList<>();
        calsOptions.add(">");
        calsOptions.add(">= =<");
        calsOptions.add("<");

        ArrayAdapter<String> calsAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                calsOptions
        );
        calsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortCalsSpinner.setAdapter(calsAdapter);

        foodRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                        currentPage++;
                        loadNextPage();
                    }
                }
            }
        });

        sortCalsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();
                if(selectedOption.equals(">= =<")){
                    maximumCalsEditText.setVisibility(View.VISIBLE);
                }else{
                    maximumCalsEditText.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ova metoda se poziva kada nema selektovane stavke
            }
        });

        return view;
    }

    private void loadNextPage() {
        isLoading = true;
        List<Food> nextPageItems = fetchDataForPage(currentPage);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isLoading = false;

                if (nextPageItems.size() < PAGE_SIZE) {
                    isLastPage = true;
                }

                foodAdapter.setItems(nextPageItems);
            }
        }, 1500);
    }

    private List<Food> fetchDataForPage(int currentPage) {
        // Implement your logic to fetch data for the given page
        return new ArrayList<>(); // Return an empty list for now
    }

    private void updateAdapter(){
        foodAdapter = new FoodAdapter(foodList, new FoodAdapter.OnFoodClickListener() {
            @Override
            public void onFoodClick(Food food) {
                Intent intent = new Intent(getContext(), FoodActivity.class);
                intent.putExtra("foodName", food.getIme());
                intent.putExtra("foodId", food.getId());
                startActivity(intent);
            }
        });
        foodRecyclerView.setAdapter(foodAdapter);
    }
}
