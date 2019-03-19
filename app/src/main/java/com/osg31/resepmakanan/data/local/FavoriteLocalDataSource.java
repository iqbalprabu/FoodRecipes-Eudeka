package com.osg31.resepmakanan.data.local;

import android.content.Context;

import com.osg31.resepmakanan.data.MealFavoriteDataSource;
import com.osg31.resepmakanan.model.MealFavorite;

import java.util.List;

public class FavoriteLocalDataSource implements MealFavoriteDataSource {


    private FavoriteDao favoriteDao;

    public FavoriteLocalDataSource(Context context) {
        favoriteDao = FavoriteDataBase.getInstance(context).favoriteDao();
    }

    @Override
    public void getAllFavoriteMeal(GetFavoriteMealCallback getFavoriteMealCallback) {
        Runnable runnable = () -> {
            List<MealFavorite> favoriteList = favoriteDao.getAllFavoriteMeal();
            if(favoriteList.isEmpty()) {
                getFavoriteMealCallback.onDataNotAvailable("Data is empty");
            } else {
                getFavoriteMealCallback.onMealFavoriteLoaded(favoriteList);
            }
        };

        new Thread(runnable).start();
    }

    @Override
    public void insertFavoriteMeal(MealFavorite mealFavorite) {
        Runnable runnable = () -> {
            favoriteDao.inserFavorite(mealFavorite);
        };

        new Thread(runnable).start();
    }

    @Override
    public void deleteFavoriteMeal(MealFavorite mealFavorite) {
        Runnable runnable = () -> {
            favoriteDao.deleteFavorite(mealFavorite);
        };

        new Thread(runnable).start();
    }
}
