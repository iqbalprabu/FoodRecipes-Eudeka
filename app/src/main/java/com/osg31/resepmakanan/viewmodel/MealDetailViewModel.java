package com.osg31.resepmakanan.viewmodel;

import com.osg31.resepmakanan.data.MealDataSource;
import com.osg31.resepmakanan.data.MealFavoriteDataSource;
import com.osg31.resepmakanan.data.MealFavoriteRepository;
import com.osg31.resepmakanan.data.MealRepository;
import com.osg31.resepmakanan.model.MealDetail;
import com.osg31.resepmakanan.model.MealFavorite;
import com.osg31.resepmakanan.navigator.AddFavoriteNavigator;
import com.osg31.resepmakanan.navigator.DeleteFavoriteNavigator;
import com.osg31.resepmakanan.navigator.DetailMealNavigator;
import com.osg31.resepmakanan.navigator.FindFavoriteNavigator;

public class MealDetailViewModel {

    private MealRepository mealRepository;
    private MealFavoriteRepository favoriteRepository;

    private AddFavoriteNavigator addFavoriteNavigator;
    private DeleteFavoriteNavigator deleteFavoriteNavigator;
    private FindFavoriteNavigator findFavoriteNavigator;

    private DetailMealNavigator mealNavigator;

    public MealDetailViewModel(MealRepository mealRepository, MealFavoriteRepository favoriteRepository) {
        this.mealRepository = mealRepository;
        this.favoriteRepository = favoriteRepository;
    }


    public void getDetailMeal(String idMeal) {
        mealRepository.getDetailMeal(idMeal, new MealDataSource.GetDetailCallback() {
            @Override
            public void onDetailMealLoaded(MealDetail data) {
                mealNavigator.loadDetailtMeal(data);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                mealNavigator.onErrorLoadMeal(errorMessage);
            }
        });
    }

    public void addFavorite(MealDetail mealDetail) {
        try {
            favoriteRepository.insertFavoriteMeal(mealDetail);
            addFavoriteNavigator.onSuccessAddFavorite();
        } catch (Exception exception) {
            addFavoriteNavigator.onFailedAddFavorite();
        }
    }

    public void deleteFavorite(String idMeal) {
        try {
            favoriteRepository.deleteFavoriteMeal(idMeal);
            deleteFavoriteNavigator.onSuccessDeleteFavorite();
        } catch (Exception exception) {
            deleteFavoriteNavigator.onFailedDeleteFavorite();
        }
    }

    public void checkIsMealFavorite(String idMeal) {
        try {
            favoriteRepository.checkIsMealFavorite(idMeal, new MealFavoriteDataSource.CheckFavoriteMealCallback() {
                @Override
                public void onMealFound(MealFavorite mealFavorite) {
                    findFavoriteNavigator.onMealFound();
                }

                @Override
                public void onMealNotFound() {
                    findFavoriteNavigator.onMealNotFound();
                }
            });
        } catch (Exception exception) {
            findFavoriteNavigator.onMealNotFound();
        }
    }

    public void setAddFavoriteNavigator(AddFavoriteNavigator addFavoriteNavigator) {
        this.addFavoriteNavigator = addFavoriteNavigator;
    }

    public void setDeleteFavoriteNavigator(DeleteFavoriteNavigator deleteFavoriteNavigator) {
        this.deleteFavoriteNavigator = deleteFavoriteNavigator;
    }

    public void setFindFavoriteNavigator(FindFavoriteNavigator findFavoriteNavigator) {
        this.findFavoriteNavigator = findFavoriteNavigator;
    }

    public void setNavigator(DetailMealNavigator navigator) {
        mealNavigator = navigator;
    }

}
