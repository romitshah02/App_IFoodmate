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

public class Cat_recyclerviewadapter extends RecyclerView.Adapter<Cat_recyclerviewadapter.Myviewholder> {
   // private final Recyclerviewinterface1 recyclerviewinterface;
    Context context;
    ArrayList<recyclercat> cat_models;

    public Cat_recyclerviewadapter(Context context, ArrayList<recyclercat> cat_models,Recyclerviewinterface1 recyclerviewinterface) {
        this.context = context;
        this.cat_models = cat_models;
        //this.recyclerviewinterface = recyclerviewinterface;
    }


    @NonNull
    @Override
    public Cat_recyclerviewadapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //this is were you inflate the layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_row , parent,false);
        //return new Myviewholder(view , recyclerviewinterface);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cat_recyclerviewadapter.Myviewholder holder, int position) {
        //assigning values to the views in the recycler view
        holder.tv1.setText(cat_models.get(position).getCat_name());
        holder.tv2.setText(cat_models.get(position).getCat_rest());
        holder.img.setImageResource(cat_models.get(position).getImageid());
    }

    @Override
    public int getItemCount() {
        //no of items you want to display
        return cat_models.size();
    }

    public static class Myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView tv1,tv2;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView3);
            tv1 = itemView.findViewById(R.id.textView8);
            tv2 = itemView.findViewById(R.id.textView9);


           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerviewinterface != null)
                    {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION)
                        {
                            recyclerviewinterface.onCatClick(pos);
                        }
                    }
                }
            });*/
        }
    }
}
