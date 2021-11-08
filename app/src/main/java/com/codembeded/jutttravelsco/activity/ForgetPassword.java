package com.codembeded.jutttravelsco.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.FloatProperty;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
    EditText new_password_forgot_et, confirm_new_password_forgot_et;
    Button reset_password_btn;
    Toolbar toolbar;
    String user_phone_str;
    int user_id;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        toolbar = findViewById(R.id.forget_password_toolbar);
        setSupportActionBar(toolbar);
        init();

        reset_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatePass() | !validateConfirmPassword()) {
                    return;
                } else {
                    resetPassword(String.valueOf(user_id), new_password_forgot_et.getText().toString());
                }
            }
        });
    }

    private void resetPassword(final String user_id_str, final String password_tv_str) {
        String tag_str_req = "req_get_forgot_password";
        progressBar.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.GET_CHANGE_PASSWORD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {

                        new_password_forgot_et.setText("");
                        confirm_new_password_forgot_et.setText("");

                        Toast.makeText(ForgetPassword.this, "Your Password has been changed", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(ForgetPassword.this, Login.class);
                        startActivity(intent);
                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), error_msg, Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("user_id", user_id_str);
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
        progressBar = findViewById(R.id.progress_forgot_password);

        user_id = getIntent().getIntExtra("User_Id",0);
//        user_phone_str = getIntent().getStringExtra("User_Phone");
    }


    private boolean validatePass() {
        String getPassword = new_password_forgot_et.getText().toString();


//        String passwordVal = "^" +
//                //"(?=.*[0-9])" +         //at least 1 digit
//                //"(?=.*[a-z])" +         //at least 1 lower case letter
//                //"(?=.*[A-Z])" +         //at least 1 upper case letter
//                "(?=.*[a-zA-Z])" +      //any letter
//                "(?=.*[@#$%^&+=])" +    //at least 1 special character
//                "(?=\\S+$)" +           //no white spaces
//                ".{4,}" +               //at least 4 characters
//                "$";

        if (getPassword.isEmpty()) {
            new_password_forgot_et.setError("Field cannot be Empty");
            return false;

        } else if (getPassword.length() < 8) {
            new_password_forgot_et.setError("Password must have 8 digits");
            return false;

        }
//        else if (!getPassword.matches(passwordVal)) {
//            new_password_forgot_et.setError("Pass is too weak");
//            return false;
//        }
        else {
            new_password_forgot_et.setError(null);
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        String getPassword = new_password_forgot_et.getText().toString();
        String getConfirmPassword = confirm_new_password_forgot_et.getText().toString();
        if (getConfirmPassword.isEmpty()) {
            confirm_new_password_forgot_et.setError("Field cannot be empty");
            return false;

        } else if (getConfirmPassword.length() < 8) {
            confirm_new_password_forgot_et.setError("Password must have 8 digits");
            return false;

        } else if (!getConfirmPassword.matches(getPassword)) {
            confirm_new_password_forgot_et.setError("Password not Matched");
            return false;

        } else
            confirm_new_password_forgot_et.setError(null);
        return true;
    }


}