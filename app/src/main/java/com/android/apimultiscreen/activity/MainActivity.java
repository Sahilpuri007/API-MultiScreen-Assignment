package com.android.apimultiscreen.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.android.apimultiscreen.R;
import com.android.apimultiscreen.fragment.SaveStateFragment;
import com.android.apimultiscreen.fragment.ShowDetailsFragment;
import com.android.apimultiscreen.listener.OnFragmentInteractionListener;
import com.android.apimultiscreen.listener.RecyclerTouchListener;
import com.android.apimultiscreen.model.User;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener, RecyclerTouchListener {

    SaveStateFragment saveStateFragment;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            user=savedInstanceState.getParcelable("USER_OBJ");
            if(user!=null) {
                addDetailsToFragment(user);
            }
        }

    }

    @Override
    public void passData(User user) {
        this.user=user;
        addDetailsToFragment(user);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("USER_OBJ",user);
    }


    private void addDetailsToFragment(User user){
        ShowDetailsFragment showDetailsFragment = (ShowDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
        if(showDetailsFragment!=null){

            showDetailsFragment.setDetails(user);
        }
    }

    @Override
    public void onItemClick(int position) {

    }
}
