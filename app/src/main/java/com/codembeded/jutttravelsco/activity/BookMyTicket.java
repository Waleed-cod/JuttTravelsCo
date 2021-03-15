package com.codembeded.jutttravelsco.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
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
import com.codembeded.jutttravelsco.models.MyTicketsRoutesTiming;
import com.codembeded.jutttravelsco.models.RoutesModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BookMyTicket extends AppCompatActivity {
    TextView date, time;
    int t1Hour, t1Minute;
    Spinner route_sp;
    private final ArrayList<RoutesModels> routes_lists = new ArrayList<>();
    Button book_ticket_btn;
    private final ArrayList<String> names = new ArrayList<>();
    private final ArrayList<MyTicketsRoutesTiming> route_timing_list = new ArrayList<>();
    String date_str, routes_id_str;
    private Boolean isInitialSinner = false;
    private static final String TAG = BookMyTicket.class.getSimpleName();
    private RadioGroup radioGroup;
    private String radio_btn_value_str;
    DatePickerDialog.OnDateSetListener dateSetListener;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_my_ticket);

        toolbar = findViewById(R.id.toolbar_my_tickets);
        setSupportActionBar(toolbar);

        init();
        get_routes();


        //button
        book_ticket_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        // Departure spinner
        route_sp.setSelected(false);
        route_sp.setSelection(0,false);

        route_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                View v = route_sp.getSelectedView();
                ((TextView) v).setTextColor(Color.BLACK);

                if (isInitialSinner){
                    routes_id_str = String.valueOf(routes_lists.get(position).getId());
                }else {
                    isInitialSinner = true;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.book_my_ticket_ac_radio_btn:
                        radio_btn_value_str = "ac";
                        break;
                    case R.id.book_my_ticket_non_ac_radio_btn:
                        radio_btn_value_str = "non ac";
                        break;

                }
            }
        });

    }

    private void init() {

        date = findViewById(R.id.date_of_ticket);
        time = findViewById(R.id.time_of_ticket);
        book_ticket_btn = findViewById(R.id.btn_book_ticket);
        route_sp = findViewById(R.id.route_spinner);
        radioGroup = findViewById(R.id.book_my_ticket_radio_group);
    }

    private void get_routes() {
        String tag_str_req = "req_get_routes";


        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.GET_ROUTES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "1st Response:" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e(" second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        JSONArray array = jObj.getJSONArray("routes");
                        routes_lists.clear();
                        for (int i=0; i<array.length();i++){
                            JSONObject jsonObject = array.getJSONObject(i);
                            JSONArray timing_array = jsonObject.getJSONArray("route_timings");
                            for (int k = 0; k<timing_array.length(); k++){
                                JSONObject route_timing_obj = timing_array.getJSONObject(k);
                                route_timing_list.add(new MyTicketsRoutesTiming(route_timing_obj.getString("departure_time"),
                                        route_timing_obj.getString("arrival_time"),
                                        route_timing_obj.getString("entry_date")));
                                time.setText(route_timing_list.get(i).getDeparture_time());

                            }

                            routes_lists.add(new RoutesModels(jsonObject.getString("name"),
                                    jsonObject.getString("date")));
                            date.setText(routes_lists.get(i).getDate());

                        }
                        names.clear();
                        for (int j = 0; j<routes_lists.size(); j++){
                            names.add(routes_lists.get(j).getLocation_name());
                        }

                        ArrayAdapter<String> spinnerArrayAdapter_dept = new ArrayAdapter<String>(BookMyTicket.this, R.layout.support_simple_spinner_dropdown_item, names);
                        spinnerArrayAdapter_dept.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        route_sp.setAdapter(spinnerArrayAdapter_dept);


                         Toast.makeText(BookMyTicket.this, "Your Routes is now under consideration", Toast.LENGTH_SHORT).show();
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

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);

    }
//    //time
//    private void showTimePickerDialog() {
//        TimePickerDialog timePickerDialog = new TimePickerDialog(
//                BookMyTicket.this,
//                new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        t1Hour = hourOfDay;
//                        t1Minute = minute;
//
//                        Calendar calendar = Calendar.getInstance();
//
//                        calendar.set(0, 0, 0, t1Hour, t1Minute);
//
//                        time.setText(DateFormat.format("hh:mm aa", calendar));
//                    }
//                }, 12, 0, false
//        );
//        ///Display previous time
//        timePickerDialog.updateTime(t1Hour, t1Minute);
//        //show time
//        timePickerDialog.show();
//    }

}