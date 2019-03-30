package com.android.apimultiscreen.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.apimultiscreen.R;
import com.android.apimultiscreen.adapter.UserListAdapter;
import com.android.apimultiscreen.listener.OnFragmentInteractionListener;
import com.android.apimultiscreen.listener.RecyclerTouchListener;
import com.android.apimultiscreen.model.User;

import java.util.ArrayList;


public class ShowListFragment extends Fragment  implements RecyclerTouchListener {

    private RecyclerView rvUserList;
    private ArrayList<User> userArrayList = new ArrayList<>();
    //private ArrayList<User> fetchedUserArrayList = new ArrayList<>();
    private RecyclerTouchListener mTouchListener=this;
    private OnFragmentInteractionListener mListener;
    private Context mContext;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_show_list,container,false);



        userArrayList=getActivity().getIntent().getParcelableArrayListExtra("userArrayList");

        rvUserList = view.findViewById(R.id.rvUserList);
        rvUserList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvUserList.setAdapter(new UserListAdapter(userArrayList,mTouchListener));
        /*if(savedInstanceState!=null){
            userArrayList=savedInstanceState.getParcelableArrayList("USER_LIST");
            if(userArrayList!=null) {
                rvUserList.setAdapter(new UserListAdapter(userArrayList,mTouchListener));
            }
        }*/


        //addToArrayList();
        return view;
    }

    private void addToArrayList() {
        /*for(int i=0;i<=5;i++){
            userArrayList.add(i,new User(""+(i+1),"user"+(i+1),"user"+(i+10001),"user"+(i+1)+"@users.com"));
        }*/
      /*  Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UsersApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsersApi usersApi= retrofit.create(UsersApi.class);

        Call<ArrayList<User>> call = usersApi.getUser();

        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                fetchedUserArrayList = response.body();
                Log.d("LIST", "onResponse: "+fetchedUserArrayList.size());
                rvUserList.setAdapter(new UserListAdapter(fetchedUserArrayList,mTouchListener));

            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });*/

    }

    @Override
    public void onItemClick(int position) {
       // this.userArrayList.add(userArrayList.get(position));
        mListener.passData(userArrayList.get(position));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("USER_LIST",userArrayList);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
