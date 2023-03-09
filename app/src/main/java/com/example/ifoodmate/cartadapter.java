package com.example.ifoodmate;

import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;

import com.example.ifoodmate.Database.OrderContract;

public class cartadapter extends CursorAdapter {
    public cartadapter(Context context, Cursor curson) {
        super(context, curson,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
      return  LayoutInflater.from(context).inflate(R.layout.cartlist,parent,false);


    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView itemname,price,quantity;

        itemname = view.findViewById(R.id.drinkNameinOrderSummary);
        price = view.findViewById(R.id.priceinOrderSummary);
        quantity = view.findViewById(R.id.quantityinOrderSummary);


        int name = cursor.getColumnIndex(OrderContract.orderentry.COLUMN_NAME);
        int priceofitem = cursor.getColumnIndex(OrderContract.orderentry.COLUMN_PRICE);
        int quantityofitem = cursor.getColumnIndex(OrderContract.orderentry.COLUMN_QUANTITY);


        String nameofdrink = cursor.getString(name);
        int pricesofitem = cursor.getInt(priceofitem);
        int quantitysofitem = cursor.getInt(quantityofitem);

        int finalprice = pricesofitem * quantitysofitem;


        itemname.setText(nameofdrink);
        price.setText(String.valueOf(finalprice));
        quantity.setText(String.valueOf(quantitysofitem));

    }


}
