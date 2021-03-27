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
    TextInputLayout user_phone_et, new_password_forgot_et, confirm_new_password_forgot_et;
    Button reset_password_btn;
    Toolbar toolbar;
    SharedPreferences preferences;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = preferences.getString("id", "");
        toolbar = findViewById(R.id.forget_password_toolbar);
        setSupportActionBar(toolbar);

        init();
        reset_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatePass() | validateConfirmPassword()) {
                    return;
                } else {
                    resetPassword(user_id, new_password_forgot_et.getEditText().getText().toString());
                    Toast.makeText(ForgetPassword.this, "Password Changed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resetPassword(final String passenger_id, final String password_tv_str) {
        String tag_str_req = "req_get_forgot_password";


        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.GET_CHANGE_PASSWORD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "1st Response:" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e("second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {

                        new_password_forgot_et.getEditText().setText("");
                        confirm_new_password_forgot_et.getEditText().setText("");
                        Toast.makeText(ForgetPassword.this, "Your Password has been changed", Toast.LENGTH_SHORT).show();

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

                params.put("passenger", passenger_id);
                params.put("password", password_tv_str);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
    }

    private void init() {
        user_phone_et = findViewById(R.id.user_phone_et);
        new_password_forgot_et = findViewById(R.id.new_password_forgot_et);
        confirm_new_password_forgot_et = findViewById(R.id.confirm_new_password_forgot_et);
        reset_password_btn = findViewById(R.id.reset_password_btn);
    }


    private boolean validatePass() {
        String getPassword = new_password_forgot_et.getEditText().getText().toString();


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
        String getPassword = new_password_forgot_et.getEditText().getText().toString();
        String getConfirmPassword = confirm_new_password_forgot_et.getEditText().getText().toString();
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