package com.osg31.resepmakanan.data;

import com.osg31.resepmakanan.model.MealDetail;
import com.osg31.resepmakanan.model.Meals;

public interface MealDataSource {

    void getAllMeals(String search, GetMealCallback callback);

    void getDetailMeal(String idMeal, GetDetailCallback callback);

    interface GetMealCallback {
        void onMealLoaded(Meals data);
        void onDataNotAvailable(String errorMessage);
    }

    interface GetDetailCallback {
        void onDetailMealLoaded(MealDetail data);
        void onDataNotAvailable(String errorMessage);
    }
}
