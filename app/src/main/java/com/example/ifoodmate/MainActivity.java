package com.example.ifoodmate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {




    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = getSharedPreferences("",MODE_PRIVATE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
                Boolean check =  pref.getBoolean("flag",false);
                SharedPreferences name = getSharedPreferences("usertype",MODE_PRIVATE);
                String type = name.getString("type","visitor");



                Intent inext;
                if (check)
                {

                    if (type == "user")
                    {
                        inext = new Intent(MainActivity.this, homepage.class);
                    }
                    else if (type == "provider")
                    {
                        inext = new Intent(MainActivity.this,sp_profile.class);
                    }
                    else
                    {
                        inext = new Intent(MainActivity.this,loginmain.class);
                    }
                }
                else
                {
                    inext = new Intent(MainActivity.this,loginmain.class);
                }
                startActivity(inext);
            }
        },1000);

    }



}


