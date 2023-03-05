package com.example.ifoodmate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class loginmain extends AppCompatActivity {
    Button btn;
    TextView tv,sk,sg,fg;
    EditText ps;
    private static final String url = "http://192.168.174.249/ifoodmate/logincheck.php";

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

                if(tv.getText().toString().equals("admin") && ps.getText().toString().equals("123")){
                    Toast.makeText(loginmain.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getApplicationContext(),admin_page.class);
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
                    SharedPreferences user = getSharedPreferences("user",MODE_PRIVATE);
                    SharedPreferences.Editor editor = user.edit();
                    editor.putBoolean("uservalue",true);
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





        HashMap<String, String> params = new HashMap<>();
        params.put("us_pwd",pwd);
        params.put("USERNAME",name);


        JSONObject jsonObject = new JSONObject(params);
        System.out.println("logincheck" + jsonObject.toString());




        RequestQueue requestQueue = Volley.newRequestQueue(loginmain.this);
        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    JSONObject object = response;
                    String value = object.getString("value");

                     //sharedpreferences for storing user data when logged in

                    //SharedPreferences spname = getSharedPreferences("provider",MODE_PRIVATE);
                    SharedPreferences login = getSharedPreferences("login",MODE_PRIVATE);
                    SharedPreferences name = getSharedPreferences("user",MODE_PRIVATE);
                    SharedPreferences uid = getSharedPreferences("userid",MODE_PRIVATE);

                    System.out.println("useroutput" + value);

                    //sharedpref editor for setting the value
                    //SharedPreferences.Editor editor = spname.edit();
                    SharedPreferences.Editor userid = uid.edit();
                    SharedPreferences.Editor check = login.edit();
                    SharedPreferences.Editor usertype = name.edit();


                    if (value.equals("success"))
                    {
                        String user_id = object.getString("uid");
                            Toast.makeText(loginmain.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(loginmain.this, homepage.class);

                            usertype.putBoolean("uservalue",true);
                            userid.putInt("uid", Integer.parseInt(user_id));
                            check.putBoolean("value",true);
                            usertype.apply();
                            userid.apply();
                            check.apply();

                            startActivity(intent);
                    }
                    else if (value.equals("sp_success"))
                    {
                        String user_id = object.getString("uid");
                        Toast.makeText(loginmain.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(loginmain.this, sp_profile.class);

                        usertype.putBoolean("uservalue",false);
                        //editor.putBoolean("providervalue", true);
                        check.putBoolean("value",true);
                        userid.putInt("uid",Integer.parseInt(user_id));

                        check.apply();
                        userid.apply();
                        //editor.apply();


                        startActivity(intent);

                    } else if (value.equals("failure")) {
                        Toast.makeText(getApplicationContext(),"No Such User",Toast.LENGTH_LONG).show();


                    } else
                    {
                        Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();

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
        }
        );

        requestQueue.add(request1);


    }




}