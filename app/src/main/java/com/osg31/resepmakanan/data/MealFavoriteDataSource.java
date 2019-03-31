package com.osg31.resepmakanan.data;

import com.osg31.resepmakanan.model.MealDetail;
import com.osg31.resepmakanan.model.MealFavorite;

import java.util.List;

public interface MealFavoriteDataSource {

    void getAllFavoriteMeal(GetFavoriteMealCallback getFavoriteMealCallback);
    void insertFavoriteMeal(MealDetail mealDetail);
    void deleteFavoriteMeal(String idMeal);
    void checkIsMealFavorite(String idMeal, CheckFavoriteMealCallback checkFavoriteMealCallback);

    interface GetFavoriteMealCallback {
        void onMealFavoriteLoaded(List<MealFavorite> mealFavoriteList);
        void onDataNotAvailable(String message);
    }


    interface CheckFavoriteMealCallback {
        void onMealFound(MealFavorite mealFavorite);
        void onMealNotFound();
    }

}
