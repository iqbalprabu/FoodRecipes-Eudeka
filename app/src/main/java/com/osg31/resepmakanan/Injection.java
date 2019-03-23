package com.osg31.resepmakanan;

import android.content.Context;

import com.osg31.resepmakanan.data.FavoriteRepository;
import com.osg31.resepmakanan.data.MealRepository;
import com.osg31.resepmakanan.data.local.FavoriteLocalDataSource;
import com.osg31.resepmakanan.data.remote.MealRemoteDataSource;

/**
 * Class for dependency injection
 *
 */
public class Injection {

    public static MealRepository provideMealRepository(Context context) {
        return new MealRepository(new MealRemoteDataSource());
    }

    public static FavoriteRepository provideFavoriteRepository(Context context) {
        return new FavoriteRepository(new FavoriteLocalDataSource(context));
    }

}
