package com.example.ifoodmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class update_sp_profile extends AppCompatActivity {
Button btn,btn2;

 private static final String url = "http://192.168.204.183/ifoodmate/update_sp_details.php";
    private static final String url2 = "http://192.168.204.183/ifoodmate/sp_details.php";
TextView name,add,phn,email,new_gst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_sp_profile);

        name = findViewById(R.id.new_catername);
       add = findViewById(R.id.new_cat_address);
       phn = findViewById(R.id.new_cat_phone);
       email = findViewById(R.id.new_cat_email);
        new_gst = findViewById(R.id.new_cat_gst_no);



        btn = findViewById(R.id.sp_update_profile);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = name.getText().toString();
                final String address = add.getText().toString();
                final String phoneno = phn.getText().toString();
                final String mail = email.getText().toString();
                final String gst = new_gst.getText().toString();


                RequestQueue requestQueue = Volley.newRequestQueue(update_sp_profile.this);
                SharedPreferences preferences = getSharedPreferences("userid",MODE_PRIVATE);
                int id = preferences.getInt("uid",001);
                Map<String,String> para = new HashMap<String,String>();
                para.put("uid", String.valueOf(id));
                para.put("name", username);
                para.put("add", address);
                para.put("pno", phoneno);
                para.put("mail", mail);
                para.put("gst",gst);

                JSONObject object = new JSONObject(para);

                System.out.println("userupdateidshow"+object.toString());

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,object, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try {
                            JSONObject object = response;

                            if (response.toString().equals("failure"))
                            {
                                Toast.makeText(update_sp_profile.this,"Unable to get details server error",Toast.LENGTH_LONG).show();
                            }
                            else
                            {

                                name.setText(object.getString("name"));
                                add.setText(object.getString("add"));
                                phn.setText(object.getString("pno"));
                                email.setText(object.getString("mail"));
                                new_gst.setText(object.getString("gst"));
                                Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
                requestQueue.add(request);
            }
        });

        btn2 = findViewById(R.id.sp_chn_pass);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),sp_change_password.class);
                startActivity(intent);
            }
        });

        getdetails();

    }

    private void getdetails() {

        RequestQueue requestQueue = Volley.newRequestQueue(update_sp_profile.this);
        SharedPreferences preferences = getSharedPreferences("userid",MODE_PRIVATE);
        int id = preferences.getInt("uid",001);
        Map<String,String> para = new HashMap<String,String>();
        para.put("uid", String.valueOf(id));

        JSONObject object = new JSONObject(para);

        System.out.println("useridshow"+object.toString());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url2,object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try {
                    JSONObject object = response;

                    if (response.toString().equals("failure"))
                    {
                        Toast.makeText(update_sp_profile.this,"Failed to login",Toast.LENGTH_LONG);
                        Intent intent = new Intent(update_sp_profile.this,loginmain.class);
                        startActivity(intent);
                    }
                    else
                    {
                        name.setText(object.getString("name"));
                        add.setText(object.getString("add"));
                        phn.setText(object.getString("pno"));
                        email.setText(object.getString("mail"));
                        new_gst.setText(object.getString("gst"));
                    }
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