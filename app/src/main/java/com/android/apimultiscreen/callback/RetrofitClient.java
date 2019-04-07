package com.android.apimultiscreen.callback;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private Retrofit retrofit = null;

    public RetrofitClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(UsersApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     *
     * @return retrofit instance
     */
    public UsersApi getRetrofitApiInterface() {
        return retrofit.create(UsersApi.class);
    }


}
