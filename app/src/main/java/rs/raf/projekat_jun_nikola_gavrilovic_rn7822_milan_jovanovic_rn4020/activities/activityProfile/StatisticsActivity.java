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

import java.util.ArrayList;
import java.util.List;

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
}
