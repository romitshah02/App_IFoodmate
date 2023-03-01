package com.example.ifoodmate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BaseHttpStack;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class user_admin extends AppCompatActivity {


    private static final String url = "http://192.168.170.120/ifoodmate/all_users.php";
    private ArrayList<allusermodel> users =  new ArrayList<>();
    private RecyclerView.Adapter madapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_admin);

        recyclerView = findViewById(R.id.r_users);
        get_users();


    }

    private void get_users()
    {

        StringRequest request1 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    JSONObject object = new JSONObject();
                    for (int i = 0; i < array.length(); i++) {
                        object = array.getJSONObject(i);
                        String user_no = object.getString("uno");
                        String username = object.getString("uname");
                        String phone_no = object.getString("pno");

                        allusermodel user = new allusermodel(user_no, username, phone_no);
                        users.add(user);
                    }
                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                }
                madapter = new useradapter(getApplicationContext(), users);
                recyclerView.setAdapter(madapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                try {
                    JSONArray array = new JSONArray(response);
                    JSONObject object = new JSONObject();
                    for (int i = 0; i < array.length(); i++) {
                        object = array.getJSONObject(i);
                        String user_no = object.getString("uno");
                        String username = object.getString("uname");
                        String phone_no = object.getString("pno");

                        allusermodel user = new allusermodel(user_no, username, phone_no);
                        users.add(user);
                    }
                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                }
                madapter = new useradapter(getApplicationContext(), users);
                recyclerView.setAdapter(madapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request1);


    }
}