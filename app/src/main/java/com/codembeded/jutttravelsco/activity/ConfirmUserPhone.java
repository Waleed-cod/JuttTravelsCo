package com.codembeded.jutttravelsco.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ConfirmUserPhone extends AppCompatActivity {

    Button update;
    TextInputLayout confirm_user_phone_et;
    ProgressBar progressBar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_user_phone);

        init();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateName()){
                    return;
            }else {
                    resetPassword("+92" + confirm_user_phone_et.getEditText().getText().toString());

                }
            }
        });


    }

    private void init() {
        toolbar = findViewById(R.id.confirm_user_toolbar);
        setSupportActionBar(toolbar);
        update = findViewById(R.id.confirm_phone_num_btn);
        confirm_user_phone_et = findViewById(R.id.confirm_user_phone_et);
        progressBar = findViewById(R.id.progress_confirm_user);

    }

    private void resetPassword(final String user_phone_str) {
        String tag_str_req = "req_get_forgot_password";
        progressBar.setVisibility(View.VISIBLE);


        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.GET_USER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                Log.e("1st response", response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        confirm_user_phone_et.getEditText().setText("");
                        int jsonOb_id = jObj.getInt("id");
                        String jsonObj_phone = jObj.getString("phone");


                        Intent intent = new Intent(ConfirmUserPhone.this, ForgetPassword.class);
                        intent.putExtra("User_Id", jsonOb_id);
                        intent.putExtra("User_Phone", jsonObj_phone);
                        startActivity(intent);
                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), error_msg, Toast.LENGTH_SHORT).show();
                        confirm_user_phone_et.setError("Enter Valid Phone Number");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("phone_number", user_phone_str);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
    }

    private boolean validateName(){
        String name_et = confirm_user_phone_et.getEditText().getText().toString().trim();
        if (name_et.isEmpty()) {
            confirm_user_phone_et.setError("Enter you Phone Number");
            return false;
        }else if (name_et.length() < 10){
            confirm_user_phone_et.setError("Enter 10 digits 3xxxxxxxxx");
            return false;
        }else {
            confirm_user_phone_et.setError(null);
            return true;
        }

    }

}