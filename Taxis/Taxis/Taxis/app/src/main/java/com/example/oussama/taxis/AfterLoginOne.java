package com.example.oussama.taxis;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AfterLoginOne extends AppCompatActivity {

    private static int SPLAH_TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login_one);
        ImageView image = (ImageView)findViewById(R.id.imageView3);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(AfterLoginOne.this, AfterLoginTwo.class);
                startActivity(i);
                finish();
            }
        }, SPLAH_TIME_OUT);
    }
}

