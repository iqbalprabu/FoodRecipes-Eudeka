package com.osg31.resepmakanan.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.osg31.resepmakanan.model.MealFavorite;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM mealFavorite")
    List<MealFavorite> getAllFavoriteMeal();

    @Query("SELECT * FROM mealFavorite WHERE meal_id = :idMeal")
    MealFavorite findOneFavorite(String idMeal);

    @Insert
    void inserFavorite(MealFavorite mealFavorite);

    @Query("DELETE FROM mealFavorite WHERE meal_id = :idMeal")
    void deleteFavorite(String idMeal);

}
