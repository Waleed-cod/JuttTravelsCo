package com.codembeded.jutttravelsco.activity;

import android.content.Intent;
import android.os.Bundle;

import com.codembeded.jutttravelsco.R;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread myThread =  new Thread(){

            @Override
            public void run() {

                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                super.run();
            }
        };
        myThread.start();
    }
}