package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.categoryFood.adapter.FoodAdapter;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.homeActivity.fragments.models.Category;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.enteties.Food;

public class CategoryFoodActivity extends AppCompatActivity {

    private RecyclerView foodRecyclerView;
    private FoodAdapter foodAdapter;
    private EditText searchEditText;
    private List<Food> foodList;
    private TextView categoryTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryfood);

        String categoryTitle = getIntent().getStringExtra("categoryName");

        categoryTitleTextView = findViewById(R.id.categoryTitleTextView);
        categoryTitleTextView.setText(categoryTitle);

        foodRecyclerView = findViewById(R.id.foodRecyclerView);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchEditText = findViewById(R.id.searchEditText);

        foodList = new ArrayList<>();
        createDummyFood(foodList);

        foodAdapter = new FoodAdapter(foodList);
        foodRecyclerView.setAdapter(foodAdapter);

        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String searchQuery = searchEditText.getText().toString().trim();
                performSearch(searchQuery);
                return true;
            }
            return false;
        });
    }

    private List<Food> createDummyFood(List<Food> foods) {
        foods.add(new Food("Burger", "Mesni proizvod"));
        foods.add(new Food("Sendvic", "Hleb,povrce,meso"));
        return foods;
    }

    private void performSearch(String searchQuery) {
        // Ovde implementirajte logiku pretrage jela po nazivu
        // Ažurirajte foodList i foodAdapter sa rezultatima pretrage
    }
}


