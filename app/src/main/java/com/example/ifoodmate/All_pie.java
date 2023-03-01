package com.example.ifoodmate;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class All_pie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_pie);

        PieChart pieChart = findViewById(R.id.pie_chart);

        ArrayList<PieEntry> visitors = new ArrayList<>();

        visitors.add(new PieEntry(10,"users"));
        visitors.add(new PieEntry(5,"Service Providers"));

        PieDataSet barDataSet = new PieDataSet(visitors, "Users");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(barDataSet);


        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.animate();


    }
}