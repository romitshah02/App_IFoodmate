package com.example.ifoodmate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class signupuser extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8;
    Button btn;
    private static final String url = "http://192.168.170.120/ifoodmate/insert_user.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupuser);
        btn = findViewById(R.id.signup_user);
        tv1 = findViewById(R.id.Email);
        tv2 = findViewById(R.id.new_username);
        tv3 = findViewById(R.id.phone_no);
        tv4 = findViewById(R.id.address);
        tv5 = findViewById(R.id.password);
        tv6 = findViewById(R.id.confirm_password);
        tv7 = findViewById(R.id.gender);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (confirmInput(view) == true)
                {
                    insertdata(tv1,tv2,tv3,tv4,tv5,tv7);
                    Intent login = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(login);
                }
                else
                {
                    Toast.makeText(signupuser.this, "Please Check All the details", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void insertdata(TextView tv1,TextView tv2,TextView tv3,TextView tv4,TextView tv5,TextView tv6)
    {
        final String name = tv2.getText().toString().trim();
        final String email = tv1.getText().toString().trim();
        final String phone_no = tv3.getText().toString().trim();
        final String address = tv4.getText().toString().trim();
        final String password = tv5.getText().toString().trim();
        final String gender = tv6.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();

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
                para.put("EMAIL", email);
                para.put("PHONE_NO",phone_no);
                para.put("add",address);
                para.put("us_pwd",password);
                para.put("gen",gender);

                return para;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }


    public boolean confirmInput(View view) {
        if (!validatemail() | !validateUsername() | !phone_no() | !address() | !password() | !gender()) {
            return false;
        }
        else
        {
            return true;
        }
    }

        public boolean validatemail()
        {
            String email = tv1.getEditableText().toString().trim();

            if (email.isEmpty())
            {
                tv1.setError("Email cannot be empty");
                return false;
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                tv1.setError("Enter valid email address");
                return false;
            }
            else
            {
                return true;
            }

        }



        public boolean validateUsername() {
            String usernameInput = tv2.getEditableText().toString().trim();

            if (usernameInput.isEmpty()) {
                tv2.setError("username cannot be empty");
                return false;
            } else if (usernameInput.length() < 5)
            {
                tv2.setError("Username should be greater than 5 characters");
                return false;
            } else
            {
                return true;
            }
        }

    public boolean phone_no() {
        String name = tv3.getEditableText().toString().trim();

        if (name.isEmpty()) {
            tv3.setError("phone no cannot be empty");
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean gender() {
        String name = tv7.getEditableText().toString().trim();

        if (name.isEmpty()) {
            tv7.setText("NOT SPECIFIED");
            return true;
        }
        else
        {
            return true;
        }
    }


    public boolean address() {
        String add = tv4.getEditableText().toString().trim();

        if (add.isEmpty()) {
            tv4.setError("Address cannot be empty");
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean password() {
        String pass = tv5.getEditableText().toString().trim();
        String confirm = tv6.getEditableText().toString().trim();
        if (pass.isEmpty() || confirm.isEmpty() ) {
            return false;
        }
        else if (pass.matches(confirm.toString()))
        {
            return true;
        }
        else
        {
            tv5.setError("Password and confirm password are not same");
            tv6.setError("Password and confirm password are not same");
            return false;
        }
    }


}