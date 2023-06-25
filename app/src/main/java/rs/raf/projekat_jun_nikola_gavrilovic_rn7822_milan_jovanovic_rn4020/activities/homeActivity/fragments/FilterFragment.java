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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.CategoryFoodActivity;
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
    private Button filterBtn;

    private MealProvider mealProvider;
    private CalorieProvider calorieProvider;

    boolean maximum = false;
    public FilterFragment() {
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        sortCheckBox = view.findViewById(R.id.sortCheckBox);
        searchEditText = view.findViewById(R.id.searchView);
        tagDropdownMenu = view.findViewById(R.id.dropdown_menu);
        filterSpinner = view.findViewById(R.id.filterSpinner);

        minimumCalsEditText = view.findViewById(R.id.minCaloriesEditText);
        sortCalsSpinner = view.findViewById(R.id.filterCalorie);
        maximumCalsEditText = view.findViewById(R.id.maxCaloriesEditText);

        filterBtn = view.findViewById(R.id.filterButton);

        mealProvider = new MealProvider();
        calorieProvider = new CalorieProvider();

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


        sortCalsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();
                if (selectedOption.equals(">= =<")) {
                    maximumCalsEditText.setVisibility(View.VISIBLE);
                    maximum = true;
                } else {
                    maximumCalsEditText.setVisibility(View.GONE);
                    maximum = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ova metoda se poziva kada nema selektovane stavke
            }
        });


        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CategoryFoodActivity.class);
                intent.putExtra("sortAbecedno", sortCheckBox.isChecked());
                intent.putExtra("tag", tagDropdownMenu.getSelectedItem().toString());
                if((searchEditText.getQuery().toString()) != null){
                    intent.putExtra("search", searchEditText.getQuery().toString());
                }
                if (maximum){
                    intent.putExtra("minimumCals", minimumCalsEditText.getText());
                    intent.putExtra("maximumCals", maximumCalsEditText.getText());
                }else{
                    intent.putExtra("minimumCals", minimumCalsEditText.getText());
                }
                intent.putExtra("filterZnak", sortCalsSpinner.getSelectedItem().toString());
                intent.putExtra("oblast", filterSpinner.getSelectedItem().toString());
                startActivity(intent);

            }
        });

        return view;
    }
}
