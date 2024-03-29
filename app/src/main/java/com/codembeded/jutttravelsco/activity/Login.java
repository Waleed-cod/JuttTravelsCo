package com.codembeded.jutttravelsco.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.helperclass.AppConfig;
import com.codembeded.jutttravelsco.helperclass.AppController;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    TextInputEditText phone_et, password_et;
    TextView sign_up_tv, forgot_password_tv;
    Button login_btn;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;

    private static final String TAG = Login.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone_et = findViewById(R.id.user_phone_login_et);
        password_et = findViewById(R.id.user_password_login_et);
        sign_up_tv = findViewById(R.id.signUp_login_tv);
        forgot_password_tv = findViewById(R.id.forgot_password_login_tv);
        login_btn = findViewById(R.id.logIn_btn);
        progressBar = findViewById(R.id.progress_login);

        sign_up_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

        forgot_password_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, ConfirmUserPhone.class);
                startActivity(i);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateName() | !validatePassword()) {
                    return;
                } else {
                    getLogin("+92" + phone_et.getText().toString(), password_et.getText().toString());
                }

            }
        });
    }

    private boolean validateName() {
        String name_et = phone_et.getText().toString().trim();
        if (name_et.isEmpty()) {
            phone_et.setError("Field cannot be empty");
            return false;
        } else if (name_et.length() < 10) {
            phone_et.setError("Enter 10 digits 3xxxxxxxxx");
            return false;
        } else {
            phone_et.setError(null);
            return true;
        }

    }

    private boolean validatePassword() {
        String pass = password_et.getText().toString();
        if (pass.isEmpty()) {
            password_et.setError("Password field cannot be empty");
            return false;
        } else {
            password_et.setError(null);
            return true;
        }
    }

    private void getLogin(final String phone_str, final String password_str) {
        String tag_str_req = "req_get_login";
        progressBar.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.GET_LOG_IN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("id", jObj.getString("id"));
                        editor.apply();
                        phone_et.setText("");
                        password_et.setText("");
                        Intent intent = new Intent(Login.this, Home.class);
                        startActivity(intent);

                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), "Error is " + error_msg, Toast.LENGTH_SHORT).show();
                        phone_et.setError("Enter Valid Phone Number or Password");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Volley Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("phone_number", phone_str);
                params.put("password", password_str);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
    }

}