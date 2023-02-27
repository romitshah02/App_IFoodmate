package com.example.ifoodmate;

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

public class loginmain extends AppCompatActivity {
    Button btn;
    TextView tv,sk,sg,fg,sp;
    EditText ps;

    private static final String url = "";
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
                else if (tv.getText().toString().equals("service") && ps.getText().toString().equals("123"))
                {
                    Toast.makeText(loginmain.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getApplicationContext(),sp_profile.class);
                    editor.putBoolean("flag",true);
                    editor.apply();
                    startActivity(intent);
                }
                else if (tv.getText().toString().equals("hello") && ps.getText().toString().equals("123"))
                {
                    Toast.makeText(loginmain.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getApplicationContext(),homepage.class);
                    editor.putBoolean("flag",true);
                    editor.apply();
                    startActivity(intent);
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
}