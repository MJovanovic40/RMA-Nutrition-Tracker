package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.adapter.FoodAdapter;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.foodActivity.FoodActivity;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.enteties.Food;

public class FilterFragment extends Fragment {

    private RadioGroup sortRadioGroup;
    private RadioButton sortRadioButton;
    private Spinner dropdownMenu;
    private EditText searchEditText;
    private Spinner filterSpinner;
    private RecyclerView foodRecyclerView;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;

    private static final int PAGE_SIZE = 20;
    private int currentPage = 0;
    private boolean isLastPage = false;
    private boolean isLoading = false;

    public FilterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        sortRadioGroup = view.findViewById(R.id.sortRadioGroup);
        sortRadioButton = view.findViewById(R.id.sortRadioButton);
        dropdownMenu = view.findViewById(R.id.dropdown_menu);
        searchEditText = view.findViewById(R.id.searchEditText);
        filterSpinner = view.findViewById(R.id.filterSpinner);
        foodRecyclerView = view.findViewById(R.id.itemRecyclerView);

        foodList = new ArrayList<>();
        foodList.add(new Food("nesto", "opis"));
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
        dropdownMenu.setAdapter(tagAdapter);

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
}
