package com.osg31.resepmakanan.data;

import com.osg31.resepmakanan.model.Meals;
import android.content.Context;
public interface MealDataSource {

    void getAllMeals(Context search, GetMealCallback callback);

    interface GetMealCallback {
        void onMealLoaded(Meals data);
        void onDataNotAvailable(String errorMessage);
    }

}
