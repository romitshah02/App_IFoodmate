package com.example.ifoodmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ifoodmate.Database.db_handler;
import com.example.ifoodmate.model.Order;

public class itemdetails extends AppCompatActivity  {

    TextView tv1,tv2,tv3;
    ImageView img;
    Button btn;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues = false;

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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    SaveCart();
            }
        });



    }


    private Boolean SaveCart() {
        String name = tv1.getText().toString();
        String price = tv2.getText().toString();
        String quantity = tv3.getText().toString();

        //Craeting item for the database
        db_handler db = new db_handler(itemdetails.this);
        Order ord = new Order(name,quantity,price);
        //adding item to the database
        db.addItem(ord);

        System.out.println("orderitems" + ord.toString());


        return true;
    }


}