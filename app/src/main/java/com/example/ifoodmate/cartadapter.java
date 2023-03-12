package com.example.ifoodmate;

import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ifoodmate.Database.OrderContract;
import com.example.ifoodmate.model.Order;
import com.example.ifoodmate.params.Params;

import java.util.List;

public class cartadapter extends RecyclerView.Adapter<cartadapter.myviewholder> {

    Context context;
    List<Order> orders;

    public cartadapter(List<Order> orders,Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public cartadapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cartlist,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cartadapter.myviewholder holder, int position)
    {
        holder.tv1.setText(orders.get(position).getName());
        holder.tv2.setText(orders.get(position).getQuantity());
        holder.tv3.setText(orders.get(position).getTotalprice());

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder
    {
        TextView tv1,tv2,tv3;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.itemNameinOrderSummary);
            tv2 = itemView.findViewById(R.id.quantityinOrderSummary);
            tv3 = itemView.findViewById(R.id.priceinOrderSummary);
        }
    }

    /*
    public void updateprice()
    {
        int sum = 0,i;

    }*/
}
