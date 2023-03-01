package com.example.ifoodmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        btn = findViewById(R.id.all_pie);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),All_pie.class);
                startActivity(intent);
            }
        });

    }


}