package com.codembeded.jutttravelsco.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class Complaints extends AppCompatActivity {
    TextInputLayout suggestion_et;
    RadioGroup radioGroup;
    String radio_btn_value_str, user_id;
    private static final String TAG = Complaints.class.getSimpleName();
    Button btn_enter;
    Toolbar toolbar;
    TextView select_one;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);

        init();
        setSupportActionBar(toolbar);
        preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = preferences.getString("id","");



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.bus_radio_btn:
                        radio_btn_value_str = "bus";
                        break;
                    case R.id.driver_radio_btn:
                        radio_btn_value_str = "driver";
                        break;
                    case R.id.services_radio_btn:
                        radio_btn_value_str = "services";
                        break;

                }

            }
        });
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateName() | !validateRadioGroup()){
                    return;
                }
                get_complains(user_id,suggestion_et.getEditText().toString(), radio_btn_value_str);
            }
        });

    }

    private void init() {
        suggestion_et = findViewById(R.id.complain_suggestion);
        radioGroup = findViewById(R.id.complain_radio_group);
        btn_enter = findViewById(R.id.btn_complain_enter);
        toolbar = findViewById(R.id.toolbar_complaints);
        select_one = findViewById(R.id.select_one);
    }

    private boolean validateName(){
        String name_et = suggestion_et.getEditText().getText().toString();
        if (name_et.isEmpty()) {
            suggestion_et.setError("Field cannot be empty");
            return false;
        }else {
            suggestion_et.setError(null);
            return true;
        }

    }

    private boolean validateRadioGroup() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            select_one.setVisibility(View.VISIBLE);
            return false;
        } else if (radioGroup.getCheckedRadioButtonId() != -1) {
            select_one.setVisibility(View.GONE);
            return true;
        } else {
            select_one.setVisibility(View.GONE);
            return true;
        }
    }


    private void get_complains(final String passenger_id, final String suggestion_str, final String radio_value_str) {
        String tag_str_req = "req_get_complains";


        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.GET_COMPLAINS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "1st Response:" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e("second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        suggestion_et.getEditText().setText("");
                        Toast.makeText(Complaints.this, "Your complaint is now under consideration", Toast.LENGTH_SHORT).show();
                        startActivity( new Intent( Complaints.this,Home.class));
                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), "Error is " + error_msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Volley Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(), "error of volley" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("passenger_id", passenger_id);
                params.put("type", radio_value_str);
                params.put("description", suggestion_str);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
    }

}