package com.android.apimultiscreen.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.apimultiscreen.R;
import com.android.apimultiscreen.adapter.PostListAdapter;
import com.android.apimultiscreen.listener.RecyclerTouchListener;
import com.android.apimultiscreen.model.Post;

import java.util.ArrayList;

public class ShowPostActivity extends AppCompatActivity implements RecyclerTouchListener {

    private RecyclerView rvPostList;
    private ArrayList<Post> postArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_post);
        rvPostList = findViewById(R.id.rvPostList);
        rvPostList.setLayoutManager(new LinearLayoutManager(this));

        postArrayList= getIntent().getParcelableArrayListExtra("postArrayList");

        rvPostList.setAdapter(new PostListAdapter(postArrayList,this));
    }

    @Override
    public void onItemClick(int position) {

    }
}
