package com.android.apimultiscreen.fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.apimultiscreen.R;
import com.android.apimultiscreen.activity.HomeActivity;
import com.android.apimultiscreen.activity.ShowPostActivity;
import com.android.apimultiscreen.adapter.PostListAdapter;
import com.android.apimultiscreen.adapter.UserListAdapter;
import com.android.apimultiscreen.callback.UsersApi;
import com.android.apimultiscreen.model.Post;
import com.android.apimultiscreen.model.User;
import com.android.apimultiscreen.util.Constant;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

public class ShowDetailsFragment extends Fragment {

    private TextView tvId, tvName, tvUserName, tvEmail;
    private Button btnShowPost;
    private int userIdToFetchPost;
    private String userNameToFetchPost;
    private RelativeLayout rlNoUSer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_details, container, false);

        tvId = view.findViewById(R.id.tvIdShow);
        tvName = view.findViewById(R.id.tvNameShow);
        tvUserName = view.findViewById(R.id.tvUserNameShow);
        tvEmail = view.findViewById(R.id.tvEmailShow);
        btnShowPost = view.findViewById(R.id.btnShowPosts);
        rlNoUSer = view.findViewById(R.id.rlNoUser);
        btnShowPost.setVisibility(View.INVISIBLE);



        btnShowPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "On Click:"+userIdToFetchPost + userNameToFetchPost);
                Intent intent = new Intent(getContext(), ShowPostActivity.class);
                intent.putExtra(Constant.EXTRA_USER_NAME,userNameToFetchPost);
                intent.putExtra(Constant.EXTRA_USER_ID,userIdToFetchPost);
                startActivity(intent);
            }
        });

        return view;
    }


    /**
     * method to set details to fragment
     * @param user user details
     */
    public void setDetails(User user) {

            userIdToFetchPost = user.getId();
            userNameToFetchPost = user.getName();
            tvId.setText(String.valueOf(user.getId()));
            tvName.setText(user.getName());
            tvUserName.setText(user.getUserName());
            tvEmail.setText(user.getEmail());

            Log.d("test", "setDetails:"+userIdToFetchPost + userNameToFetchPost);
            btnShowPost.setVisibility(View.VISIBLE);
            rlNoUSer.setVisibility(View.INVISIBLE);

    }
}
