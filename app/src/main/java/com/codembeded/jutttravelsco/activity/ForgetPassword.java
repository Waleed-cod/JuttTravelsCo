package com.codembeded.jutttravelsco.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.FloatProperty;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.helperclass.AppConfig;
import com.codembeded.jutttravelsco.helperclass.AppController;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgetPassword extends AppCompatActivity {

    final static String TAG = ForgetPassword.class.getSimpleName();
    TextInputLayout new_password_forgot_et, confirm_new_password_forgot_et;
    Button reset_password_btn;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        toolbar = findViewById(R.id.forget_password_toolbar);
        setSupportActionBar(toolbar);

        init();
        resetPassword(new_password_forgot_et.getEditText().getText().toString());
    }

    private void resetPassword(final String password_tv_str) {
        String tag_str_req = "req_get_forgot_password";


        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.GET_CHANGE_PASSWORD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "!st Response:" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e(" second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {

                        Intent intent = new Intent(ForgetPassword.this, Home.class);
                        startActivity(intent);
                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), "Error is " + error_msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Volley Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "error of volley" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("password", password_tv_str);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
    }

    private void init() {

        new_password_forgot_et = findViewById(R.id.new_password_forgot_et);
        confirm_new_password_forgot_et = findViewById(R.id.confirm_new_password_forgot_et);
        reset_password_btn = findViewById(R.id.reset_password_btn);
    }
}