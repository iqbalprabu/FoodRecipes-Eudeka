package com.osg31.resepmakanan.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Intent;

@Entity(tableName = "mealFavorite")
public class MealFavorite {

    @PrimaryKey
    @ColumnInfo(name = "meal_id")
    private Integer mealId;

    @ColumnInfo(name = "meal_name")
    private String mealName;

    @ColumnInfo(name = "meal_thumbnail")
    private String mealThumbnail;

    @ColumnInfo(name = "meal_category")
    private String mealCategory;


    public Integer getMealId() {
        return mealId;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealThumbnail() {
        return mealThumbnail;
    }

    public void setMealThumbnail(String mealThumbnail) {
        this.mealThumbnail = mealThumbnail;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }
}
