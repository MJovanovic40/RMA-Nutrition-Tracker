package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activityProfile;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

//import com.github.mikephil.charting.charts.BarChart;
//import com.github.mikephil.charting.components.Legend;
//import com.github.mikephil.charting.components.XAxis;
//import com.github.mikephil.charting.data.BarData;
//import com.github.mikephil.charting.data.BarDataSet;
//import com.github.mikephil.charting.data.BarEntry;
//import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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

//    private RadioGroup statisticsRadioGroup;
//    private FrameLayout chartContainer;
//    private BarChart chart;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_statistics);
//
//        statisticsRadioGroup = findViewById(R.id.statisticsRadioGroup);
//        chartContainer = findViewById(R.id.chartContainer);
//
//        // Inicijalizacija grafa
//        chart = new BarChart(this);
//        chartContainer.addView(chart);
//
//        // Postavljanje osnovnih postavki grafa
//        setupChart();
//
//        // Slušač promjene odabira radio dugmadi
//        statisticsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
//                if (checkedId == R.id.mealCountRadioButton) {
//                    // Odabrano je prikaz broja obroka
//                    updateChartDataMealCount();
//                } else if (checkedId == R.id.calorieCountRadioButton) {
//                    // Odabrana je kalorijska statistika
//                    updateChartDataCalorieCount();
//                }
//            }
//        });
//
//        // Podrazumijevano prikazuje graf broja obroka
//        updateChartDataMealCount();
//    }
//
//    // Inicijalizacija postavki grafa
//    private void setupChart() {
//        chart.setDrawBarShadow(false);
//        chart.setDrawValueAboveBar(true);
//        chart.getDescription().setEnabled(false);
//
//        XAxis xAxis = chart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawGridLines(false);
//        xAxis.setGranularity(1f);
//        xAxis.setAxisMinimum(0f);
//
//        chart.getAxisLeft().setAxisMinimum(0f);
//        chart.getAxisRight().setEnabled(false);
//
//        Legend legend = chart.getLegend();
//        legend.setEnabled(false);
//    }
//
//    // Ažuriranje podataka grafa za prikaz broja obroka
//    private void updateChartDataMealCount() {
//        List<BarEntry> entries = new ArrayList<>();
//        // Učitavanje podataka iz lokalne baze (broj obroka za svaki dan u prethodnih 7 dana)
//        // i dodavanje vrijednosti u listu entries
//
//        // Primjer podataka:
//        entries.add(new BarEntry(1f, 3)); // Dan 1: 3 obroka
//        entries.add(new BarEntry(2f, 4)); // Dan 2: 4 obroka
//        entries.add(new BarEntry(3f, 2)); // Dan 3: 2 obroka
//        entries.add(new BarEntry(4f, 5)); // Dan 4: 5 obroka
//        entries.add(new BarEntry(5f, 1)); // Dan 5: 1 obrok
//        entries.add(new BarEntry(6f, 3)); // Dan 6: 3 obroka
//        entries.add(new BarEntry(7f, 2)); // Dan 7: 2 obroka
//
//        BarDataSet dataSet = new BarDataSet(entries, "Broj obroka");
//        dataSet.setColor(Color.BLUE);
//        dataSet.setValueTextColor(Color.BLACK);
//
//        List<IBarDataSet> dataSets = new ArrayList<>();
//        dataSets.add(dataSet);
//
//        BarData data = new BarData(dataSets);
//        chart.setData(data);
//        chart.invalidate();
//    }
//
//    // Ažuriranje podataka grafa za prikaz kalorijske statistike
//    private void updateChartDataCalorieCount() {
//        // Implementirati logiku za prikaz kalorijske statistike
//        // na sličan način kao što je prikazan primjer za broj obroka
//    }

    private GraphView graphView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        graphView = findViewById(R.id.idGraphView);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7); // Subtract 7 days from the current date

        Date startDate = calendar.getTime();

        List<MealEntity> meals = AppState.getInstance().getDb().mealDao().getMealsWithinLast7Days(startDate);


        Map<Integer, Integer> o = new HashMap<>();

        Calendar calendarSave = Calendar.getInstance();

        for(int i = 0; i < meals.size(); i++) {
            MealEntity meal = meals.get(i);
            calendarSave.setTime(meal.getDateSaved());
            int c = 0;

            if(o.containsKey(calendarSave.get(Calendar.DAY_OF_WEEK)) && o.get(calendarSave.get(Calendar.DAY_OF_WEEK)) != null){
                c = o.get(calendarSave.get(Calendar.DAY_OF_WEEK));
            }
            System.out.println(calendarSave.get(Calendar.DAY_OF_WEEK));
            o.put(calendarSave.get(Calendar.DAY_OF_WEEK), c + 1);
        }

        DataPoint[] dataPoints = new DataPoint[7];

        for(int i = 0; i < 7; i++) {
            dataPoints[i] = new DataPoint(i, 0);
        }

        for(Integer date : o.keySet()) {
            dataPoints[date] = new DataPoint(date, o.get(date));
        }

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);

        // after adding data to our line graph series.
        // on below line we are setting
        // title for our graph view.
        graphView.setTitle("Statistika");

        // on below line we are setting
        // text color to our graph view.
        graphView.setTitleColor(R.color.purple_200);

        // on below line we are setting
        // our title text size.
        graphView.setTitleTextSize(18);

        // on below line we are adding
        // data series to our graph view.
        graphView.addSeries(series);

        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    switch((int) value) {
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
                    // Use the default formatting for y-values
                    return super.formatLabel(value, isValueX);
                }
                return "";
            }
        });

    }
}
