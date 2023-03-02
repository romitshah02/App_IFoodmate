package com.example.ifoodmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class admin_page extends AppCompatActivity {
TextView tv1;
TextView tv2;
TextView tv3;
TextView tv4;
TextView tv5;
TextView tv6;
TextView tv7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        tv1 = findViewById(R.id.offer);
                tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent offer= new Intent(getApplicationContext(),offers.class);
                        startActivity(offer);
                    }
                });
        tv2 = findViewById(R.id.user);
                tv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent user = new Intent(getApplicationContext(),user_admin.class);
                        startActivity(user);
                    }
                });
        tv3 = findViewById(R.id.serviceprovider);
                tv3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent spdetails = new Intent(getApplicationContext(),sp_details_admin.class);
                        startActivity(spdetails);
                    }
                });
        tv4 = findViewById(R.id.feedback);
                tv4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent feedback = new Intent(getApplicationContext(),feedback_admin.class);
                        startActivity(feedback);
                    }
                });
        tv5 = findViewById(R.id.about_us1);
                tv5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent aboutus = new Intent(getApplicationContext(),aboutus_admin.class);
                        startActivity(aboutus);
                    }
                });
        tv6 = findViewById(R.id.pwd);
                tv6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent pwd = new Intent(getApplicationContext(),changepwd_admin.class);
                        startActivity(pwd);
                    }
                });
        tv7 = findViewById(R.id.report);
                tv7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent report = new Intent(getApplicationContext(),report_admin.class);
                        startActivity(report);
                    }
                });
    }
}