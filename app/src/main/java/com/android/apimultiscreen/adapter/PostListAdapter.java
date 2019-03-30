package com.android.apimultiscreen.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.apimultiscreen.R;
import com.android.apimultiscreen.listener.RecyclerTouchListener;
import com.android.apimultiscreen.model.Post;


import java.util.ArrayList;

public class PostListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<Post> postArrayList;
    private RecyclerTouchListener mListener;

    public PostListAdapter(ArrayList<Post> postArrayList, RecyclerTouchListener mListener) {
        this.postArrayList = postArrayList;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = (View) layoutInflater.inflate(R.layout.item_post, viewGroup, false);
        return new PostListAdapter.PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        PostListAdapter.PostViewHolder postViewHolder = (PostListAdapter.PostViewHolder) viewHolder;
        postViewHolder.tvId.setText(String.valueOf(postArrayList.get(i).getId()));
        postViewHolder.tvTitle.setText(postArrayList.get(i).getTitle());
        postViewHolder.tvBody.setText(postArrayList.get(i).getBody());

        postViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(viewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        TextView tvId,tvTitle,tvBody;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvPostId);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
        }
    }


}
