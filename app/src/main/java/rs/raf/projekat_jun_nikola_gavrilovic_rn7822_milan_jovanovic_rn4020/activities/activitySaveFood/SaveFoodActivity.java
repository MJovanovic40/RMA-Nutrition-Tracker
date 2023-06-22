package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activitySaveFood;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;

public class SaveFoodActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private ImageView foodImageView;
    private DatePicker datePicker;
    private Spinner categorySpinner;
    private Button cameraButton;
    private Button saveButton;

    private Bitmap foodImageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savefood);

        foodImageView = findViewById(R.id.foodImageView);
        datePicker = findViewById(R.id.datePicker);
        categorySpinner = findViewById(R.id.categorySpinner);
        cameraButton = findViewById(R.id.cameraButton);
        saveButton = findViewById(R.id.saveButton);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFood();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null && extras.containsKey("data")) {
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                foodImageView.setImageBitmap(imageBitmap);
                foodImageBitmap = imageBitmap;
            }
        }
    }

    private void saveFood() {
        if (foodImageBitmap == null) {
            Toast.makeText(this, "Take a photo of the food", Toast.LENGTH_SHORT).show();
            return;
        }

        String selectedDate = formatDate(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        String selectedCategory = categorySpinner.getSelectedItem().toString();

        if (TextUtils.isEmpty(selectedCategory)) {
            Toast.makeText(this, "Select a category", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO implementirati cuvanje jela u bazu podataka

        Toast.makeText(this, "Food saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    private String formatDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(calendar.getTime());
    }
}
