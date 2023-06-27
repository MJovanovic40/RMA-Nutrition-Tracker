package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activityProfile;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.AppState;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.R;
import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.entities.MealEntity;

public class StatisticsActivity extends AppCompatActivity {

    private GraphView graphView;
    private GraphView graphViewCalories;

    private RadioGroup radioGroup;

    private StatisticsViewModel statisticsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        graphView = findViewById(R.id.idGraphView);
        graphViewCalories = findViewById(R.id.idGraphViewCalories);
        radioGroup = findViewById(R.id.statisticsRadioGroup);

        statisticsViewModel = new ViewModelProvider(this).get(StatisticsViewModel.class);

        setupGraphViewMealNumber();
        setupGraphViewCalories();

        radioGroup.getCheckedRadioButtonId();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.mealCountRadioButton) {
                    graphViewCalories.setVisibility(View.GONE);
                    graphView.setVisibility(View.VISIBLE);
                } else {
                    graphViewCalories.setVisibility(View.VISIBLE);
                    graphView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setupGraphViewCalories() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7); // Subtract 7 days from the current date

        Date startDate = calendar.getTime();

        List<MealEntity> meals = AppState.getInstance().getDb().mealDao().getMealsWithinLast7Days(startDate);

        Map<Integer, Float> o = new HashMap<>();

        Calendar calendarSave = Calendar.getInstance();

        for (int i = 0; i < meals.size(); i++) {
            MealEntity meal = meals.get(i);
            calendarSave.setTime(meal.getPreparationDate());
            float c = 0;

            if (o.containsKey(calendarSave.get(Calendar.DAY_OF_WEEK)) && o.get(calendarSave.get(Calendar.DAY_OF_WEEK)) != null) {
                c = o.get(calendarSave.get(Calendar.DAY_OF_WEEK));
            }
            o.put(calendarSave.get(Calendar.DAY_OF_WEEK), meal.getCalories());
        }

        DataPoint[] dataPoints = new DataPoint[7];

        for (int i = 0; i < 7; i++) {
            dataPoints[i] = new DataPoint(i, 0);
        }

        for (Integer date : o.keySet()) {
            dataPoints[date] = new DataPoint(date, o.get(date));
        }

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);

        graphViewCalories.setTitle("Statistika broja obroka");
        graphViewCalories.setTitleColor(R.color.black);
        graphViewCalories.setTitleTextSize(72);
        graphViewCalories.addSeries(series);

        graphViewCalories.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    switch ((int) value) {
                        case 1:
                            return "Sunday";
                        case 2:
                            return "Monday";
                        case 3:
                            return "Tuesday";
                        case 4:
                            return "Wednesday";
                        case 5:
                            return "Thursday";
                        case 6:
                            return "Friday";
                        case 7:
                            return "Saturday";
                    }
                } else {
                    return super.formatLabel(value, isValueX);
                }
                return "";
            }
        });

        Map<Integer, Float> caloriesData = new HashMap<>(o);
        statisticsViewModel.setCaloriesData(caloriesData);
    }

    private void setupGraphViewMealNumber() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);

        Date startDate = calendar.getTime();

        List<MealEntity> meals = AppState.getInstance().getDb().mealDao().getMealsWithinLast7Days(startDate);

        Map<Integer, Integer> o = new HashMap<>();

        Calendar calendarSave = Calendar.getInstance();

        for (int i = 0; i < meals.size(); i++) {
            MealEntity meal = meals.get(i);
            calendarSave.setTime(meal.getPreparationDate());
            int c = 0;

            if (o.containsKey(calendarSave.get(Calendar.DAY_OF_WEEK)) && o.get(calendarSave.get(Calendar.DAY_OF_WEEK)) != null) {
                c = o.get(calendarSave.get(Calendar.DAY_OF_WEEK));
            }
            System.out.println(calendarSave.get(Calendar.DAY_OF_WEEK));
            o.put(calendarSave.get(Calendar.DAY_OF_WEEK), c + 1);
        }

        DataPoint[] dataPoints = new DataPoint[7];

        for (int i = 0; i < 7; i++) {
            dataPoints[i] = new DataPoint(i, 0);
        }

        for (Integer date : o.keySet()) {
            dataPoints[date] = new DataPoint(date, o.get(date));
        }

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);

        graphView.setTitle("Statistika broja obroka");
        graphView.setTitleColor(R.color.black);
        graphView.setTitleTextSize(72);
        graphView.addSeries(series);

        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    switch ((int) value) {
                        case 1:
                            return "Sunday";
                        case 2:
                            return "Monday";
                        case 3:
                            return "Tuesday";
                        case 4:
                            return "Wednesday";
                        case 5:
                            return "Thursday";
                        case 6:
                            return "Friday";
                        case 7:
                            return "Saturday";
                    }
                } else {
                    return super.formatLabel(value, isValueX);
                }
                return "";
            }
        });

        Map<Integer, Integer> mealCountData = new HashMap<>(o);
        statisticsViewModel.setMealCountData(mealCountData);
    }
}

