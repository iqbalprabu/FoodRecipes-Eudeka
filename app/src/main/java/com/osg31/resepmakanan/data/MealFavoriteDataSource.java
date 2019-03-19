package com.osg31.resepmakanan.data;

import com.osg31.resepmakanan.model.MealFavorite;

import java.util.List;

public interface MealFavoriteDataSource {

    void getAllFavoriteMeal(GetFavoriteMealCallback getFavoriteMealCallback);
    void insertFavoriteMeal(MealFavorite mealFavorite);
    void deleteFavoriteMeal(MealFavorite mealFavorite);

    interface GetFavoriteMealCallback {
        void onMealFavoriteLoaded(List<MealFavorite> mealFavoriteList);
        void onDataNotAvailable(String message);
    }


}
