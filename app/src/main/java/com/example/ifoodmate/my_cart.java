package com.example.ifoodmate;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.content.CursorLoader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ifoodmate.Database.OrderContract;
import com.example.ifoodmate.Database.db_handler;
import com.example.ifoodmate.model.Order;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;


public class my_cart extends Fragment  {

    public cartadapter madapter;
    db_handler db;
    TextView del,total;

@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_my_cart,
            container, false);


     db = new db_handler(getContext().getApplicationContext());
    List<Order> orderList = db.getAllItems();
    for(Order order : orderList)
    {
        System.out.println("orderlist" + order.getTotalprice().toString());
    }

    RecyclerView listView = rootView.findViewById(R.id.item_listview);
    madapter = new cartadapter(orderList,getContext().getApplicationContext());
    listView.setAdapter(madapter);
    listView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));

    del = rootView.findViewById(R.id.delete);
    del.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            db.delAllItems();
            orderList.clear();
            madapter.notifyDataSetChanged();
            listView.setAdapter(madapter);
            total.setText(String.valueOf(updateprice(orderList)));
        }
    });

    total = rootView.findViewById(R.id.tv_total_price);

    total.setText(String.valueOf(updateprice(orderList)));


    return rootView;
    }

    public int updateprice(List<Order> orderList)
    {
        int sum =0,i;
        for(i = 0; i < orderList.size();i++)
        {
            sum = sum + (Integer.parseInt(orderList.get(i).getTotalprice()));
        }
        return sum;
    }


}