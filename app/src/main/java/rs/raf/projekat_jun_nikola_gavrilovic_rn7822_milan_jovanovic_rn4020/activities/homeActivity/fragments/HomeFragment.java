package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.AppState;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.CategoryFoodActivity;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models.Category;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models.CategoryAdapter;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.category.CategoryResponse;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.category.CategoryResponseWrapper;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.providers.MealProvider;

public class HomeFragment extends Fragment {
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private ProgressBar categoryProgressBar;
    private EditText searchEditText;

    private Button searchBtn;

//    private SoftInputAssist softInputAssist;
    private MealProvider mealProvider;
    private List<Category> categories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

//        softInputAssist = new SoftInputAssist(getActivity());

        categoryProgressBar = view.findViewById(R.id.categoryProgressBar);
        searchEditText = view.findViewById(R.id.searchEditText);
        searchBtn = view.findViewById(R.id.searchButton);

        mealProvider = new MealProvider();

        categories = new ArrayList<>();

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                performSearch(editable.toString());
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Logika za search
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoryRecyclerView.setVisibility(View.GONE);
        categoryProgressBar.setVisibility(View.VISIBLE);

        //Fetch categories
        mealProvider.getCategoryService().fetchAllCategories().enqueue(new Callback<CategoryResponseWrapper>() {
            @Override
            public void onResponse(Call<CategoryResponseWrapper> call, Response<CategoryResponseWrapper> response) {
                CategoryResponseWrapper categoryResponses = response.body();
                System.out.println(categoryResponses.getCategories());
                if(categoryResponses == null){
                    return;
                }

                for(CategoryResponse c: categoryResponses.getCategories()) {
                    Category category = new Category(c.getStrCategoryThumb(), c.getStrCategory(), c.getStrCategoryDescription());
                    categories.add(category);
                    updateAdapter(categories);
                }
                AppState.getInstance().setCategories(categories);
            }

            @Override
            public void onFailure(Call<CategoryResponseWrapper> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }

    private void updateAdapter(List<Category> categories){
        categoryAdapter = new CategoryAdapter(categories, new CategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(Category category) {
                Intent intent = new Intent(requireContext(), CategoryFoodActivity.class);
                intent.putExtra("categoryName", category.getNazivKategorije());
                startActivity(intent);
            }

            public void onCategoryOptionsClick(Category category) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Opis kategorije");
                builder.setMessage(category.getOpisKategorije());
                builder.setPositiveButton("OK", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        categoryRecyclerView.setAdapter(categoryAdapter);

        categoryRecyclerView.setVisibility(View.VISIBLE);
        categoryProgressBar.setVisibility(View.GONE);
    }

    private void performSearch(String keyWord) {
        if(categories == null) return;

        List<Category> satisfiedCategories = new ArrayList<>();

        for(Category c: categories) {
            if(c.getNazivKategorije().toLowerCase().startsWith(keyWord.toLowerCase())){
                satisfiedCategories.add(c);
            }
        }

        updateAdapter(satisfiedCategories);
    }

//    @Override
//    public void onResume() {
//        softInputAssist.onResume();
//        super.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        softInputAssist.onPause();
//        super.onPause();
//    }
//
//    @Override
//    public void onDestroy() {
//        softInputAssist.onDestroy();
//        super.onDestroy();
//    }
}