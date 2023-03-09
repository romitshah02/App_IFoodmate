package com.example.ifoodmate.Database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OrderProvider extends ContentProvider {

    public static final int ORDER = 100;

    public static UriMatcher sUrimatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static
    {
        sUrimatcher.addURI(OrderContract.CONTENT_AUTHORITY,OrderContract.path,ORDER);
    }

    public OrderHelper mhelper;

    @Override
    public boolean onCreate() {
        mhelper = new OrderHelper(getContext());
        return true;
    }


    @Override
    public Cursor query( Uri uri,  String[] projection,  String selection,  String[] selectionArgs,  String sortOrder) {
        SQLiteDatabase database = mhelper.getReadableDatabase();
        Cursor cursor;
        int match = sUrimatcher.match(uri);
        switch (match)
        {
            case ORDER:
                cursor = database.query(OrderContract.orderentry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Cant query");
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }


    @Override
    public String getType( Uri uri) {
        return null;
    }


    @Override
    public Uri insert( Uri uri,  ContentValues values) {

        int match = sUrimatcher.match(uri);

        switch (match)
        {
            case ORDER:
                return insertcart(uri,values);

            default:
                throw new IllegalArgumentException("Cant insert data");
        }

    }

    private Uri insertcart(Uri uri, ContentValues values) {

        String name = values.getAsString(OrderContract.orderentry.COLUMN_NAME);
        if (name == null)
        {
            throw new IllegalArgumentException("No name");
        }

        String quantity = values.getAsString(OrderContract.orderentry.COLUMN_QUANTITY);
        if (quantity == null)
        {
            throw new IllegalArgumentException("No quantity");
        }

        String price = values.getAsString(OrderContract.orderentry.COLUMN_PRICE);
        if (price == null)
        {
            throw new IllegalArgumentException("No price");
        }

        SQLiteDatabase database = mhelper.getWritableDatabase();
        long id = database.insert(OrderContract.orderentry.TABLE_NAME,null,values);

        if (id == -1)
        {
            return null;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete( Uri uri,  String selection,  String[] selectionArgs) {

        int rowsDeleted;
        SQLiteDatabase database = mhelper.getWritableDatabase();
        int match = sUrimatcher.match(uri);

        switch (match)
        {
            case ORDER:
                rowsDeleted = database.delete(OrderContract.orderentry.TABLE_NAME,selection,selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("unable to delete");
        }

        if (rowsDeleted != 0)
        {
            getContext().getContentResolver().notifyChange(uri,null);
        }

        return rowsDeleted;
    }

    @Override
    public int update( Uri uri, ContentValues values,  String selection, String[] selectionArgs) {
        return 0;
    }
}
