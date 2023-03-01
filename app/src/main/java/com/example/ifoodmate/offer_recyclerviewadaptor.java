package com.example.ifoodmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class offer_recyclerviewadaptor extends RecyclerView .Adapter<offer_recyclerviewadaptor.Myviewholder>{

    Context context;
    ArrayList<offercat> offer_models;

    public offer_recyclerviewadaptor(Context context, ArrayList<offercat> offer_models) {
        this.context = context;
        this.offer_models = offer_models;
    }

    @NonNull
    @Override
    public offer_recyclerviewadaptor.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.offer_recycler , parent,false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull offer_recyclerviewadaptor.Myviewholder holder, int position) {
        holder.tv1.setText(offer_models.get(position).getPer());
        holder.tv2.setText(offer_models.get(position).getDet());

    }

    @Override
    public int getItemCount() {
        return offer_models.size();
    }

    public static class Myviewholder extends RecyclerView.ViewHolder
    {
        TextView tv1,tv2;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.off_per);
            tv2 = itemView.findViewById(R.id.offer_details);

        }
    }
}
