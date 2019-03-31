package com.osg31.resepmakanan.data;

import com.osg31.resepmakanan.data.local.FavoriteLocalDataSource;
import com.osg31.resepmakanan.model.MealDetail;
import com.osg31.resepmakanan.model.MealFavorite;

import java.util.List;

public class MealFavoriteRepository implements MealFavoriteDataSource {

    private FavoriteLocalDataSource favoriteLocalDataSource;

    public MealFavoriteRepository(FavoriteLocalDataSource favoriteLocalDataSource) {
        this.favoriteLocalDataSource = favoriteLocalDataSource;
    }

    @Override
    public void getAllFavoriteMeal(GetFavoriteMealCallback getFavoriteMealCallback) {
        favoriteLocalDataSource.getAllFavoriteMeal(new GetFavoriteMealCallback() {
            @Override
            public void onMealFavoriteLoaded(List<MealFavorite> mealFavoriteList) {
                getFavoriteMealCallback.onMealFavoriteLoaded(mealFavoriteList);
            }

            @Override
            public void onDataNotAvailable(String message) {
                getFavoriteMealCallback.onDataNotAvailable(message);
            }
        });
    }

    @Override
    public void insertFavoriteMeal(MealDetail mealDetail) {
        favoriteLocalDataSource.insertFavoriteMeal(mealDetail);
    }

    @Override
    public void deleteFavoriteMeal(String idMeal) {
        favoriteLocalDataSource.deleteFavoriteMeal(idMeal);
    }

    @Override
    public void checkIsMealFavorite(String idMeal, CheckFavoriteMealCallback checkFavoriteMealCallback) {
        favoriteLocalDataSource.checkIsMealFavorite(idMeal, checkFavoriteMealCallback);
    }
}
