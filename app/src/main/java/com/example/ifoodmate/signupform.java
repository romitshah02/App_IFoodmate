package com.example.ifoodmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class signupform extends AppCompatActivity {
Button btn,btn1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupform);
        btn = findViewById(R.id.sgsp);
        btn1 = findViewById(R.id.sguser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sgsp = new Intent(getApplicationContext(),signupsp.class);
                startActivity(sgsp);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sgus = new Intent(getApplicationContext(),signupuser.class);
                startActivity(sgus);
            }
        });

    }
}