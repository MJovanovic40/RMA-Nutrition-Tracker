package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activityAddFood;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
    private List<Food> foodList;

    private Calendar selectedDate;

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

        foodList = new ArrayList<>();
        foodList.add(new Food("Hamburger", "Brza hrana", 500));
        foodList.add(new Food("Salata", "Zdrava hrana", 200));
        foodList.add(new Food("Pica", "Italijanska hrana", 800));

        foodAdapter = new FoodAdapter(foodList, this);
        svaHranaRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        svaHranaRecyclerView.setAdapter(foodAdapter);

        odaberiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }

    @Override
    public void onFoodClick(Food food) {
        odabranaHranaTextView.setText(food.getIme());
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddFoodActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectedDate = Calendar.getInstance();
                selectedDate.set(Calendar.YEAR, year);
                selectedDate.set(Calendar.MONTH, monthOfYear);
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String dateString = sdf.format(selectedDate.getTime());
                dateButton.setText(dateString);
            }
        }, year, month, day);

        datePickerDialog.show();
    }
}
