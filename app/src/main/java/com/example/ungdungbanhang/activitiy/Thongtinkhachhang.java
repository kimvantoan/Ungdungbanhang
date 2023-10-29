package com.example.ungdungbanhang.activitiy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ungdungbanhang.R;
import com.example.ungdungbanhang.ultil.CheckConnection;
import com.example.ungdungbanhang.ultil.server;

import org.jetbrains.annotations.Contract;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Thongtinkhachhang extends AppCompatActivity {
    private EditText edttenkhachhang,edtemail,edtsdt;
    private Button btnxacnhan,btntrove;
    private String ten, sdt, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinkhachhang);
        edttenkhachhang = findViewById(R.id.edittexttenkhachhang);
        edtemail= findViewById(R.id.edittextemail);
        edtsdt=findViewById(R.id.edittextsodienthoai);
        btnxacnhan=findViewById(R.id.buttonxacnhan);
        btntrove= findViewById(R.id.buttontrove);
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnxacnhan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ten = edttenkhachhang.getText().toString();
                sdt = edtsdt.getText().toString();
                email = edtemail.getText().toString();
                addDataToDatabase(ten, sdt, email);
            }
        });
    }

    private void addDataToDatabase(String courseName, String courseDescription, String courseDuration) {
        String url = "https://webkimtoan.000webhostapp.com/wp-admin/thongtinkhachhanng.php";

        RequestQueue queue = Volley.newRequestQueue(Thongtinkhachhang.this);

        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(Thongtinkhachhang.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                edttenkhachhang.setText("");
                edtemail.setText("");
                edtsdt.setText("");
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(Thongtinkhachhang.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Contract(pure = true)
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("customName", ten);
                params.put("phoneNumber", sdt);
                params.put("email", email);
                return params;
            }
        };
        queue.add(request);
    }
}

