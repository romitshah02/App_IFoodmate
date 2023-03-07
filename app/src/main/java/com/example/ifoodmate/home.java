package com.example.ifoodmate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class home extends Fragment {
    RelativeLayout all,offers ;

    ArrayList<main_recycler> cat_models = new ArrayList<>();

    private static final String url = "http://192.168.204.183/ifoodmate/all_sp.php";

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

   /* private void get_sp()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext().getApplicationContext());


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONArray array = response;

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        String user_no = String.valueOf(object.getInt("uno"));
                        String username = object.getString("uname");
                        String phone_no = object.getString("pno");
                        allusermodel user = new allusermodel(user_no, username, phone_no);
                        users.add(user);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                madapter = new useradapter(getApplicationContext(),users);
                recyclerView.setAdapter(madapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","OnErrorResponse" + error.getMessage());
            }
        });

        requestQueue.add(request);

    }*/



}