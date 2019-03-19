package com.osg31.resepmakanan.data;

import com.osg31.resepmakanan.data.remote.MealRemoteDataSource;
import com.osg31.resepmakanan.model.MealDetail;
import com.osg31.resepmakanan.model.Meals;

public class MealRepository implements MealDataSource {

    private MealRemoteDataSource mealRemoteDataSource;

    public MealRepository(MealRemoteDataSource mealRemoteDataSource) {
        this.mealRemoteDataSource = mealRemoteDataSource;
    }

    public void getAllMeals(final String search, final GetMealCallback callback) {
        mealRemoteDataSource.getAllMeals(search, new GetMealCallback() {
            @Override
            public void onMealLoaded(Meals data) {
                callback.onMealLoaded(data);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                callback.onDataNotAvailable(errorMessage);
            }
        });
    }

    public void getDetailMeal(String idMeal, GetDetailCallback callback) {
        mealRemoteDataSource.getDetailMeal(idMeal, new GetDetailCallback() {
            @Override
            public void onDetailMealLoaded(MealDetail data) {
                callback.onDetailMealLoaded(data);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                callback.onDataNotAvailable(errorMessage);
            }
        });
    }
}
