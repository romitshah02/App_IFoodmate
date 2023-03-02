package com.example.ifoodmate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class sp_profile extends AppCompatActivity {
  Button btn;
  TextView name,add,phn,email,gst;
    SharedPreferences sharedPreferences;
    private static final String url = "http://192.168.170.120/ifoodmate/sp_details.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_profile);
        btn = findViewById(R.id.Manage_Category);

        sharedPreferences = getSharedPreferences("",MODE_PRIVATE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),add_items_sp.class);
                startActivity(intent);
            }
        });
        getdetails();

    }

    private void getdetails() {

        RequestQueue requestQueue = Volley.newRequestQueue(sp_profile.this);
        SharedPreferences preferences = getSharedPreferences("userid",MODE_PRIVATE);
        int id = preferences.getInt("userid",1010);
        Map<String,String> para = new HashMap<String,String>();
        para.put("uid", String.valueOf(id));

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,new  JSONObject(para), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try {
                        JSONObject object = response;
                    name = findViewById(R.id.catername);
                    add = findViewById(R.id.cataddress);
                    phn = findViewById(R.id.catphone);
                    email = findViewById(R.id.catEmail);
                    gst = findViewById(R.id.gstno);
                        name.setText(object.getString("name"));
                        add.setText(object.getString("add"));
                        phn.setText(object.getString("pno"));
                        email.setText(object.getString("mail"));
                        gst.setText(object.getString("gst"));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);

    }

}