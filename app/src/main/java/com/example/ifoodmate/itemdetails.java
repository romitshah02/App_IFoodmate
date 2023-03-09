package com.example.ifoodmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class itemdetails extends AppCompatActivity {

    TextView tv1,tv2,tv3;
    ImageView img;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemdetails);
        tv1 = findViewById(R.id.item_title);
        tv2 = findViewById(R.id.item_details_price);
        tv3 = findViewById(R.id.item_detail_qt);
        img = findViewById(R.id.item_detail_img);
        btn = findViewById(R.id.add_item_to_cart);

        String name = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");
        String img0 = getIntent().getStringExtra("img");

        Glide.with(getApplicationContext()).load(img0).into(img);
        tv1.setText(name);
        tv2.setText(price);


    }
}