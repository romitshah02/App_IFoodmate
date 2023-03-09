package com.example.ifoodmate;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.content.CursorLoader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ifoodmate.Database.OrderContract;

import org.jetbrains.annotations.Nullable;

import java.util.Currency;


public class my_cart extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public cartadapter madapter;
    public static int loader = 0;

@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_my_cart,
            container, false);




    TextView del = rootView.findViewById(R.id.delete);

    del.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int deletethedata = getActivity().getContentResolver().delete(OrderContract.orderentry.Content_uri, null, null);
        }
    });

    getActivity().getLoaderManager().initLoader(loader, null, this);
    ListView listView = rootView.findViewById(R.id.item_listview);
    madapter = new cartadapter(getContext().getApplicationContext(),null);
    listView.setAdapter(madapter);

    return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {OrderContract.orderentry._ID,
                OrderContract.orderentry.COLUMN_NAME,
                OrderContract.orderentry.COLUMN_PRICE,
                OrderContract.orderentry.COLUMN_QUANTITY,
    };
        return new CursorLoader(getContext().getApplicationContext(), OrderContract.orderentry.Content_uri,
                projection,
                null,
                null,
                null);

    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        madapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    madapter.swapCursor(null);

    }
}