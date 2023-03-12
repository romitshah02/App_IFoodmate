package com.example.ifoodmate.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.ifoodmate.model.Order;
import com.example.ifoodmate.params.Params;

import java.util.ArrayList;
import java.util.List;

public class db_handler extends SQLiteOpenHelper {

    public db_handler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_TABLE = " CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Params.ORDER_NAME + " TEXT NOT NULL , "
                + Params.ORDER_QUANTITY + " TEXT NOT NULL, "

                + Params.ORDER_PRICE + " TEXT NOT NULL);";

        db.execSQL(SQL_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addItem(Order ord)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.ORDER_NAME,ord.getName());
        values.put(Params.ORDER_QUANTITY,ord.getQuantity());
        values.put(Params.ORDER_PRICE,ord.getPrice());

        db.insert(Params.TABLE_NAME,null,values);
        db.close();

    }

    public List<Order> getAllItems()
    {
        List<Order> orderList = new ArrayList<>();

        SQLiteDatabase db =this.getReadableDatabase();


        String select = " SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);

        //loop through now
        if(cursor.moveToFirst())
        {
            do
            {
             Order order = new Order();
             order.setName(cursor.getString(1));
             order.setQuantity(cursor.getString(2));
             order.setPrice(cursor.getString(3));
            int pr = Integer.parseInt(cursor.getString(3));
            int qt = Integer.parseInt(cursor.getString(2));
            int tprice = pr * qt;
            order.setTotalprice(String.valueOf(tprice));

             orderList.add(order);
            }
            while (cursor.moveToNext());
        }

        return orderList;
    }




    public void delAllItems()
    {
        SQLiteDatabase db =this.getReadableDatabase();
        String select = " DELETE FROM " + Params.TABLE_NAME;
        db.execSQL(select);

    }
}
