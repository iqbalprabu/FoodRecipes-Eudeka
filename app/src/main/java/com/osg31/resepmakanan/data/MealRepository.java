package com.osg31.resepmakanan.data;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;
import com.osg31.resepmakanan.data.remote.MealRemoteDataSource;
import com.osg31.resepmakanan.model.Meals;
public class MealRepository implements MealDataSource{
    private MealRemoteDataSource mealRemoteDataSource;
    public MealRepository(MealRemoteDataSource mealRemoteDataSource) {
        this.mealRemoteDataSource = mealRemoteDataSource;
    }

    public void getAllMeals(final String search, final GetMealCallback callback) {
        mealRemoteDataSource.getAllMeals(search,new GetMealCallback() {
            @Override
            public void onMealLoaded(Meals data) {
                //insert  to Databa
                callback.onMealLoaded(data);
            }
            @Override
            public void onDataNotAvailable(String errorMessage) {
                callback.onDataNotAvailable(errorMessage);
            }
        });
    }
}
