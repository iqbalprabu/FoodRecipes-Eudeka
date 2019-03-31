package com.osg31.resepmakanan.data.local;

import android.content.Context;

import com.osg31.resepmakanan.data.MealFavoriteDataSource;
import com.osg31.resepmakanan.model.MealDetail;
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
    public void insertFavoriteMeal(MealDetail mealDetail) {
        Runnable runnable = () -> {

            MealFavorite mealFavorite = new MealFavorite();

            mealFavorite.setMealId(Integer.valueOf(mealDetail.idMeal));
            mealFavorite.setMealName(mealDetail.strMeal);
            mealFavorite.setMealCategory(mealDetail.strCategory);
            mealFavorite.setMealThumbnail(mealDetail.strMealThumb);

            favoriteDao.inserFavorite(mealFavorite);
        };

        new Thread(runnable).start();
    }

    @Override
    public void deleteFavoriteMeal(String idMeal) {
        Runnable runnable = () -> {
            favoriteDao.deleteFavorite(idMeal);
        };

        new Thread(runnable).start();
    }

    @Override
    public void checkIsMealFavorite(String idMeal, CheckFavoriteMealCallback checkFavoriteMealCallback) {
        Runnable runnable = () -> {
            MealFavorite mealFavorite = favoriteDao.findOneFavorite(idMeal);
            if(mealFavorite != null) checkFavoriteMealCallback.onMealFound(mealFavorite);
            else checkFavoriteMealCallback.onMealNotFound();
        };

        new Thread(runnable).start();
    }
}
