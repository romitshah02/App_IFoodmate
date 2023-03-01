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

public class useradapter extends RecyclerView.Adapter<useradapter.Myviewholder> {

    Context context;
    ArrayList<allusermodel> user_models;

    public useradapter(Context context, ArrayList<allusermodel> user_models) {

        this.context = context;
        this.user_models = useradapter.this.user_models;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_recycler, parent,false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        holder.tv1.setText(user_models.get(position).getU_no());
        holder.tv2.setText(user_models.get(position).getU_name());
        holder.tv3.setText(user_models.get(position).getP_no());
    }

    @Override
    public int getItemCount() {
        return user_models.size();
    }


    public static class Myviewholder extends RecyclerView.ViewHolder
    {
        TextView tv1,tv2,tv3;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.usernum);
            tv2 = itemView.findViewById(R.id.username);
            tv3 = itemView.findViewById(R.id.phone_no);

        }
    }
}
