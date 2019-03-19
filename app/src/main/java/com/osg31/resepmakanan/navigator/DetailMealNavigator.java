package com.osg31.resepmakanan.navigator;

import com.osg31.resepmakanan.model.MealDetail;

public interface DetailMealNavigator {
    void loadDetailtMeal(MealDetail mealDetail);

    void onErrorLoadMeal(String message);
}
