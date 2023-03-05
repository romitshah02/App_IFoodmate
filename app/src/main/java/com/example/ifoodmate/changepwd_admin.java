package com.example.ifoodmate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
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

public class changepwd_admin extends AppCompatActivity {


    TextView tv1,tv2,tv3;
    Button btn;

    private static final String url = "http://192.168.174.249/ifoodmate/update_user_pwd.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepwd_admin);

        tv1 = findViewById(R.id.old_password);
        tv2 = findViewById(R.id.new_password);
        tv3 = findViewById(R.id.confirm_new_password);
        btn = findViewById(R.id.change_pwd_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (password())
                {
                    SharedPreferences preferences = getSharedPreferences("userid",MODE_PRIVATE);
                    int id = preferences.getInt("uid",001);

                    final String old =  tv1.getText().toString();
                    final String new_pwd = tv2.getText().toString();

                    Map<String,String> para = new HashMap<String,String>();
                    para.put("pwd",old);
                    para.put("new_pwd",new_pwd);
                    para.put("uid", String.valueOf(id));

                    JSONObject object = new JSONObject(para);

                    RequestQueue requestQueue = Volley.newRequestQueue(changepwd_admin.this);

                    System.out.println("passwordchg" + object.toString());

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response)
                        {
                            try
                            {
                             JSONObject object1 = response;
                             if (response.toString().equals("failure"))
                             {
                                 Toast.makeText(changepwd_admin.this,"failed to  update password",Toast.LENGTH_LONG).show();
                             }
                             else
                             {
                                 Toast.makeText(changepwd_admin.this,"password updated",Toast.LENGTH_LONG).show();
                             }

                            }
                            catch (Exception e)
                            {

                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            Toast.makeText(changepwd_admin.this,error.toString(),Toast.LENGTH_LONG);
                        }
                    });

                    requestQueue.add(jsonObjectRequest);

                }
                else
                {
                    Toast.makeText(changepwd_admin.this, "Please Check All the details", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public boolean password() {
        String pass = tv2.getEditableText().toString().trim();
        String confirm = tv3.getEditableText().toString().trim();
        if (pass.isEmpty() || confirm.isEmpty() ) {
            return false;
        }
        else if (pass.matches(confirm.toString()))
        {
            return true;
        }
        else
        {
            tv2.setError("Password and confirm password are not same");
            tv3.setError("Password and confirm password are not same");
            return false;
        }
    }
}