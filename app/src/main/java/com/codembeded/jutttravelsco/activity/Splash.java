package com.codembeded.jutttravelsco.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.codembeded.jutttravelsco.R;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id", "nothing");
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (id.matches("nothing")) {
//                    Intent mainIntent = new Intent(Splash.this, Login.class);
//                    Splash.this.startActivity(mainIntent);
//                    Splash.this.finish();
//                } else {
//                    Intent mainIntent = new Intent(Splash.this, Home.class);
//                    Splash.this.startActivity(mainIntent);
//                    Splash.this.finish();
//                }
//            }
//            },3000);

        Thread mThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2500);
                    if (id.matches("")) {
                        Intent mainIntent = new Intent(Splash.this, Login.class);
                        Splash.this.startActivity(mainIntent);
                        Splash.this.finish();

                    } else {
                        Intent mainIntent = new Intent(Splash.this, Home.class);
                        Splash.this.startActivity(mainIntent);
                        Splash.this.finish();
                    }
                } catch (InterruptedException e) {
//                    Toast.makeText(Splash.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        mThread.start();
    }



}
