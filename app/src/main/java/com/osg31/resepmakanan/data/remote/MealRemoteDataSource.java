package com.osg31.resepmakanan.data.remote;

import com.osg31.resepmakanan.data.MealDataSource;
import com.osg31.resepmakanan.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealRemoteDataSource implements MealDataSource {

    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    @Override
    public void getAllMeals(String search, final GetMealCallback callback) {
        Call<Meals> call = apiInterface.getAllMeals(search);
        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                callback.onMealLoaded(response.body());
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                callback.onDataNotAvailable(t.toString());
            }
        });
    }

    @Override
    public void getDetailMeal(String idMeal, GetDetailCallback callback) {
        Call<Meals> call = apiInterface.getDetailMeal(idMeal);
        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                callback.onDetailMealLoaded(response.body().getMeals().get(0));
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                callback.onDataNotAvailable(t.toString());
            }
        });
    }
}
