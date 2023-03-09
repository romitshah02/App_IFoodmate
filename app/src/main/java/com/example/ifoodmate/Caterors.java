package com.example.ifoodmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Caterors extends AppCompatActivity implements Recyclerviewinterface1{
    ArrayList<recyclercat> cat_models = new ArrayList<>();
    private static final String url = "http://192.168.52.96/ifoodmate/cat_providers.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterors);
        String cat_name = getIntent().getStringExtra("catname");

        setupcatmodels(cat_name);
        System.out.println("passedcatname" + cat_name);

    }

  private void setupcatmodels(String name){

        String cat_rest = "Available Items ";



      RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
      Map<String,String> para = new HashMap<String,String>();
      para.put("catname",name);

        JSONObject object = new JSONObject(para);
        JSONArray array = new JSONArray();
        array.put(object);
        System.out.println("passedccat_name" + array.toString());



      JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, array, new Response.Listener<JSONArray>() {
          @Override
          public void onResponse(JSONArray response) {
              System.out.print("caterors" + response.toString());
              try
              {
                  JSONArray array = response;

                  for (int i = 0 ; i < array.length();i++)
                  {
                      JSONObject object = array.getJSONObject(i);
                      String catname = object.getString("name");
                      String img =  object.getString("img");
                      String urlimage =  "http://192.168.52.96/ifoodmate/" + img;
                      recyclercat cat = new recyclercat(catname,cat_rest,urlimage);
                      cat_models.add(cat);
                  }
                  RecyclerView recyclerView = findViewById(R.id.r_caterors);
                  Cat_recyclerviewadapter adapter = new Cat_recyclerviewadapter(Caterors.this,cat_models,Caterors.this);
                  recyclerView.setAdapter(adapter);
                  recyclerView.setLayoutManager(new LinearLayoutManager(Caterors.this));

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
        Intent intent = new Intent(getApplicationContext(),menu_items.class);
        intent.putExtra("catname",cat_models.get(pos).getCat_name());
        intent.putExtra("img",cat_models.get(pos).getImageid());
        startActivity(intent);
    }
}