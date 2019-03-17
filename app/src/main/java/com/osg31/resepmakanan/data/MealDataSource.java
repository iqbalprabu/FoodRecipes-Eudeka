package com.osg31.resepmakanan.data;

import com.osg31.resepmakanan.model.Meals;

public interface MealDataSource {

    void getAllMeals(String search, GetMealCallback callback);

    interface GetMealCallback {
        void onMealLoaded(Meals data);

        void onDataNotAvailable(String errorMessage);
    }

}
