package com.osg31.resepmakanan.navigator;

import com.osg31.resepmakanan.model.MealFavorite;

import java.util.List;

public interface FavoriteMealNavigator {

    void loadListFavoriteMeal(List<MealFavorite> mealFavoriteList);
    void onErrorLoadFavoriteMeal(String message);

}
