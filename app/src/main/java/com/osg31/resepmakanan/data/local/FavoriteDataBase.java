package com.osg31.resepmakanan.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.osg31.resepmakanan.model.MealFavorite;

@Database(entities = {MealFavorite.class}, version = 1)
public abstract class FavoriteDataBase extends RoomDatabase {

    private static FavoriteDataBase INSTANCE;

    public abstract FavoriteDao favoriteDao();

    private static final Object sLock = new Object();

    public static FavoriteDataBase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        FavoriteDataBase.class, "FavoriteMeal.db")
                        .build();
            }
            return INSTANCE;
        }
    }

}
