package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

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
    /*private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private ProgressBar categoryProgressBar;
    private EditText searchEditText;

    private Spinner searchTypeSpinner;

    private Button searchBtn;

    private MealProvider mealProvider;
    private List<Category> categories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        categoryProgressBar = view.findViewById(R.id.categoryProgressBar);
        searchEditText = view.findViewById(R.id.searchEditText);
        searchBtn = view.findViewById(R.id.searchButton);
        searchTypeSpinner = view.findViewById(R.id.searchTypeSpinner);

        mealProvider = new MealProvider();

        categories = new ArrayList<>();


        List<String> searchTypes = new ArrayList<>();
        searchTypes.add("Meal name");
        searchTypes.add("Ingredient");

        ArrayAdapter<String> searchTypeAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                searchTypes
        );

        searchTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchTypeSpinner.setAdapter(searchTypeAdapter);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(searchEditText.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Seach is empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getContext(), CategoryFoodActivity.class);
                intent.putExtra("origin", "search");
                intent.putExtra("searchType", searchTypeSpinner.getSelectedItem().toString());
                intent.putExtra("searchQuery", searchEditText.getText().toString());
                startActivity(intent);
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
                intent.putExtra("origin", "category");
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
    }*/
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private ProgressBar categoryProgressBar;
    private EditText searchEditText;
    private Spinner searchTypeSpinner;
    private Button searchBtn;

    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        categoryProgressBar = view.findViewById(R.id.categoryProgressBar);
        searchEditText = view.findViewById(R.id.searchEditText);
        searchBtn = view.findViewById(R.id.searchButton);
        searchTypeSpinner = view.findViewById(R.id.searchTypeSpinner);

        // Set up the spinner and adapter
        ArrayAdapter<CharSequence> searchTypeAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.search_types,
                android.R.layout.simple_spinner_item
        );
        searchTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchTypeSpinner.setAdapter(searchTypeAdapter);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(searchEditText.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Seach is empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getContext(), CategoryFoodActivity.class);
                intent.putExtra("origin", "search");
                intent.putExtra("searchType", searchTypeSpinner.getSelectedItem().toString());
                intent.putExtra("searchQuery", searchEditText.getText().toString());
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categoryRecyclerView.setVisibility(View.GONE);
        categoryProgressBar.setVisibility(View.VISIBLE);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getCategoryListLiveData().observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                updateAdapter(categories);
            }
        });

        homeViewModel.fetchCategories();
    }

    private void updateAdapter(List<Category> categories) {
        categoryAdapter = new CategoryAdapter(categories, new CategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(Category category) {
                Intent intent = new Intent(requireContext(), CategoryFoodActivity.class);
                intent.putExtra("origin", "category");
                intent.putExtra("categoryName", category.getNazivKategorije());
                startActivity(intent);
            }

            @Override
            public void onCategoryOptionsClick(Category category) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Category Description");
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

}