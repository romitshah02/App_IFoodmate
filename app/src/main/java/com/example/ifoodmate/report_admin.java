package com.example.ifoodmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class report_admin extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_admin);
        PieChart pieChart = findViewById(R.id.barchart);

        ArrayList<PieEntry> visitors = new ArrayList<>();

        visitors.add(new PieEntry(20, "users"));
        visitors.add(new PieEntry(15, "Service Providers"));

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