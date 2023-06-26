package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activitySaveFood;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.AppState;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.DetailedMealResponse;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.models.meal.DetailedMealResponseWrapper;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.api.providers.MealProvider;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.entities.MealEntity;

public class SaveFoodActivity extends AppCompatActivity implements IPickResult {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 2;

    private TextView foodNameTextView;
    private ImageView foodImageView;
    private TextView dateButton;
    private Spinner categorySpinner;

    //private Button cameraButton;

    private Button saveButton;

    private Bitmap foodImageBitmap;

    private Calendar selectedDate;

    private ChooseImageDialog chooseImageDialog;

    private MealProvider mealProvider;

    private String mealImage;
    private DetailedMealResponse meal = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savefood);

        Intent intent = getIntent();

        foodImageView = findViewById(R.id.foodImageView);
        dateButton = findViewById(R.id.dateButton);
        categorySpinner = findViewById(R.id.categorySpinner);
        //cameraButton = findViewById(R.id.cameraButton);

        saveButton = findViewById(R.id.saveButton);
        foodNameTextView = findViewById(R.id.foodNameTextView);

        chooseImageDialog = new ChooseImageDialog();

        mealProvider = new MealProvider();

        foodNameTextView.setText(intent.getStringExtra("foodName"));
        selectedDate = Calendar.getInstance(); // Inicijalno postavljamo odabrani datum na današnji datum

        Picasso.get().load(getIntent().getStringExtra("image")).into(foodImageView);
        mealImage = getIntent().getStringExtra("image");

        mealProvider.getMealService().fetchMealById(intent.getStringExtra("foodId")).enqueue(new Callback<DetailedMealResponseWrapper>() {
            @Override
            public void onResponse(Call<DetailedMealResponseWrapper> call, Response<DetailedMealResponseWrapper> response) {
                if(response == null || response.body().getMeals() == null){
                    return;
                }
                meal = response.body().getMeals().get(0);
            }

            @Override
            public void onFailure(Call<DetailedMealResponseWrapper> call, Throwable t) {

            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate.set(Calendar.YEAR, LocalDate.now().getYear());
            selectedDate.set(Calendar.MONTH, LocalDate.now().getMonthValue() - 1);
            selectedDate.set(Calendar.DAY_OF_MONTH, LocalDate.now().getDayOfMonth());
            updateDateButtonText();
        }

        List<String> spinnerAdapterList = new ArrayList<>();
        spinnerAdapterList.add("Breakfast");
        spinnerAdapterList.add("Lunch");
        spinnerAdapterList.add("Dinner");
        spinnerAdapterList.add("Snack");

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                spinnerAdapterList
        );
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        foodImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open camera
                PickImageDialog.build(new PickSetup()).show(SaveFoodActivity.this);
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFood();
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectedDate.set(Calendar.YEAR, year);
                selectedDate.set(Calendar.MONTH, monthOfYear);
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateButtonText();
            }
        };

        // Prikazujemo DatePicker dijalog sa odabranim datumom
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                dateSetListener,
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void updateDateButtonText() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.", Locale.getDefault());
        String formattedDate = sdf.format(selectedDate.getTime());
        dateButton.setText(formattedDate);
    }


    private void saveFood() {
        /*if (foodImageBitmap == null) {
            Toast.makeText(this, "Take a photo of the food", Toast.LENGTH_SHORT).show();
            return;
        }*/

        if(mealImage == null){
            Toast.makeText(this, "Take a photo of the food", Toast.LENGTH_SHORT).show();
            return;
        }

        String selectedCategory = categorySpinner.getSelectedItem().toString();

        if (TextUtils.isEmpty(selectedCategory)) {
            Toast.makeText(this, "Select a category", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO implementirati cuvanje jela u bazu podataka i polsati jelo u Menu za sacuvana jela
        if(meal == null){
            Toast.makeText(this, "Meal not ready.", Toast.LENGTH_SHORT).show();
            return;
        }

        MealEntity mealEntity = new MealEntity(meal, mealImage, selectedDate.getTime() ,selectedCategory, getIntent().getFloatExtra("calories", 0));

        AppState.getInstance().getDb().mealDao().add(mealEntity);

        Toast.makeText(this, "Food saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            Picasso.get().load(r.getUri()).into(foodImageView);
            mealImage = r.getUri().toString();
        } else {
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
