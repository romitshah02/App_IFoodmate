package com.example.ifoodmate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

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


public class home extends Fragment implements Recyclerviewinterface1 {
    RelativeLayout all,offers,punjabi,chinese,italian ;
    RecyclerView recyclerView;


    ArrayList<main_recycler> cat_models = new ArrayList<>();

    private static final String url = "http://192.168.255.115/ifoodmate/top_sp.php";

    //@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,
                container, false);

        all = rootView.findViewById(R.id.all_cat);
        punjabi = rootView.findViewById(R.id.punjabi_home);
        chinese = rootView.findViewById(R.id.chinese_home);
        italian = rootView.findViewById(R.id.italian_home);


        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent all_cat = new Intent(getActivity(), Categories.class);
                startActivity(all_cat);
            }
        });

        punjabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent all_cat = new Intent(getActivity(), Caterors.class);
                all_cat.putExtra("catname","punjabi");
                startActivity(all_cat);
            }
        });

        chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent all_cat = new Intent(getActivity(), Caterors.class);
                all_cat.putExtra("catname","chinese");
                startActivity(all_cat);
            }
        });

        italian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent all_cat = new Intent(getActivity(), Caterors.class);
                all_cat.putExtra("catname","italian");
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


        rootView = get_sp(rootView);
        return rootView;

    }

        private View get_sp(View view)
        {


            RequestQueue requestQueue = Volley.newRequestQueue(getContext().getApplicationContext());


            JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("topspresponse" + response.toString());
                    try {
                        JSONArray array = response;

                        for (int i = 0; i < 10; i++) {
                            JSONObject object = array.getJSONObject(i);
                            String name = object.getString("name");
                            String img = object.getString("img");
                            String urlimage = "http://192.168.255.115/ifoodmate/providers/" + img;
                            main_recycler user = new main_recycler(name, urlimage);
                            cat_models.add(user);
                        }

                    } catch (Exception e) {

                    }
                    recyclerView = view.findViewById(R.id.r_items);
                    mainadapter mainadapter = new mainadapter(view.getContext(),cat_models,home.this);
                    recyclerView.setAdapter(mainadapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            });

            requestQueue.add(request);
            return view;
        }

    @Override
    public void onCatClick(int pos) {
        Intent intent = new Intent(getContext().getApplicationContext(),menu_items.class);
        intent.putExtra("catname",cat_models.get(pos).getProv_name());
        intent.putExtra("img",cat_models.get(pos).getImg());
        intent.putExtra("name","all");
        startActivity(intent);
    }
}

