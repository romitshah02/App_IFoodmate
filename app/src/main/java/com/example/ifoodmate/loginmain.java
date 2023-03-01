package com.example.ifoodmate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class loginmain extends AppCompatActivity {
    Button btn;
    TextView tv,sk,sg,fg,sp;
    EditText ps;
    private static final String url = "http://192.168.170.120/ifoodmate/logincheck.php";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginmain);
        fg = findViewById(R.id.forgot);
        tv = findViewById(R.id.username);
        sg = findViewById(R.id.signup);
        sk = findViewById(R.id.visitor);
        btn=findViewById(R.id.login);
        ps = findViewById(R.id.Password);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                if(tv.getText().toString().equals("admin") && ps.getText().toString().equals("123")){
                    Toast.makeText(loginmain.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getApplicationContext(),admin_page.class);
                    editor.putBoolean("flag",true);
                    editor.apply();
                    startActivity(intent);
                }
                else if (tv.getText().toString().equals("") || ps.getText().toString().equals("")){
                    Toast.makeText(loginmain.this, "Please enter some details", Toast.LENGTH_SHORT).show();
                    ps.setError("Please Enter Password");
                    tv.setError("Please Enter Username");
                }
                else if (tv.getText().toString().equals("hello") && ps.getText().toString().equals("123"))
                {
                    Toast.makeText(loginmain.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getApplicationContext(),homepage.class);
                    editor.putBoolean("flag",true);
                    editor.apply();
                    startActivity(intent);
                }
                else
                {
                    logincheck(tv.getText().toString(),ps.getText().toString());
                }
            }

        });

        sg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent signup0 = new Intent(getApplicationContext(),signupform.class);
                startActivity(signup0);
            }
        });

        fg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgot = new Intent(getApplicationContext(),forget.class);
                startActivity(forgot);
            }
        });

        sk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent vispg = new Intent(getApplicationContext(),homepage.class);
                startActivity(vispg);
            }
        });




    }
    private void logincheck(String uname, String pass)
    {
        final String name = uname;
        final String pwd = pass;

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success"))
                {
                    Toast.makeText(loginmain.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginmain.this, homepage.class);
                    editor.putBoolean("flag", true);
                    editor.apply();
                    startActivity(intent);
                }
                else if (response.trim().equals("sp_success"))
                {
                    Toast.makeText(loginmain.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginmain.this, sp_profile.class);
                    editor.putBoolean("flag", true);
                    editor.apply();
                    startActivity(intent);
                }
                else if (response.trim().equals("failure"))
                {
                    Toast.makeText(loginmain.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> para = new HashMap<String,String>();
                para.put("USERNAME",name);
                para.put("us_pwd",pwd);
                return para;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }




}