package com.example.ifoodmate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
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

public class signupsp extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8;
 Button btn;
    private static final String url = "http://192.168.129.166/ifoodmate/insert_service.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupsp);
        btn = findViewById(R.id.signup_sp);
        tv1 = findViewById(R.id.Email);
        tv2 = findViewById(R.id.new_username);
        tv3 = findViewById(R.id.Name);
        tv4 = findViewById(R.id.address);
        tv5 = findViewById(R.id.password);
        tv6 = findViewById(R.id.confirm_password);
        tv7 = findViewById(R.id.GST);
        tv8 = findViewById(R.id.phone);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (confirmInput(view) == true)
                {
                    insertdata(tv1,tv3,tv8,tv4,tv5,tv7);
                    Intent login = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(login);
                }
                else
                {
                    Toast.makeText(signupsp.this, "Please Check All the details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void insertdata(TextView tv1,TextView tv2,TextView tv3,TextView tv4,TextView tv5,TextView tv6)
    {
        final String email = tv1.getText().toString().trim();
        final String name = tv2.getText().toString().trim();
        final String phone_no = tv3.getText().toString().trim();
        final String address = tv4.getText().toString().trim();
        final String password = tv5.getText().toString().trim();
        final String gst = tv6.getText().toString().trim();

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
                para.put("gst",gst);

                return para;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
    public boolean confirmInput(View view) {
        if (!validatemail() | !validateUsername() | !name() | !address() | !password() | !num() | !GST()) {
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
        }  else
        {
            return true;
        }
    }

    public boolean name() {
        String name = tv3.getEditableText().toString().trim();

        if (name.isEmpty()) {
            tv3.setError("Name cannot be empty");
            return false;
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
    public boolean num()
    {
        String no= tv8.getEditableText().toString().trim();

        if (no.isEmpty()) {
            tv8.setError("Phone no cannot be empty");
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean GST() {
        String gst= tv7.getEditableText().toString().trim();

        if (gst.isEmpty()) {
            tv7.setError("GST cannot be empty");
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