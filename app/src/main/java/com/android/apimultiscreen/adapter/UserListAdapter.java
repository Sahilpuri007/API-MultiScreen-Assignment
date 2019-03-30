package com.android.apimultiscreen.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.apimultiscreen.R;
import com.android.apimultiscreen.listener.RecyclerTouchListener;
import com.android.apimultiscreen.model.User;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private ArrayList<User> userArrayList;
    private RecyclerTouchListener mListener;

    public UserListAdapter(ArrayList<User> userArrayList, RecyclerTouchListener mListener) {
        Log.d("Adapter", "UserListAdapter: "+ userArrayList.size());
        this.userArrayList = userArrayList;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = (View) layoutInflater.inflate(R.layout.item_user, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        UserViewHolder userViewHolder = (UserViewHolder) viewHolder;
        userViewHolder.tvId.setText(String.valueOf(userArrayList.get(i).getId()));
        userViewHolder.tvName.setText(userArrayList.get(i).getName());

        userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(viewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvId,tvName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);

        }
    }


}
