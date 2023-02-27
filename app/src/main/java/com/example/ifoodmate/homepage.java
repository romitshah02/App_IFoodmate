package com.example.ifoodmate;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homepage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_bar);
bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedlistner);
        getSupportFragmentManager().beginTransaction().replace(R.id.bottom_layout, new home()).commit();

    }

     private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedlistner = new BottomNavigationView.OnNavigationItemSelectedListener() {
         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem item) {

             Fragment sf = null;
             switch (item.getItemId())
             {
                 case R.id.home:
                    sf = new home();
                    break;
                 case R.id.cart:
                     sf = new my_cart();
                     break;
                 case R.id.order:
                     sf = new orders();
                     break;
                 case R.id.profile:
                     sf = new my_profile();
                     break;

             }
             getSupportFragmentManager().beginTransaction().replace(R.id.bottom_layout,sf).commit();
             return true;
         }
     };

}