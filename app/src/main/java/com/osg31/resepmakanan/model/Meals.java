package com.osg31.resepmakanan.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Meals {

    @SerializedName("meals")
    private List<MealDetail> meals;

    public Meals(List<MealDetail> meals) {
        this.meals = meals;
    }

    public List<MealDetail> getMeals() {
        return meals;
    }

}
