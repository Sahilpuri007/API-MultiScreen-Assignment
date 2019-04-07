package com.android.apimultiscreen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.Toast;

import com.android.apimultiscreen.R;
import com.android.apimultiscreen.util.InternetCheckUtil;

public class SplashScreenActivity extends AppCompatActivity {


    private static final int SPLASH_SCREEN_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        checkInternet();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        checkInternet();
        return super.onTouchEvent(event);

    }


    /**
     * checkInternet method to check for internet connection
     */
    private void checkInternet() {

        boolean checkConnection = new InternetCheckUtil().checkConnection(this);
        if (checkConnection) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_SCREEN_TIME);

        } else {
            Toast.makeText(SplashScreenActivity.this,
                    "Internet connection not found .Please  check connection and try again!!", 100).show();

        }
    }


}