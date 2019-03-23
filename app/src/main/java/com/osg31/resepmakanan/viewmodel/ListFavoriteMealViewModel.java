package com.osg31.resepmakanan.viewmodel;

import com.osg31.resepmakanan.data.FavoriteRepository;
import com.osg31.resepmakanan.data.MealFavoriteDataSource;
import com.osg31.resepmakanan.model.MealFavorite;
import com.osg31.resepmakanan.navigator.FavoriteMealNavigator;

import java.util.List;

public class ListFavoriteMealViewModel {

    private FavoriteRepository favoriteRepository;
    private FavoriteMealNavigator favoriteMealNavigator;

    public ListFavoriteMealViewModel(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public void setNavigator(FavoriteMealNavigator navigator) {
        favoriteMealNavigator = navigator;
    }

    public void getListFavorite() {

        favoriteRepository.getAllFavoriteMeal(new MealFavoriteDataSource.GetFavoriteMealCallback() {
            @Override
            public void onMealFavoriteLoaded(List<MealFavorite> mealFavoriteList) {
                favoriteMealNavigator.loadListFavoriteMeal(mealFavoriteList);
            }

            @Override
            public void onDataNotAvailable(String message) {
                favoriteMealNavigator.onErrorLoadFavoriteMeal(message);
            }
        });

    }

}
