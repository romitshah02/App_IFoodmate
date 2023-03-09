package com.example.ifoodmate.Database;

import android.net.Uri;
import android.provider.BaseColumns;

public class OrderContract {

    public OrderContract()
    {}

    public static final String CONTENT_AUTHORITY = "com.example.ifoodmate";
    public static final Uri BASE_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String path = "orderig";

    public static abstract class orderentry implements BaseColumns
    {
        public static final Uri Content_uri = Uri.withAppendedPath(BASE_URI,path);

        public static final String TABLE_NAME = "orderig";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_QUANTITY = "qty";
        public static final String COLUMN_PRICE = "price";

    }
}
