package com.osg31.resepmakanan.data.remote;

import com.osg31.resepmakanan.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api/json/v1/1/search.php")
    Call<Meals> getAllMeals(@Query("s") String search);

}
