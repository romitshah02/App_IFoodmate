package com.example.ifoodmate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class changepwd_admin extends AppCompatActivity {


    TextView tv1,tv2,tv3;
    Button btn;

    private static final String url = "http://192.168.204.183/ifoodmate/update_user_pwd.php";
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


                if (password() && samepassword())
                {
                    SharedPreferences preferences = getSharedPreferences("userid",MODE_PRIVATE);
                    int id = preferences.getInt("uid",001);
                    final String old =  tv1.getText().toString();
                    final String new_pwd = tv2.getText().toString();
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals("success"))
                            {
                                Toast.makeText(getApplicationContext(),"updated",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(),my_profile.class);
                                startActivity(intent);
                            }
                            else
                            {

                                Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                        }
                    })
                    {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> para = new HashMap<String,String>();
                            para.put("pwd",old);
                            para.put("new_pwd",new_pwd);
                            para.put("uid", String.valueOf(id));

                            return para;
                        }
                    };

                    requestQueue.add(stringRequest);

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

    public boolean samepassword() {
        String pass = tv1.getEditableText().toString().trim();
        String confirm = tv2.getEditableText().toString().trim();
        if (pass.isEmpty() || confirm.isEmpty() ) {
            return false;
        }
        else if (pass.matches(confirm.toString()))
        {
            tv1.setError("same password");
            tv2.setError("same password");
            return false;
        }
        else
        {
            return true;
        }
    }
}