package com.android.apimultiscreen.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.apimultiscreen.R;
import com.android.apimultiscreen.adapter.UserListAdapter;
import com.android.apimultiscreen.callback.RetrofitClient;
import com.android.apimultiscreen.callback.UsersApi;
import com.android.apimultiscreen.fragment.ShowDetailsFragment;
import com.android.apimultiscreen.listener.OnFragmentInteractionListener;
import com.android.apimultiscreen.listener.RecyclerTouchListener;
import com.android.apimultiscreen.model.User;
import com.android.apimultiscreen.util.Constant;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements OnFragmentInteractionListener, RecyclerTouchListener {

    private User user;
    private ArrayList<User> userArrayList = new ArrayList<>();
    private RecyclerView rvUserList;
    private RecyclerTouchListener mTouchListener = this;
    private Context mContext;
    ProgressBar pbUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        if (savedInstanceState != null) {
            userArrayList = savedInstanceState.getParcelableArrayList(Constant.EXTRA_USER_LIST);
            if (userArrayList != null) {
                rvUserList.setAdapter(new UserListAdapter(userArrayList, mTouchListener));
            }
        } else {

            fetchFromApi();
        }


        if (savedInstanceState != null) {
            user = savedInstanceState.getParcelable(Constant.EXTRA_USER);
            if (user != null) {
                Log.d("HERE", "onCreate: ");
                addDetailsToFragment(user);
            }
        }

    }


    @Override
    public void passData(User user) {
        this.user = user;
        addDetailsToFragment(user);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constant.EXTRA_USER, user);
        outState.putParcelableArrayList(Constant.EXTRA_USER_LIST, userArrayList);
    }


    @Override
    public void onItemClick(int position) {
        OnFragmentInteractionListener mListener = (OnFragmentInteractionListener) mContext;
        mListener.passData(userArrayList.get(position));
    }

    /**
     * Method to initailize views
     */
    private void init() {
        mContext = this;
        rvUserList = findViewById(R.id.rvUserList);
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
        pbUserList = findViewById(R.id.pb_user_list);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            pbUserList.drawableHotspotChanged(20, 20);
        }

    }


    /**
     * TO set User details to second fragment
     *
     * @param user user details
     */
    private void addDetailsToFragment(User user) {
        ShowDetailsFragment showDetailsFragment = (ShowDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
        if (showDetailsFragment != null) {

            showDetailsFragment.setDetails(user);
        }

    }

    /**
     * Fetch List from api
     */
    private void fetchFromApi() {

        RetrofitClient retrofitClient = new RetrofitClient();
        final UsersApi usersApi = retrofitClient.getRetrofitApiInterface();

        Call<ArrayList<User>> call = usersApi.getUser();

        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if (response.isSuccessful()) {
                    userArrayList = response.body();
                    rvUserList.setAdapter(new UserListAdapter(userArrayList, mTouchListener));
                    pbUserList.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
    }


}
