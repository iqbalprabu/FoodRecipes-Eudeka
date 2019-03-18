package com.osg31.resepmakanan.viewmodel;

import android.content.Context;

import com.osg31.resepmakanan.data.MealDataSource;
import com.osg31.resepmakanan.data.MealRepository;
import com.osg31.resepmakanan.model.Meals;
import com.osg31.resepmakanan.navigator.MealNavigator;

public class ListMealViewModel {

    private MealRepository mealRepository;
    private MealNavigator mealNavigator;
    private Context context;

    public ListMealViewModel(MealRepository mealRepository, Context context) {
        this.mealRepository = mealRepository;
        this.context = context;
    }

    public void setNavigator(MealNavigator navigator) {
        mealNavigator = navigator;
    }

    public void getListMeal(String search) {

        mealRepository.getAllMeals(search, new MealDataSource.GetMealCallback() {
            @Override
            public void onMealLoaded(Meals data) {
                mealNavigator.loadListMeal(data.getMeals());
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                mealNavigator.onErrorLoadMeal(errorMessage);
            }
        });

    }

}
