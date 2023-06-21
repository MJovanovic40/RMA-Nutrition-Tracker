package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.adapter.FoodAdapter;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.enteties.Food;

public class FilterFragment extends Fragment {

    private RadioGroup sortRadioGroup;
    private EditText filterNameEditText;
    private EditText filterTagEditText;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;

    public FilterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sortRadioGroup = view.findViewById(R.id.sortRadioGroup);
        filterNameEditText = view.findViewById(R.id.filterNameEditText);
        filterTagEditText = view.findViewById(R.id.filterTagEditText);

        foodList = createDummyItems();
        foodAdapter = new FoodAdapter(foodList);

        filterNameEditText.addTextChangedListener(filterTextWatcher);
        filterTagEditText.addTextChangedListener(filterTextWatcher);

        sortRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // Ovde implementirajte logiku za sortiranje
            // Na osnovu odabranog RadioButtona možete primeniti odgovarajući način sortiranja na item listu
        });
    }

    private List<Food> createDummyItems() {
        List<Food> foodList = new ArrayList<>();
        foodList.add(new Food("Jaja","Skuvati u vodi"));

        return foodList;
    }

    private TextWatcher filterTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Ignorirajte
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Ignorirajte
        }

        /** Napravi samo za Ime */
        @Override
        public void afterTextChanged(Editable s) {
            // Ovde implementirajte logiku za filtriranje na osnovu naziva i tagova
            String filterName = filterNameEditText.getText().toString().trim();
            String filterTag = filterTagEditText.getText().toString().trim();
            filterItems(filterName, filterTag);
        }
    };

    private void filterItems(String filterName, String filterTag) {
        List<Food> filteredItems = new ArrayList<>();

        // Ovde implementirajte logiku za filtriranje itema na osnovu naziva i tagova

        foodAdapter.setItems(filteredItems);
    }
}
