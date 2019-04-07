package com.android.apimultiscreen.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.apimultiscreen.R;
import com.android.apimultiscreen.adapter.PostListAdapter;
import com.android.apimultiscreen.callback.UsersApi;
import com.android.apimultiscreen.model.Post;
import com.android.apimultiscreen.util.Constant;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowPostActivity extends AppCompatActivity {

    private RecyclerView rvPostList;
    private TextView tvUserTitle;
    private ArrayList<Post> postArrayList = new ArrayList<>();
    ProgressBar pbPostList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_post);
        init();
        if(savedInstanceState!=null){
            postArrayList=savedInstanceState.getParcelableArrayList(Constant.EXTRA_POST_LIST);
            if(postArrayList!=null) {
                rvPostList.setAdapter(new PostListAdapter(postArrayList));
            }
        }else {

            fetchPost(getIntent().getIntExtra(Constant.EXTRA_USER_ID, -1));
        }
    }

    private void init() {

        tvUserTitle=findViewById(R.id.tvUserTitle);
        rvPostList = findViewById(R.id.rvPostList);
        rvPostList.setLayoutManager(new LinearLayoutManager(this));
        tvUserTitle.setText(String.format("%s's Posts", getIntent().getStringExtra(Constant.EXTRA_USER_NAME)));
        postArrayList= getIntent().getParcelableArrayListExtra(Constant.EXTRA_POST_LIST);
        pbPostList = findViewById(R.id.pb_post_list);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            pbPostList.drawableHotspotChanged(20,20);
        }

    }

    private void fetchPost(int userId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UsersApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsersApi usersApi= retrofit.create(UsersApi.class);

              Call<ArrayList<Post>> call = usersApi.getPost(userId);

        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                ArrayList<Post> fetchedPostArrayList = response.body();
                for (int i=0;i<fetchedPostArrayList.size();i++) {
                    Log.d("POST", "onResponse: " +fetchedPostArrayList.get(i).getId()+" "+fetchedPostArrayList.get(i).getTitle());
                    rvPostList.setAdapter(new PostListAdapter(fetchedPostArrayList));
                    pbPostList.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                Log.d("POST", "onFailure: "+t.toString());

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Constant.EXTRA_POST_LIST,postArrayList);
    }



}
