package com.codembeded.jutttravelsco.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class NewParcel extends AppCompatActivity {
    TextInputLayout receive_name_et, receive_contact_et, parcel_weight_et, parcel_quantity_et, info_et;
    Button confirm_btn;
    Toolbar toolbar;
    private static final String TAG = Complaints.class.getSimpleName();
    SharedPreferences preferences;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_parcel);
        toolbar = findViewById(R.id.parcel_toolbar);
        setSupportActionBar(toolbar);
        init();

        preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = preferences.getString("id","");

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_parcel(user_id,receive_name_et.getEditText().getText().toString(), receive_contact_et.getEditText().getText().toString(), parcel_weight_et.getEditText().getText().toString(),
                        parcel_quantity_et.getEditText().getText().toString(), info_et.getEditText().getText().toString());
            }

        });
    }

    private void get_parcel(final String passenger_id, final String name_str, final String contact_str, final String weight_str, final String quantity_str, final String info_str) {
        String tag_str_req = "req_get_parcels";


        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.ADD_PARCEL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "1st Response:" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e("second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        Toast.makeText(NewParcel.this, "Your Parcel has been Booked", Toast.LENGTH_SHORT).show();
                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), "" + error_msg, Toast.LENGTH_SHORT).show();
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
                params.put("receiver_name", name_str);
                params.put("receiver_contact", contact_str);
                params.put("weight", weight_str);
                params.put("quantity", quantity_str);
                params.put("description", info_str);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
    }

    private void init() {
        receive_name_et = findViewById(R.id.receiver_name_parcel_et);
        receive_contact_et = findViewById(R.id.receiver_contact_parcel_et);
        parcel_weight_et = findViewById(R.id.weight_parcel_et);
        parcel_quantity_et = findViewById(R.id.quantity_parcel_et);
        info_et = findViewById(R.id.desc_parcel_et);
        confirm_btn = findViewById(R.id.btn_book_parcel);
    }

}