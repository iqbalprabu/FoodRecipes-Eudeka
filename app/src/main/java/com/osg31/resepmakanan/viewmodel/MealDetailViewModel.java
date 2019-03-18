package com.osg31.resepmakanan.viewmodel;

import android.content.Context;

import com.osg31.resepmakanan.data.MealDataSource;
import com.osg31.resepmakanan.data.MealRepository;
import com.osg31.resepmakanan.model.Meals;
import com.osg31.resepmakanan.navigator.DetailMealNavigator;

public class MealDetailViewModel {

    private MealRepository mealRepository;
    private DetailMealNavigator mealNavigator;
    private Context context;

    public MealDetailViewModel(MealRepository mealRepository, Context context) {
        this.mealRepository = mealRepository;
        this.context = context;
    }

    public void setNavigator(DetailMealNavigator navigator) {
        mealNavigator = navigator;
    }

    public void getDetailMeal(String idMeal){
        mealRepository.getDetailMeal(idMeal, new MealDataSource.GetDetailCallback() {
            @Override
            public void onDetailMealLoaded(Meals data) {
                mealNavigator.loadDetailtMeal(data.getMeals());
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                mealNavigator.onErrorLoadMeal(errorMessage);
            }
        });
    }
}
