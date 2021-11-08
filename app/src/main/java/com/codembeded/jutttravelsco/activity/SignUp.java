package com.codembeded.jutttravelsco.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
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

public class SignUp extends AppCompatActivity {

    private static final String TAG = SignUp.class.getSimpleName();
    TextInputLayout name_et, phone_et, email_et, cnic_et, password_et, confirm_password_et;
    Button btn_sign_up;
    String radio_btn_value_str;
    RadioGroup radioGroup;
    SharedPreferences sharedPreferences;
    Toolbar toolbar;
    TextView please_select_gender;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        toolbar = findViewById(R.id.sign_up_toolbar);
        setSupportActionBar(toolbar);

        init();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.male_radio_btn:
                        radio_btn_value_str = "male";
                        break;
                    case R.id.female_radio_btn:
                        radio_btn_value_str = "female";
                        break;
                }
            }
        });

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateName() | !validatePhone() | !validateCNIC() | !validateEmail() |
                        !validatePass() | !validateConfirmPassword() | !validateRadioGroup()) {
                    return;

                } else {
                    get_sign_up(name_et.getEditText().getText().toString(), "+92" + phone_et.getEditText().getText().toString(),
                            email_et.getEditText().getText().toString(), cnic_et.getEditText().getText().toString(),
                            password_et.getEditText().getText().toString(), radio_btn_value_str);
                }
            }
        });
    }

    private void init() {
        name_et = findViewById(R.id.user_name_sign_up_et);
        phone_et = findViewById(R.id.user_phone_sign_up_et);
        email_et = findViewById(R.id.user_email_sign_up_et);
        cnic_et = findViewById(R.id.user_cnic_sign_up_et);
        password_et = findViewById(R.id.user_password_sign_up_et);
        confirm_password_et = findViewById(R.id.user_password_sign_up_confirm_et);
        btn_sign_up = findViewById(R.id.user_sign_up_btn);
        radioGroup = findViewById(R.id.radio_group);
        please_select_gender = findViewById(R.id.please_select_gender);
        progressBar = findViewById(R.id.progress_sign_up);
    }

    private boolean validateName() {
        String name = name_et.getEditText().getText().toString();
        if (name.isEmpty()) {
            name_et.setError("Field cannot be empty");
            return false;
        } else {
            name_et.setError(null);
            return true;
        }

    }

    private boolean validatePhone() {
        String phone = phone_et.getEditText().getText().toString().trim();
        if (phone.isEmpty()) {
            phone_et.setError("Field cannot be empty");
            return false;
        } else if (phone.length() < 10) {
            phone_et.setError("Enter 10 digits 3xxxxxxxx");
            return false;
        } else {
            phone_et.setError(null);
            return true;
        }

    }

    private boolean validateCNIC() {
        String cnic = cnic_et.getEditText().getText().toString();
        if (cnic.isEmpty()) {
            cnic_et.setError("Field cannot be empty");
            return false;
        } else {
            cnic_et.setError(null);
            return true;
        }

    }

    private boolean validateEmail() {
        String email = email_et.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.isEmpty()) {
            email_et.setError("Field cannot be Empty");
            return false;
        } else if (!email.matches(emailPattern)) {
            email_et.setError("Invalid email address");
            return false;
        } else {
            email_et.setError(null);
            return true;
        }

    }

    private boolean validatePass() {
        String getPassword = password_et.getEditText().getText().toString();

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
            password_et.setError("Field cannot be Empty");
            return false;

        } else if (getPassword.length() < 8) {
            password_et.setError("Password must have 8 digits");
            return false;
        }
//        else if (!getPassword.matches(passwordVal)) {
//            new_password_forgot_et.setError("Pass is too weak");
//            return false;
//        }
        else {
            password_et.setError(null);
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        String getPassword = password_et.getEditText().getText().toString();
        String getConfirmPassword = confirm_password_et.getEditText().getText().toString();
        if (getConfirmPassword.isEmpty()) {
            confirm_password_et.setError("Field cannot be empty");
            return false;

        } else if (getConfirmPassword.length() < 8) {
            confirm_password_et.setError("Password must have 8 digits");
            return false;

        } else if (!getConfirmPassword.matches(getPassword)) {
            confirm_password_et.setError("Password not Matched");
            return false;

        } else {
            confirm_password_et.setError(null);
            return true;
        }
    }

    private boolean validateRadioGroup() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            please_select_gender.setVisibility(View.VISIBLE);
            return false;
        } else if (radioGroup.getCheckedRadioButtonId() != -1) {
            please_select_gender.setVisibility(View.GONE);
            return true;
        } else {
            please_select_gender.setVisibility(View.GONE);
            return true;
        }
    }

    private void get_sign_up(final String name_tv_str, final String phone_tv_str, final String email_tv_str, final String cnic_tv_str,
                             final String password_tv_str, final String radio_value_str) {
        String tag_str_req = "req_get_sign_up";

        progressBar.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.GET_SIGN_UP, new Response.Listener<String>() {
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
                        name_et.getEditText().setText("");
                        phone_et.getEditText().setText("");
                        email_et.getEditText().setText("");
                        cnic_et.getEditText().setText("");
                        password_et.getEditText().setText("");
                        confirm_password_et.getEditText().setText("");
                        Toast.makeText(SignUp.this, "Welcome!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUp.this, Home.class);
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
//                Toast.makeText(getApplicationContext(), "error of volley" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("name", name_tv_str);
                params.put("contact", phone_tv_str);
                params.put("email", email_tv_str);
                params.put("cnic", cnic_tv_str);
                params.put("password", password_tv_str);
                params.put("gender", radio_value_str);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
    }
}