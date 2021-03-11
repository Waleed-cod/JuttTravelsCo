package com.codembeded.jutttravelsco.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.adapter.AdapterForTour;
import com.codembeded.jutttravelsco.helperclass.AppConfig;
import com.codembeded.jutttravelsco.helperclass.AppController;
import com.codembeded.jutttravelsco.models.TourModels;
import com.codembeded.jutttravelsco.models.VehiclesModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OurTours extends AppCompatActivity {

    RecyclerView tour_rv;
    AdapterForTour adapterForTour;
    final static String TAG = OurTours.class.getSimpleName();
    ArrayList<TourModels> tours_lists = new ArrayList<>();
    TextView empty_card_tv_tour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_tours);

        tour_rv = findViewById(R.id.tour_rv);
        empty_card_tv_tour = findViewById(R.id.empty_card_tv_tour);

        getToursDetails();

    }

    private void getToursDetails() {
        String tag_str_req = "req_get_tours";
        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.GET_TOURS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "1st Response:" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e("second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        JSONArray array = jObj.getJSONArray("tours");
                        if (array.length() == 0) {
                            empty_card_tv_tour.setVisibility(View.VISIBLE);
                        } else
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject jsonObject = array.getJSONObject(i);
                                tours_lists.add(new TourModels(jsonObject.getInt("id"),
                                        jsonObject.getString(""),
                                        jsonObject.getString(""),
                                        jsonObject.getString(""),
                                        jsonObject.getString(""),
                                        jsonObject.getString(""),
                                        jsonObject.getString(""),
                                        jsonObject.getString(""),
                                        jsonObject.getString("")));
                            }

                        adapterForTour = new AdapterForTour(tours_lists, OurTours.this);
                        tour_rv.setLayoutManager(new LinearLayoutManager(OurTours.this, LinearLayoutManager.VERTICAL, false));
                        tour_rv.setAdapter(adapterForTour);

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

}