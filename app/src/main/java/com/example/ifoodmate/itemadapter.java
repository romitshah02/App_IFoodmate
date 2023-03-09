package com.example.ifoodmate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class itemadapter extends RecyclerView.Adapter<itemadapter.Myviewholder> {


    Context context;
    ArrayList<recyclercat> cat_models;

    private final Itemviewinterface itemviewinterface;

    public itemadapter(Context context, ArrayList<recyclercat> cat_models, Itemviewinterface itemviewinterface) {
        this.context = context;
        this.cat_models = cat_models;
        this.itemviewinterface = itemviewinterface;
    }


    @NonNull
    @Override
    public itemadapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //this is were you inflate the layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.itemmodel, parent,false);
        return new Myviewholder(view , itemviewinterface);
    }

    @Override
    public void onBindViewHolder(@NonNull itemadapter.Myviewholder holder, int position) {
        //assigning values to the views in the recycler view
        holder.tv1.setText(cat_models.get(position).getCat_name());
        holder.tv2.setText(cat_models.get(position).getCat_rest());
        Glide.with(context).load(cat_models.get(position).getImageid()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        //no of items you want to display
        return cat_models.size();
    }

    public static class Myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img;
        Button btn;
        TextView tv1,tv2;
        public Myviewholder(@NonNull View itemView,Itemviewinterface itemviewinterface) {
            super(itemView);
            img = itemView.findViewById(R.id.item_img);
            tv1 = itemView.findViewById(R.id.item_name);
            tv2 = itemView.findViewById(R.id.item_price);
            btn = itemView.findViewById(R.id.item_add_button);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  if (itemviewinterface != null)
                  {
                      int pos = getAdapterPosition();

                      if (pos != RecyclerView.NO_POSITION)
                      {
                          itemviewinterface.onItemClick(pos);
                      }
                  }
                }
            });

        }
    }
}
