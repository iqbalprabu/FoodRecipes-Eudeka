package com.osg31.resepmakanan.viewmodel;

import com.osg31.resepmakanan.data.MealDataSource;
import com.osg31.resepmakanan.data.MealRepository;
import com.osg31.resepmakanan.model.MealDetail;
import com.osg31.resepmakanan.navigator.DetailMealNavigator;

public class MealDetailViewModel {

    private MealRepository mealRepository;
    private DetailMealNavigator mealNavigator;

    public MealDetailViewModel(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public void setNavigator(DetailMealNavigator navigator) {
        mealNavigator = navigator;
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
}
