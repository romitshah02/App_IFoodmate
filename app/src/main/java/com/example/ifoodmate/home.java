package com.example.ifoodmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class home extends Fragment {
    RelativeLayout all,offers ;

    ArrayList<main_recycler> cat_models = new ArrayList<>();
    int[] catimg = {R.drawable.item_image,R.drawable.chinese,R.drawable.pasta,R.drawable.gujarati,R.drawable.gujarati,R.drawable.gujarati,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream};
    //@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,
                container, false);
        all = rootView.findViewById(R.id.all_cat);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent all_cat = new Intent(getActivity(), Categories.class);
                startActivity(all_cat);
            }
        });

        offers = rootView.findViewById(R.id.view_offers_main);
        offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.example.ifoodmate.offers.class);
                startActivity(intent);
            }
        });



        RecyclerView recyclerView = rootView.findViewById(R.id.r_items);
        setupcatmodels();
        mainadapter mainadapter = new mainadapter(rootView.getContext(), cat_models);
        recyclerView.setAdapter(mainadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        return rootView;

    }

    private void setupcatmodels(){
        String[] catnames = getResources().getStringArray(R.array.Service_Providers);
        for(int i = 0 ;i < catnames.length;i++)
        {
            cat_models.add(new main_recycler(catnames[i],catimg[i]));
        }
    }








}