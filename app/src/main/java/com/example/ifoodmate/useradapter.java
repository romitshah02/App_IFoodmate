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
import java.util.List;

public class useradapter extends RecyclerView.Adapter<useradapter.viewHolder> {

    Context context;
    List<allusermodel> user_models1;

    public useradapter(Context context, List<allusermodel> user_models) {

        this.context = context;
        this.user_models1 = user_models;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_recycler, parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.tv1.setText(user_models1.get(position).getU_no());
        holder.tv2.setText(user_models1.get(position).getU_name());
        holder.tv3.setText(user_models1.get(position).getP_no());
    }

    @Override
    public int getItemCount() {
        return user_models1.size();
    }


    public static class viewHolder extends RecyclerView.ViewHolder
    {
        TextView tv1,tv2,tv3;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.usernum);
            tv2 = itemView.findViewById(R.id.username);
            tv3 = itemView.findViewById(R.id.phoneno);

        }
    }


}
