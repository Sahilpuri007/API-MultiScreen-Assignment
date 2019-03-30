package com.android.apimultiscreen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;


import com.android.apimultiscreen.R;
import com.android.apimultiscreen.adapter.UserListAdapter;
import com.android.apimultiscreen.callback.UsersApi;
import com.android.apimultiscreen.model.User;
import com.android.apimultiscreen.util.InternetCheckUtil;

import java.util.ArrayList;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashScreenActivity extends AppCompatActivity {



    private int SPLASH_SCREEN_TIME = 1000;
    private ArrayList<User> userArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        checkInternet();
        fetchFromApi();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        checkInternet();
        fetchFromApi();
        return super.onTouchEvent(event);

    }


    private void checkInternet() {
        Timer t = new Timer();
        boolean checkConnection=new InternetCheckUtil().checkConnection(this);
        if(checkConnection) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    intent.putParcelableArrayListExtra("userArrayList", userArrayList);
                    SplashScreenActivity.this.startActivity(intent);
                    finish();
                }
            }, SPLASH_SCREEN_TIME);
        }
        else{
            Toast.makeText(SplashScreenActivity.this,
                    "Internet connection not found .Please  check connection and try again!!",100).show();
        }
    }

    private void fetchFromApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UsersApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final UsersApi usersApi= retrofit.create(UsersApi.class);

        Call<ArrayList<User>> call = usersApi.getUser();

        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                userArrayList = response.body();
                Log.d("LIST", "onResponse: "+userArrayList.size());
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
    }
}