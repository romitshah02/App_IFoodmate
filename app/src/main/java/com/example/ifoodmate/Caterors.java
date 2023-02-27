package com.example.ifoodmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class Caterors extends AppCompatActivity implements Recyclerviewinterface1{
    ArrayList<recyclercat> cat_models = new ArrayList<>();
    int[] catimg = {R.drawable.item_image,R.drawable.chinese,R.drawable.pasta,R.drawable.gujarati,R.drawable.gujarati,R.drawable.gujarati,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterors);
        RecyclerView recyclerView = findViewById(R.id.r_caterors);
        setupcatmodels();
        Cat_recyclerviewadapter adapter = new Cat_recyclerviewadapter(this,cat_models,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupcatmodels(){
        String[] catnames = getResources().getStringArray(R.array.Service_Providers);
        String cat_rest = "Available Items :>";

        for(int i = 0 ;i < catnames.length;i++)
        {
            cat_models.add(new recyclercat(catnames[i],cat_rest,catimg[i]));
        }
    }

    @Override
    public void onCatClick(int pos) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}