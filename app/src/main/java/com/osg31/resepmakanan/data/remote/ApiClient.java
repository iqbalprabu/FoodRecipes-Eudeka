package com.osg31.resepmakanan.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
