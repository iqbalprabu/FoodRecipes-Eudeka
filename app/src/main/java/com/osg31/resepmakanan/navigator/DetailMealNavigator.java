package com.osg31.resepmakanan.navigator;

import com.osg31.resepmakanan.model.MealDetail;

import java.util.List;

public interface DetailMealNavigator {
    void loadDetailtMeal(List<MealDetail> mealDetailList);

    void onErrorLoadMeal(String message);
}
