package com.example.ifoodmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Intent;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ifoodmate.Database.OrderContract;

public class itemdetails extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

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


        int pr;



        Glide.with(getApplicationContext()).load(img0).into(img);
        tv1.setText(name);
        tv2.setText(price);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment =new my_cart();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.item_container,fragment).commit();

                SaveCart();
            }
        });


    }

    private int calculateprice(String price, String quantity) {
        int pr = Integer.parseInt(price);
        int qty = Integer.parseInt(quantity);

        int final_price = pr * qty;

        return final_price;
    }

    private Boolean SaveCart() {
        String name = tv1.getText().toString();
        String price = tv2.getText().toString();
        String quantity = tv3.getText().toString();

        ContentValues values = new ContentValues();
        values.put(OrderContract.orderentry.COLUMN_NAME,name);
        values.put(OrderContract.orderentry.COLUMN_QUANTITY,quantity);
        values.put(OrderContract.orderentry.COLUMN_PRICE,price);

        if (mCurrentCartUri == null) {
            Uri newUri = getContentResolver().insert(OrderContract.orderentry.Content_uri, values);
            if (newUri==null) {
                Toast.makeText(this, "Failed to add to Cart", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Success  adding to Cart", Toast.LENGTH_SHORT).show();

            }
        }


        hasAllRequiredValues = true;
        return hasAllRequiredValues;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {OrderContract.orderentry._ID,
                OrderContract.orderentry.COLUMN_NAME,
                OrderContract.orderentry.COLUMN_PRICE,
                OrderContract.orderentry.COLUMN_QUANTITY
    };

        return new CursorLoader(this, mCurrentCartUri,
                projection,
                null,
                null,
                null);
    }



    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {

            int name = cursor.getColumnIndex(OrderContract.orderentry.COLUMN_NAME);
            int price = cursor.getColumnIndex(OrderContract.orderentry.COLUMN_PRICE);
            int quantity = cursor.getColumnIndex(OrderContract.orderentry.COLUMN_QUANTITY);



            String nameofdrink = cursor.getString(name);
            String priceofdrink = cursor.getString(price);
            String quantityofdrink = cursor.getString(quantity);


            tv1.setText(nameofdrink);
            tv2.setText(priceofdrink);
            tv3.setText(quantityofdrink);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        tv1.setText("");
        tv2.setText("");
        tv3.setText("");

    }
}