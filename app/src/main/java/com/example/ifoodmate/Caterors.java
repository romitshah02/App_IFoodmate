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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Caterors extends AppCompatActivity implements Recyclerviewinterface1{
    ArrayList<recyclercat> cat_models = new ArrayList<>();
    private static final String url = "http://192.168.204.183/ifoodmate/cat_providers.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterors);
        String[] cat_name = getIntent().getStringArrayExtra("catname");
        setupcatmodels(cat_name);

    }

  private void setupcatmodels(String[] name){
        //String[] catnames = getResources().getStringArray(R.array.Service_Providers);
        String cat_rest = "Available Items ";

       /* for(int i = 0 ;i < catnames.length;i++)
        {
            cat_models.add(new recyclercat(catnames[i],cat_rest,catimg[i]));
        }*/

      RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
      Map<String,String> para = new HashMap<String,String>();
      para.put("catname",String.valueOf(name));

      JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
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
                      String urlimage =  "http://192.168.204.183/ifoodmate/" + img;
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
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}