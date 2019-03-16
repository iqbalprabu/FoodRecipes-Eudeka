package com.osg31.resepmakanan.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.osg31.resepmakanan.data.remote.MealRemoteDataSource;
import com.osg31.resepmakanan.model.Meals;

import java.util.List;

public class MealRepository implements MealDataSource {
    private MealRemoteDataSource remoteDataSource;

    public MealRepository(MealRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getAllMeals(final Context search, final GetMealCallback callback) {
        NetworkInfo info = ((ConnectivityManager) search.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            Toast.makeText(search, "Using database cloud", Toast.LENGTH_SHORT).show();
            remoteDataSource.getAllMeals(search, new GetMealCallback() {
                @Override
                public void onMealLoaded(Meals data) {
                    if (data == null ){
                        Toast.makeText(search, "Data Null", Toast.LENGTH_SHORT).show();
                    } else {
                        callback.onMealLoaded(data);

                    }
                }
                @Override
                public void onDataNotAvailable(String errorMessage) {
                    callback.onDataNotAvailable(errorMessage);
                }
            });
        }

        else  {
            callback.onMealLoaded(null);
            callback.onDataNotAvailable("");
        }
    }

}
