package com.codembeded.jutttravelsco.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
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
    String radio_btn_value_str;
    private static final String TAG = Complaints.class.getSimpleName();
    Button btn_enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        suggestion_et = findViewById(R.id.complain_suggestion);
        radioGroup = findViewById(R.id.complain_radio_group);
        btn_enter = findViewById(R.id.btn_complain_enter);

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
                get_complains(suggestion_et.getEditText().toString(), radio_btn_value_str);
            }
        });

    }

    private void get_complains(final String suggestion_str, final String radio_value_str) {
        String tag_str_req = "req_get_complains";


        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.GET_COMPLAINS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "!st Response:" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e(" second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        Toast.makeText(Complaints.this, "Your complaint is now under consideration", Toast.LENGTH_SHORT).show();
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

                params.put("type", radio_value_str);
                params.put("description", suggestion_str);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
    }

}