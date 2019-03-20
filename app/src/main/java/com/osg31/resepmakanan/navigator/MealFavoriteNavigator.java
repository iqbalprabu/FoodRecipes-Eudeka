package com.osg31.resepmakanan.navigator;

import com.osg31.resepmakanan.model.MealFavorite;

import java.util.List;

public interface MealFavoriteNavigator {

    void loadListFavoriteMeal(List<MealFavorite> mealFavoriteList);
    void onErrorLoadFavroiteMeal(String message);
}
