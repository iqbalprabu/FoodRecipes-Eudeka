package com.osg31.resepmakanan.data;

import com.osg31.resepmakanan.data.local.FavoriteLocalDataSource;
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
    public void insertFavoriteMeal(MealFavorite mealFavorite) {
        favoriteLocalDataSource.insertFavoriteMeal(mealFavorite);
    }

    @Override
    public void deleteFavoriteMeal(MealFavorite mealFavorite) {
        favoriteLocalDataSource.deleteFavoriteMeal(mealFavorite);
    }
}
