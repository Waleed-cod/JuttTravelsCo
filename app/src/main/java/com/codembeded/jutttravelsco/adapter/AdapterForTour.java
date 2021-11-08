package com.codembeded.jutttravelsco.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.activity.Home;
import com.codembeded.jutttravelsco.helperclass.AppConfig;
import com.codembeded.jutttravelsco.helperclass.AppController;
import com.codembeded.jutttravelsco.models.TourModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterForTour extends RecyclerView.Adapter<AdapterForTour.ViewHolder> {

    ArrayList<TourModels> data;
    Context ctx;
    TextView total_amount_alert_dialog;
    EditText total_seats_alert_dialog;
    Button confirm_ticket_alert_dialog;
    int tour_id, price_per_seat, amount;

    SharedPreferences preferences;
    String user_id;

    public AdapterForTour(ArrayList<TourModels> data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_box, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tour_routes.setText(data.get(position).getRoutes());
        holder.tour_departure_date.setText(data.get(position).getDeparture_date());
        holder.tour_arrival_date.setText(data.get(position).getArrival_date());
        holder.tour_time.setText(data.get(position).getTime());
        holder.tour_person_rate.setText("Rs:  " + data.get(position).getRate_per_seat());
        holder.tour_desc.setText(data.get(position).getDesc());
        tour_id = data.get(position).getId();
        price_per_seat = data.get(position).getRate_per_seat();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tour_routes, tour_departure_date, tour_arrival_date, tour_time,
                tour_person_rate, tour_desc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tour_routes = itemView.findViewById(R.id.routes_tour_box);
            this.tour_departure_date = itemView.findViewById(R.id.departure_date_tour_box);
            this.tour_arrival_date = itemView.findViewById(R.id.arrival_date_tour_box);
            this.tour_time = itemView.findViewById(R.id.time_tour_box);
            this.tour_person_rate = itemView.findViewById(R.id.person_rate_tour_box);
            this.tour_desc = itemView.findViewById(R.id.desc_tour_box);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAlertDialog();
                }
            });

        }
    }

    private void showAlertDialog() {
        preferences = ctx.getSharedPreferences("MyPrefs",Context.MODE_PRIVATE);
        user_id = preferences.getString("id","");

        AlertDialog alertDialog = new AlertDialog.Builder(ctx, R.style.Theme_MaterialComponents_Dialog_Alert).create();
        alertDialog.setContentView(R.layout.alert_dialog_box);
        alertDialog.setTitle("Grab Your Seats");
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.alert_dialog_box, null);
        alertDialog.setView(view);

        total_seats_alert_dialog = view.findViewById(R.id.total_seats_tour_alert_dialog_box);
        total_amount_alert_dialog = view.findViewById(R.id.total_amount_alert_dialog_box);
        confirm_ticket_alert_dialog = view.findViewById(R.id.confirm_tickets_alert_dialog_box_btn);

        total_seats_alert_dialog.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                amount = price_per_seat * Integer.parseInt(total_seats_alert_dialog.getText().toString());
                String getText = total_seats_alert_dialog.getText().toString();
                int getTextToInt = Integer.parseInt(getText);
                amount = price_per_seat * getTextToInt;
                total_amount_alert_dialog.setText(String.valueOf(amount));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        confirm_ticket_alert_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addTourBooking(user_id, total_seats_alert_dialog.getText().toString(),
                        String.valueOf(tour_id),String.valueOf(amount));
                Intent intent = new Intent(ctx, Home.class);
                ctx.startActivity(intent);
            }
        });
        alertDialog.show();


    }

    private void addTourBooking(final String passenger_id, final String total_seats_str, final String tour_id_str, final String total_Amount_str) {
        String tag_str_req = "req_add_bookings";


        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.ADD_TOUR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("1st Response:" , response);
                        try {
                            JSONObject jObj = new JSONObject(response);
                            Log.e("second response:", response);
                            boolean error = jObj.getBoolean("error");
                            //check for error node in json
                            if (!error) {

                                Toast.makeText(ctx, "Your Ticket has been Booked", Toast.LENGTH_SHORT).show();
                            } else {
                                String error_msg = jObj.getString("error_msg");
                                Toast.makeText(ctx, "" + error_msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Log.e("Volley Error: " , error.getMessage());
                        Toast.makeText(ctx, "error of volley" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("passenger_id", passenger_id);
                params.put("total_seats", total_seats_str);
                params.put("amount", total_Amount_str);
                params.put("tour_id", tour_id_str);


                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
    }


}
