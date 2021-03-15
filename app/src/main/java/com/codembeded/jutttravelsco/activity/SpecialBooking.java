package com.codembeded.jutttravelsco.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.helperclass.AppConfig;
import com.codembeded.jutttravelsco.helperclass.AppController;
import com.codembeded.jutttravelsco.models.RoutesModels;
import com.codembeded.jutttravelsco.models.VehiclesModels;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SpecialBooking extends AppCompatActivity {

    private static final String TAG = SpecialBooking.class.getSimpleName();
    private final ArrayList<VehiclesModels> vehicles_lists = new ArrayList<>();
    private final ArrayList<String> vehicles_names = new ArrayList<>();
    Button booking_btn;
    MaterialTextView datePicker_tv, timePicker_tv;
    TextInputLayout dept_et, arrival_et, suggestions_et;
    RadioGroup radioGroup;
    EditText mileage_et;
    Spinner vehiclesSpecialBooking_sp;
    Boolean isInitialSinner;
    DatePickerDialog.OnDateSetListener dateSetListener;
    String date_str, vehicle_id_str, radio_btn_value_str;
    private int t1Hour, t1Minute;
    Toolbar toolbar;
    SharedPreferences sharedPreferences;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_booking);

        toolbar = findViewById(R.id.special_booking_toolbar);
        setSupportActionBar(toolbar);

        init();
        getVehicles();
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = sharedPreferences.getString("id", "");

        //Vehicle names
        vehiclesSpecialBooking_sp.setSelected(false);
        vehiclesSpecialBooking_sp.setSelection(0, false);
        vehiclesSpecialBooking_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                View v = vehiclesSpecialBooking_sp.getSelectedView();

                ((TextView) v).setTextColor(Color.BLACK);

//                if (isInitialSinner){
                vehicle_id_str = String.valueOf(vehicles_lists.get(position).getId());
//                }else{
//                    isInitialSinner = true;
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //date
        datePicker_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(SpecialBooking.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                date_str = year + "/" + month + "/" + dayOfMonth;
                datePicker_tv.setText(date_str);
            }
        };

        timePicker_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.ac_special_booking_btn:
                        radio_btn_value_str = "1";
                        break;
                    case R.id.non_ac_special_booking_btn:
                        radio_btn_value_str = "0";
                        break;
                }
            }
        });

        booking_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setSpecialBooking(dept_et.getEditText().getText().toString(), arrival_et.getEditText().getText().toString(), vehicle_id_str, datePicker_tv.getText().toString(),
                        timePicker_tv.getText().toString(), mileage_et.getText().toString(), radio_btn_value_str,
                        suggestions_et.getEditText().getText().toString(), user_id);
            }
        });

    }

    private void init() {
        booking_btn = findViewById(R.id.special_booking_btn);
        datePicker_tv = findViewById(R.id.date_special_booking_tv);
        timePicker_tv = findViewById(R.id.time_special_booking_tv);
        suggestions_et = findViewById(R.id.suggestion_special_booking_et);
        radioGroup = findViewById(R.id.radio_group_special_booking);
        dept_et = findViewById(R.id.dept_special_booking_et);
        arrival_et = findViewById(R.id.arrival_special_booking_et);
        mileage_et = findViewById(R.id.extra_mileage);
        vehiclesSpecialBooking_sp = findViewById(R.id.select_vehicles_spinner);

    }

    //time
    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                SpecialBooking.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        t1Hour = hourOfDay;
                        t1Minute = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0, 0, 0, t1Hour, t1Minute);

                        timePicker_tv.setText(DateFormat.format("hh:mm aa", calendar));
                    }
                }, 12, 0, false
        );
        ///Display previous time
        timePickerDialog.updateTime(t1Hour, t1Minute);
        //show time
        timePickerDialog.show();
    }


    private void getVehicles() {
        String tag_str_req = "req_get_vehicles";
        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.GET_VEHICLES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "1st Response:" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e("second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        JSONArray array = jObj.getJSONArray("vehicles");
                        vehicles_lists.clear();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObject = array.getJSONObject(i);
                            vehicles_lists.add(new VehiclesModels(jsonObject.getInt("id"),
                                    jsonObject.getString("name")));
                        }
                        vehicles_names.clear();
                        for (int j = 0; j < vehicles_lists.size(); j++) {
                            vehicles_names.add(vehicles_lists.get(j).getVehicle_name());
                        }

                        ArrayAdapter<String> spinnerArrayAdapter_dept = new ArrayAdapter<String>(SpecialBooking.this, R.layout.support_simple_spinner_dropdown_item, vehicles_names);
                        spinnerArrayAdapter_dept.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        vehiclesSpecialBooking_sp.setAdapter(spinnerArrayAdapter_dept);

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
                        Log.e(TAG, "Volley Error:" + error.getMessage());
                        Toast.makeText(getApplicationContext(), "error of volley" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
    }

    private void setSpecialBooking(final String dept_et_str, final String arrival_et_str, final String vehicle_sp_str,
                                   final String date_value_str, final String time_value_str, final String mileage_str,
                                   final String ac_status_str, final String suggestion_str, final String passenger_id) {
        String tag_str_req = "req_special_booking";
        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.SPECIAL_BOOKING, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "1st Response:" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e("second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {

                        Intent intent = new Intent(SpecialBooking.this, Home.class);
                        startActivity(intent);
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
                        Log.e(TAG, "error of volley:" + error.getMessage());
                        Toast.makeText(getApplicationContext(), "error of volley" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
//                params.put("passenger_id", user_id);
                params.put("departure", dept_et_str);
                params.put("arrival", arrival_et_str);
                params.put("vehicle_id", vehicle_sp_str);
                params.put("booking_date", date_value_str);
                params.put("booking_time", time_value_str);
                params.put("extra_mileage", mileage_str);
                params.put("ac_status", ac_status_str);
                params.put("description", suggestion_str);
                params.put("passenger_id", passenger_id);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
    }


}