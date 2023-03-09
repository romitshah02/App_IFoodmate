package com.example.ifoodmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class menu_items extends AppCompatActivity implements Itemviewinterface{
    ArrayList<recyclercat> cat_models = new ArrayList<>();
    ImageView img0;
    Button btn;
    private static final String url = "http://192.168.52.96/ifoodmate/menu_items.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_items);

        img0 = findViewById(R.id.menu_cat_img);








        String procider_name = getIntent().getStringExtra("catname");
        String provider_img = getIntent().getStringExtra("img");

        Glide.with(getApplicationContext()).load(provider_img).into(img0);
        setupcatmodels(procider_name);
    }

    private void setupcatmodels(String name){


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        Map<String,String> para = new HashMap<String,String>();
        para.put("catname",name);

        JSONObject object = new JSONObject(para);
        JSONArray array = new JSONArray();
        array.put(object);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, array, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.print("menu_items" + response.toString());
                try
                {
                    JSONArray array = response;

                    for (int i = 0 ; i < array.length();i++)
                    {
                        JSONObject object = array.getJSONObject(i);
                        String catname = object.getString("name");
                        String price = object.getString("price");
                        String img =  object.getString("img");
                        String urlimage =  "http://192.168.52.96/ifoodmate/" + img;
                        recyclercat cat = new recyclercat(catname,price,urlimage);
                        cat_models.add(cat);
                    }
                    RecyclerView recyclerView = findViewById(R.id.menu_items);
                    itemadapter adapter = new itemadapter(menu_items.this,cat_models, menu_items.this);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(menu_items.this));
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("erroritem" + error.toString());
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onItemClick(int pos) {

        Intent intent = new Intent(getApplicationContext(),itemdetails.class);
        intent.putExtra("name",cat_models.get(pos).getCat_name());
        intent.putExtra("price",cat_models.get(pos).getCat_rest());
        intent.putExtra("img",cat_models.get(pos).getImageid());
        startActivity(intent);
    }
}