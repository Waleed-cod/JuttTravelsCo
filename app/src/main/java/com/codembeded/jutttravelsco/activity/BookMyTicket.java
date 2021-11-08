package com.codembeded.jutttravelsco.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
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

public class BookMyTicket extends AppCompatActivity {
    TextView date, select_ac;
    Spinner route_sp, time_tickets_sp;
    TextInputLayout total_tickets, women_seats;
    private final ArrayList<RoutesModels> routes_lists = new ArrayList<>();
    Button book_ticket_btn;
    private final ArrayList<String> names = new ArrayList<>();
    private final ArrayList<String> time = new ArrayList<>();
    private final ArrayList<MyTicketsRoutesTiming> route_timing_list = new ArrayList<>();
    String routes_id_str, routes_time_id_str, end_date_str;
    private final Boolean isInitialSinner = false;
    private static final String TAG = BookMyTicket.class.getSimpleName();
    private RadioGroup radioGroup;
    String user_id, total_amount;
    int ac_rate, non_ac_rate, seats;
    Toolbar toolbar;
    SharedPreferences preferences;
    Boolean radio_btn_value = false;
    ProgressBar progressBar;
    DatePickerDialog.OnDateSetListener mDateSetListenerMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_my_ticket);
        preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = preferences.getString("id", "");
        toolbar = findViewById(R.id.toolbar_my_tickets);
        setSupportActionBar(toolbar);

        init();
        get_routes();
        route_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                View v = route_sp.getSelectedView();
                ((TextView) v).setTextColor(Color.BLACK);

                routes_id_str = String.valueOf(routes_lists.get(position).getId());
                get_routes_timing(routes_id_str);
                ac_rate = routes_lists.get(position).getAc_rate();
                non_ac_rate = routes_lists.get(position).getNon_ac_rate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                routes_id_str = String.valueOf(parent.getSelectedItemId());
                get_routes_timing(routes_id_str);
            }
        });
        time_tickets_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                View v = time_tickets_sp.getSelectedView();
                ((TextView) v).setTextColor(Color.BLACK);
                routes_time_id_str = String.valueOf(route_timing_list.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(BookMyTicket.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListenerMax,
                        year, month, day);
                long now = System.currentTimeMillis() - 1000;
                dialog.getDatePicker().setMaxDate(now + (1000 * 60 * 60 * 24 * 3));
                dialog.getDatePicker().setMinDate(now);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mDateSetListenerMax = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                end_date_str = year + "/" + month + "/" + dayOfMonth;
                date.setText(end_date_str);

            }
        };

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.book_my_ticket_ac_radio_btn:
                        radio_btn_value = true;
                        break;
                    case R.id.book_my_ticket_non_ac_radio_btn:
                        radio_btn_value = false;
                        break;
                }
            }
        });

        book_ticket_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateTickets()) {
                    return;
                } else if (!validateWSeats()) {
                    return;
                } else if (!validateRadioGroup()) {
                    return;
                }
                getAmountOfAc();
                addBooking(user_id, routes_id_str, date.getText().toString(), routes_time_id_str, total_tickets.getEditText().getText().toString(),
                        women_seats.getEditText().getText().toString(), radio_btn_value.toString(), total_amount);

            }
        });

    }

    private void getAmountOfAc() {
        if (radio_btn_value) {

            seats = Integer.parseInt(total_tickets.getEditText().getText().toString().trim()) * ac_rate;

        } else {
            seats = Integer.parseInt(total_tickets.getEditText().getText().toString().trim()) * non_ac_rate;

        }
        total_amount = String.valueOf(seats);
    }

    private void init() {

        date = findViewById(R.id.date_of_ticket);
        time_tickets_sp = findViewById(R.id.time_spinner_tickets);
        book_ticket_btn = findViewById(R.id.btn_book_ticket);
        route_sp = findViewById(R.id.route_spinner);
        radioGroup = findViewById(R.id.book_my_ticket_radio_group);
        women_seats = findViewById(R.id.women_seats_tickets);
        total_tickets = findViewById(R.id.total_ticket_et);
        select_ac = findViewById(R.id.select_ac);
        progressBar = findViewById(R.id.progress_book_my_ticket);
    }


    private boolean validateTickets() {
        String tickets = total_tickets.getEditText().getText().toString().trim();
        if (tickets.isEmpty()) {
            total_tickets.setError("Field cannot be empty");
            return false;
        } else if (tickets.matches(String.valueOf(Integer.parseInt("0")))) {
            total_tickets.setError("Enter the valid Number");
            return false;
        } else if (Integer.parseInt(tickets) > 18) {
            total_tickets.setError("Should be less then or equal to 18");
            return false;
        } else {
            total_tickets.setError(null);
            return true;
        }

    }

    private boolean validateWSeats() {

        String seats = women_seats.getEditText().getText().toString().trim();
        String tickets = total_tickets.getEditText().getText().toString().trim();
        if (seats.isEmpty()) {
            women_seats.setError("Field cannot be empty");
            return false;
        } else if (Integer.parseInt(seats) > Integer.parseInt(tickets)) {
            women_seats.setError("Ladies seats should be less then or equal to total Tickets");
            return false;
        } else if (Integer.parseInt(seats) > 18) {
            women_seats.setError("Should be less then or equal to 18");
            return false;
        } else {
            women_seats.setError(null);
            return true;
        }

    }

    private boolean validateRadioGroup() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            select_ac.setVisibility(View.VISIBLE);
            return false;
        } else if (radioGroup.getCheckedRadioButtonId() != -1) {
            select_ac.setVisibility(View.GONE);
            return true;
        } else {
            select_ac.setVisibility(View.GONE);
            return true;
        }
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
                        routes_lists.clear();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObject = array.getJSONObject(i);
                            routes_lists.add(new RoutesModels(jsonObject.getInt("id"),
                                    jsonObject.getString("name"),
                                    jsonObject.getInt("ac_rate"),
                                    jsonObject.getInt("non_ac_rate"),
                                    jsonObject.getString("date")));

                        }
                        names.clear();
                        for (int j = 0; j < routes_lists.size(); j++) {
                            names.add(routes_lists.get(j).getLocation_name());
                        }

                        ArrayAdapter<String> spinnerArrayAdapter_dept = new ArrayAdapter<String>(BookMyTicket.this, R.layout.support_simple_spinner_dropdown_item, names);
                        spinnerArrayAdapter_dept.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        route_sp.setAdapter(spinnerArrayAdapter_dept);

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

    private void get_routes_timing(final String route_id_str) {
        String tag_str_req = "req_get_routes_timing";
        progressBar.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.GET_ROUTES_TIMING + "?route_id=" + route_id_str , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        JSONArray array = jObj.getJSONArray("route_timings");
                        route_timing_list.clear();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObject = array.getJSONObject(i);
                            route_timing_list.add(new MyTicketsRoutesTiming(jsonObject.getInt("id"),
                                    jsonObject.getString("departure_time"),
                                    jsonObject.getString("arrival_time"),
                                    jsonObject.getString("entry_date"),
                                    jsonObject.getInt("status")));
                        }
                        time.clear();
                        for (int l = 0; l < route_timing_list.size(); l++) {
                            time.add(route_timing_list.get(l).getDeparture_time());
                        }
                        ArrayAdapter<String> spinnerArrayAdapter_time = new ArrayAdapter<String>(BookMyTicket.this, R.layout.support_simple_spinner_dropdown_item, time);
                        spinnerArrayAdapter_time.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        time_tickets_sp.setAdapter(spinnerArrayAdapter_time);
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
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);

    }


    private void addBooking(final String passenger_id, final String routes_id_str, final String date_str, final String routes_time_id_str,
                            final String total_tickets_str, final String women_seats_str, final String ac_status_str,
                            final String total_amount_str) {
        String tag_str_req = "req_add_bookings";

        progressBar.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.ADD_BOOKING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");
                            //check for error node in json
                            if (!error) {
                                total_tickets.getEditText().setText("");
                                women_seats.getEditText().setText("");

                                Toast.makeText(BookMyTicket.this, "Your Ticket has Booked", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(BookMyTicket.this, Home.class));
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
                        Toast.makeText(getApplicationContext(), "error of volley" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("passenger_id", passenger_id);
                params.put("total_seats", total_tickets_str);
                params.put("ladies_seats", women_seats_str);
                params.put("booking_date", date_str);
                params.put("ac_status", ac_status_str);
                params.put("route_id", routes_id_str);
                params.put("booking_time", routes_time_id_str);
                params.put("total_amount", total_amount_str);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
    }

}