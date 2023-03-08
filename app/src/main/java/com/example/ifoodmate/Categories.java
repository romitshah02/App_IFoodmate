package com.example.ifoodmate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

public class Categories extends AppCompatActivity implements Recyclerviewinterface1{
    ArrayList<recyclercat> cat_models = new ArrayList<>();

    private static final String url = "http://192.168.204.183/ifoodmate/all_categories.php";
    int[] catimg = {R.drawable.item_image,R.drawable.chinese,R.drawable.pasta,R.drawable.gujarati,R.drawable.gujarati,R.drawable.gujarati,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream,R.drawable.icecream};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        setupcatmodels();

    }

    private void setupcatmodels(){
        String[] catnames = getResources().getStringArray(R.array.cat_names);
        String cat_rest = "Available Catering Services ";

        /*for(int i = 0 ;i < catnames.length;i++)
        {
            cat_models.add(new recyclercat(catnames[i],cat_rest,catimg[i]));
        }*/


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.print("categories" + response.toString());
                try
                {
                    JSONArray array = response;

                    for (int i = 0 ; i < array.length();i++)
                    {
                        JSONObject object = array.getJSONObject(i);
                        String catname = object.getString("name");
                        String img =  object.getString("img");
                        String urlimage =  "http://192.168.204.183/ifoodmate/" + img;
                        recyclercat cat = new recyclercat(catname,cat_rest,urlimage);
                        cat_models.add(cat);
                    }
                                RecyclerView recyclerView = findViewById(R.id.r_categories);
        Cat_recyclerviewadapter adapter = new Cat_recyclerviewadapter(Categories.this,cat_models,Categories.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Categories.this));

                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onCatClick(int pos) {
        Intent intent = new Intent(getApplicationContext(),Caterors.class);
        intent.putExtra("catname",cat_models.get(pos).getCat_name());
        intent.putExtra("catpos",pos);

        startActivity(intent);
    }
}