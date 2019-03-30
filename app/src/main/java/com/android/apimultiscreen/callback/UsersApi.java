package com.android.apimultiscreen.callback;

import android.util.Log;

import com.android.apimultiscreen.model.Post;
import com.android.apimultiscreen.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UsersApi {

    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @GET("users")
    Call<ArrayList<User>> getUser();

    @GET("posts")
    Call<ArrayList<Post>> getPost(@Query("userId") int userId);
}
