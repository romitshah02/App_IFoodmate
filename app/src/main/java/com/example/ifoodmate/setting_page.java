package com.example.ifoodmate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class setting_page extends AppCompatActivity {

    Button btn;
    TextView name,add,phn,email;


    private static final String url = "http://192.168.174.249/ifoodmate/user_details.php";
    private static final String url2 = "http://192.168.174.249/ifoodmate/update_user.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        name = findViewById(R.id.user_name_setting);
        add = findViewById(R.id.user_address);
        phn = findViewById(R.id.user_phone_no);
        email = findViewById(R.id.user_email);

        getdetails();

        btn = findViewById(R.id.user_update);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = name.getText().toString();
                final String address = add.getText().toString();
                final String phoneno = phn.getText().toString();
                final String mail = email.getText().toString();


                RequestQueue requestQueue = Volley.newRequestQueue(setting_page.this);
                SharedPreferences preferences = getSharedPreferences("userid",MODE_PRIVATE);
                int id = preferences.getInt("uid",001);
                Map<String,String> para = new HashMap<String,String>();
                para.put("uid", String.valueOf(id));
                para.put("username", username);
                para.put("add", address);
                para.put("pno", phoneno);
                para.put("mail", mail);

                JSONObject object = new JSONObject(para);

                System.out.println("userupdateidshow"+object.toString());

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url2,object, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try {
                            JSONObject object = response;

                            if (response.toString().equals("failure"))
                            {
                                Toast.makeText(setting_page.this,"Unable to get details server error",Toast.LENGTH_LONG);
                            }
                            else
                            {

                                name.setText(object.getString("uname"));
                                add.setText(object.getString("add"));
                                phn.setText(object.getString("pno"));
                                email.setText(object.getString("mail"));
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

                    }
                });
                requestQueue.add(request);
            }
        });
    }


    private void getdetails() {

        RequestQueue requestQueue = Volley.newRequestQueue(setting_page.this);
        SharedPreferences preferences = getSharedPreferences("userid",MODE_PRIVATE);
        int id = preferences.getInt("uid",001);
        Map<String,String> para = new HashMap<String,String>();
        para.put("uid", String.valueOf(id));

        JSONObject object = new JSONObject(para);

        System.out.println("useridshow"+object.toString());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try {
                    JSONObject object = response;

                    if (response.toString().equals("failure"))
                    {
                        Toast.makeText(setting_page.this,"Unable to get details server error",Toast.LENGTH_LONG);
                    }
                    else
                    {
                        name = findViewById(R.id.user_name_setting);
                        add = findViewById(R.id.user_address);
                        phn = findViewById(R.id.user_phone_no);
                        email = findViewById(R.id.user_email);

                        name.setText(object.getString("uname"));
                        add.setText(object.getString("add"));
                        phn.setText(object.getString("pno"));
                        email.setText(object.getString("mail"));

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