package com.codembeded.jutttravelsco.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
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
import com.codembeded.jutttravelsco.models.VehiclesModels;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SpecialBooking extends AppCompatActivity {

    private static final String TAG = SpecialBooking.class.getSimpleName();
    private final ArrayList<VehiclesModels> vehicles_lists = new ArrayList<>();
    private final ArrayList<String> vehicles_names = new ArrayList<>();
    Button booking_btn;
    TextView datePicker_tv, timePicker_tv, select_type;
    TextInputLayout dept_et, arrival_et, suggestions_et, mileage_et;
    RadioGroup radioGroup;
    Spinner vehiclesSpecialBooking_sp;
    Boolean isInitialSinner;
    DatePickerDialog.OnDateSetListener dateSetListener;
    String date_str, vehicle_id_str, radio_btn_value_str, user_id;
    private int t1Hour, t1Minute;
    Toolbar toolbar;
    SharedPreferences sharedPreferences;
    ProgressBar progressBar;

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
//                vehicle_id_str = vehicles_lists.get(position).getVehicle_name();
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

                if (!validateDeparture() | !validateArrival() | !validateRadioGroup()) {
                    return;
                } else {
                    setSpecialBooking(dept_et.getEditText().getText().toString(), arrival_et.getEditText().getText().toString(), vehicle_id_str, datePicker_tv.getText().toString(),
                            timePicker_tv.getText().toString(), mileage_et.getEditText().getText().toString(), radio_btn_value_str,
                            suggestions_et.getEditText().getText().toString(), user_id);
                }
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
        select_type = findViewById(R.id.select_ac);
        progressBar = findViewById(R.id.progress_special_booking);
        mileage_et.getEditText().setText(" ");
        suggestions_et.getEditText().setText(" ");


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

    private boolean validateDeparture() {
        String name_et = dept_et.getEditText().getText().toString();
        if (name_et.isEmpty()) {
            dept_et.setError("Field cannot be empty");
            return false;
        } else {
            dept_et.setError(null);
            return true;
        }

    }

    private boolean validateArrival() {
        String name_et = arrival_et.getEditText().getText().toString();
        if (name_et.isEmpty()) {
            arrival_et.setError("Field cannot be empty");
            return false;
        } else {
            arrival_et.setError(null);
            return true;
        }

    }

    private boolean validateRadioGroup() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            select_type.setVisibility(View.VISIBLE);
            return false;
        } else if (radioGroup.getCheckedRadioButtonId() != -1) {
            select_type.setVisibility(View.GONE);
            return true;
        } else {
            select_type.setVisibility(View.GONE);
            return true;
        }
    }

    private void getVehicles() {
        String tag_str_req = "req_get_vehicles";
        progressBar.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.GET_VEHICLES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject jObj = new JSONObject(response);
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
                        Toast.makeText(getApplicationContext(), "Error is:" + error_msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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
        progressBar.setVisibility(View.VISIBLE);
        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.SPECIAL_BOOKING, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {

                        Toast.makeText(SpecialBooking.this, "Booked ", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

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