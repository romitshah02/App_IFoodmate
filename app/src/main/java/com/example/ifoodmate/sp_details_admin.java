package com.example.ifoodmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class sp_details_admin extends AppCompatActivity {
    List<allusermodel> users =  new ArrayList<allusermodel>();
    RecyclerView.Adapter madapter;
    RecyclerView recyclerView;
    private static final String url = "http://192.168.174.249/ifoodmate/all_sp.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_details_admin);

        recyclerView = findViewById(R.id.r_sp);
        get_users();

    }


    private void get_users()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(sp_details_admin.this);


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

    }
}