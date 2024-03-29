package com.codembeded.jutttravelsco.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.helperclass.AppConfig;
import com.codembeded.jutttravelsco.helperclass.AppController;
import com.codembeded.jutttravelsco.models.RoutesModels;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NewParcel extends AppCompatActivity {
    TextInputLayout receive_name_et, receive_contact_et, parcel_weight_et, parcel_quantity_et, info_et;
    Button confirm_btn;
    Toolbar toolbar;
    private static final String TAG = Complaints.class.getSimpleName();
    SharedPreferences preferences;
    String user_id, parcel_routes_sp_str;
    Spinner parcel_sp;
    ArrayList<RoutesModels> routes_list = new ArrayList<>();
    ArrayList<String> routes_names = new ArrayList<>();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_parcel);
        toolbar = findViewById(R.id.parcel_toolbar);
        setSupportActionBar(toolbar);

        get_routes();
        preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = preferences.getString("id", "");


        parcel_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                View v = parcel_sp.getSelectedView();
                ((TextView) v).setTextColor(Color.BLACK);

                parcel_routes_sp_str = String.valueOf(routes_list.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateName() | !validatePhone() | !validateWeight() | !validateQuantity() | !validateInfo()) {
                    return;
                } else {
                    Log.e("data", parcel_quantity_et.getEditText().getText().toString());
                    get_parcel(user_id, parcel_routes_sp_str, receive_name_et.getEditText().getText().toString(), receive_contact_et.getEditText().getText().toString(),
                            parcel_weight_et.getEditText().getText().toString(), parcel_quantity_et.getEditText().getText().toString(),
                            info_et.getEditText().getText().toString());
                }
            }

        });

        init();
    }

    private void get_routes() {
        String tag_str_req = "req_get_routes";

        progressBar.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.GET_ROUTES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        JSONArray array = jObj.getJSONArray("routes");
                        routes_list.clear();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObject = array.getJSONObject(i);
                            routes_list.add(new RoutesModels(jsonObject.getInt("id"),
                                    jsonObject.getString("name"),
                                    jsonObject.getInt("ac_rate"),
                                    jsonObject.getInt("non_ac_rate"),
                                    jsonObject.getString("date")));
                        }

                        routes_names.clear();
                        for (int j = 0; j < routes_list.size(); j++) {
                            routes_names.add(routes_list.get(j).getLocation_name());
                        }

                        ArrayAdapter<String> spinnerArrayAdapter_dept = new ArrayAdapter<String>(NewParcel.this, R.layout.support_simple_spinner_dropdown_item, routes_names);
                        spinnerArrayAdapter_dept.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        parcel_sp.setAdapter(spinnerArrayAdapter_dept);

                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), error_msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);

    }


    private void get_parcel(final String passenger_id, final String parcel_routes_sp_id_str, final String name_str,
                            final String contact_str, final String weight_str, final String quantity_str, final String info_str) {
        String tag_str_req = "req_get_parcels";
        progressBar.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.ADD_PARCEL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        receive_name_et.getEditText().setText("");
                        receive_contact_et.getEditText().setText("");
                        parcel_weight_et.getEditText().setText("");
                        parcel_quantity_et.getEditText().setText("");
                        info_et.getEditText().setText("");
                        Toast.makeText(NewParcel.this, "Your Parcel has been Booked", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(NewParcel.this, Home.class));

                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), error_msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("passenger_id", passenger_id);
                params.put("route_id", parcel_routes_sp_id_str);
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
        parcel_sp = findViewById(R.id.parcel_route_spinner);
        progressBar = findViewById(R.id.progress_new_parcel);
    }

    private boolean validateName() {
        String name_et = receive_name_et.getEditText().getText().toString();
        if (name_et.isEmpty()) {
            receive_name_et.setError("Field cannot be empty");
            return false;
        } else {
            receive_name_et.setError(null);
            return true;
        }

    }

    private boolean validatePhone() {
        String phone = receive_contact_et.getEditText().getText().toString();
        if (phone.isEmpty()) {
            receive_contact_et.setError("Field cannot be empty");
            return false;
        } else if (phone.length() < 13) {
            receive_contact_et.setError("Enter 13 digits +92xxxxxxxxx");
            return false;
        } else {
            receive_contact_et.setError(null);
            return true;
        }

    }

    private boolean validateWeight() {
        String name_et = parcel_weight_et.getEditText().getText().toString();
        if (name_et.isEmpty()) {
            parcel_weight_et.setError("Field cannot be empty");
            return false;
        } else {
            parcel_weight_et.setError(null);
            return true;
        }

    }

    private boolean validateQuantity() {
        String name_et = parcel_quantity_et.getEditText().getText().toString();
        if (name_et.isEmpty()) {
            parcel_quantity_et.setError("Field cannot be empty");
            return false;
        } else {
            parcel_quantity_et.setError(null);
            return true;
        }

    }

    private boolean validateInfo() {
        String name_et = info_et.getEditText().getText().toString();
        if (name_et.isEmpty()) {
            info_et.setError("Field cannot be empty");
            return false;
        } else {
            info_et.setError(null);
            return true;
        }

    }


}