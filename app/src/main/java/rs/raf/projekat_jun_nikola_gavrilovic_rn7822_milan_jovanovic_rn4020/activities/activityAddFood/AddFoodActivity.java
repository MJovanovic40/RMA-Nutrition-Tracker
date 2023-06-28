package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activityAddFood;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.adapter.FoodAdapter;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.models.Food;


public class AddFoodActivity extends AppCompatActivity implements FoodAdapter.OnFoodClickListener {
    private Spinner kategorijaSpinner;
    private TextView dateButton;
    private Spinner vrstaPretrageSpinner;
    private RecyclerView svaHranaRecyclerView;
    private TextView odabranaHranaTextView;
    private Button odaberiButton;

    private FoodAdapter foodAdapter;
    private AddFoodViewModel addFoodViewModel;

    private Calendar selectedDate;
    private float chosenCalories = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfood);

        kategorijaSpinner = findViewById(R.id.kategorijaSpinner);
        dateButton = findViewById(R.id.dateButton);
        vrstaPretrageSpinner = findViewById(R.id.vrstaPretrageSpinner);
        svaHranaRecyclerView = findViewById(R.id.svaHranaRecyclerView);
        odabranaHranaTextView = findViewById(R.id.odabranaHranaTextView);
        odaberiButton = findViewById(R.id.odaberiButton);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.meal_categories,
                android.R.layout.simple_spinner_item
        );
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kategorijaSpinner.setAdapter(categoryAdapter);

        ArrayAdapter<CharSequence> storageTypeAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.food_storage_type,
                android.R.layout.simple_spinner_item
        );
        storageTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vrstaPretrageSpinner.setAdapter(storageTypeAdapter);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String dateString = sdf.format(new Date());
        dateButton.setText(dateString);
        selectedDate = Calendar.getInstance();

        foodAdapter = new FoodAdapter(new ArrayList<>(), this);
        svaHranaRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        svaHranaRecyclerView.setAdapter(foodAdapter);

        addFoodViewModel = new ViewModelProvider(this).get(AddFoodViewModel.class);
        addFoodViewModel.getFoodListLiveData().observe(this, foodList -> {
            foodAdapter.setItems(foodList);
            foodAdapter.notifyDataSetChanged();
        });

        fetchData(vrstaPretrageSpinner.getSelectedItem().toString());

        odaberiButton.setOnClickListener(v -> {
            Intent data = new Intent();
            data.putExtra("foodName", odabranaHranaTextView.getText().toString());
            data.putExtra("category", kategorijaSpinner.getSelectedItem().toString());
            data.putExtra("calories", chosenCalories);
            data.putExtra("date", selectedDate.getTime().getTime());

            setResult(RESULT_OK, data);
            finish();
        });

        vrstaPretrageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fetchData(vrstaPretrageSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        dateButton.setOnClickListener(v -> showDatePicker());
    }

    @Override
    public void onFoodClick(Food food) {
        odabranaHranaTextView.setText(food.getIme());
        chosenCalories = food.getCalories();
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, monthOfYear, dayOfMonth) -> {
            selectedDate = Calendar.getInstance();
            selectedDate.set(Calendar.YEAR, year1);
            selectedDate.set(Calendar.MONTH, monthOfYear);
            selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String dateString = sdf.format(selectedDate.getTime());
            dateButton.setText(dateString);
        }, year, month, day);

        datePickerDialog.show();
    }

    private void fetchData(String type) {
        addFoodViewModel.fetchData(type);
    }
}

