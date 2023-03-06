package com.example.ifoodmate;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class add_items_sp extends AppCompatActivity {
    ImageView img;
    TextView tv1;
    Button btn;


    private Bitmap bitmap;


    private static final String url = "http://192.168.174.249/ifoodmate/insert_category.php?add=333";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items_sp);
        tv1 = findViewById(R.id.catname);
        img = findViewById(R.id.category_img);
        btn = findViewById(R.id.done);

        ActivityResultLauncher<Intent> activityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Uri uri = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        img.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        );
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ByteArrayOutputStream byteArrayOutputStream;
                byteArrayOutputStream = new ByteArrayOutputStream();
                if (bitmap != null)
                {
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                    byte[] bytes = byteArrayOutputStream.toByteArray();
                    final String base64img = Base64.encodeToString(bytes,Base64.DEFAULT);
                    insertcat(tv1,base64img);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No Image Selected", Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    private void insertcat(TextView tv1, String base64img) {
        final String catname = tv1.getText().toString();
        final String img = base64img;


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success"))
                {
                    Toast.makeText(getApplicationContext(), "Category Added", Toast.LENGTH_LONG).show();
                }
                else
                {
                    System.out.println("categoryresponse" + response);
                    Toast.makeText(getApplicationContext(), "Failed to add Category", Toast.LENGTH_LONG).show();
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
                para.put("CATEGORY_NAME",catname);
                para.put("CAT_IMG",img);

                return para;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }


}