package com.example.ifoodmate;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class mainadapter extends RecyclerView.Adapter<mainadapter.Myviewholder> {

    Context context;
    ArrayList<main_recycler> cat_models;

    public mainadapter(Context context, ArrayList<main_recycler> cat_models) {

        this.context = context;
        this.cat_models = cat_models;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.itemrecycler, parent,false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        holder.tv1.setText(cat_models.get(position).getProv_name());
        Glide.with(context).load(cat_models.get(position).getImg()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return cat_models.size();
    }


    public static class Myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView tv1;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.cate_img);
            tv1 = itemView.findViewById(R.id.cateror_name);

        }
    }
}
