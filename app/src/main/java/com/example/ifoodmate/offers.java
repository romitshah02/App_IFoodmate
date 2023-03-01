package com.example.ifoodmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class offers extends AppCompatActivity {
    ArrayList<offercat> offer_models = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offers);
        RecyclerView recyclerView = findViewById(R.id.r_offer);
        setupoffermodels();
        offer_recyclerviewadaptor adapter = new offer_recyclerviewadaptor(this,offer_models);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    private void setupoffermodels(){
        String[] offerper = getResources().getStringArray(R.array.Offer_Percentage);
        String[] offerdes = getResources().getStringArray(R.array.offer_description);

        for(int i = 0 ;i < offerper.length;i++)
        {
            offer_models.add(new offercat(offerper[i],offerdes[i]));
        }
    }
}