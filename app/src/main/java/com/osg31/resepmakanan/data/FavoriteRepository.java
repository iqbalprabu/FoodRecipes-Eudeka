package com.osg31.resepmakanan.data;

import com.osg31.resepmakanan.data.local.FavoriteLocalDataSource;
import com.osg31.resepmakanan.data.remote.MealRemoteDataSource;
import com.osg31.resepmakanan.model.MealDetail;
import com.osg31.resepmakanan.model.MealFavorite;

public class FavoriteRepository implements MealFavoriteDataSource {

    private FavoriteLocalDataSource favoriteLocalDataSource;

    public FavoriteRepository(FavoriteLocalDataSource favoriteLocalDataSource) {
        this.favoriteLocalDataSource = favoriteLocalDataSource;
    }

    @Override
    public void getAllFavoriteMeal(GetFavoriteMealCallback getFavoriteMealCallback) {
        favoriteLocalDataSource.getAllFavoriteMeal(getFavoriteMealCallback);
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
    public void checkIsMealFavorite(String idMeal) {
        favoriteLocalDataSource.checkIsMealFavorite(idMeal);
    }


}
