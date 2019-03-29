package com.osg31.resepmakanan.view;
/*
 * Create by Alikhsan on 3/21/2019.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.osg31.resepmakanan.Injection;
import com.osg31.resepmakanan.R;
import com.osg31.resepmakanan.adapter.AdapterListMealFavorite;
import com.osg31.resepmakanan.model.MealFavorite;
import com.osg31.resepmakanan.navigator.FavoriteMealNavigator;
import com.osg31.resepmakanan.utils.RecyclerItemTouchListener;
import com.osg31.resepmakanan.viewmodel.ListFavoriteMealViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMealFavActivity extends AppCompatActivity implements FavoriteMealNavigator {
    @BindView(R.id.rv_recipe_list_fav)
    RecyclerView rvRecipeListFav;
    Toolbar toolbar;
    private AdapterListMealFavorite adapterListMealFavorite;
    private ListFavoriteMealViewModel favoriteMealViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meal_fav);
        ButterKnife.bind(this);

        favoriteMealViewModel = new ListFavoriteMealViewModel(Injection.provideFavoriteRepository(this));
        favoriteMealViewModel.setNavigator(this);
        favoriteMealViewModel.getListFavorite();

        initAdapter();
        initActionBar();
    }

    private void initActionBar() {
        toolbar.setTitle("List Favorites");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initAdapter() {
        rvRecipeListFav.setLayoutManager(new GridLayoutManager(this, 2));
        adapterListMealFavorite = new AdapterListMealFavorite(new ArrayList<>(), this);
        rvRecipeListFav.addOnItemTouchListener(new RecyclerItemTouchListener(this, rvRecipeListFav,
                new RecyclerItemTouchListener.onItemClickListener() {
                    @Override
                    public void onClickSingle(View view, int position) {
                        long idMeal = adapterListMealFavorite.getItemId(position);
                        Intent detailMeal = new Intent(ListMealFavActivity.this, MealDetailActivity.class);
                        detailMeal.putExtra(MealDetailActivity.DETAIL, idMeal);
                        startActivity(detailMeal);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
        rvRecipeListFav.setAdapter(adapterListMealFavorite);
    }

    @Override
    public void loadListFavoriteMeal(List<MealFavorite> mealFavoriteList) {
        adapterListMealFavorite.clearAll();
        if (mealFavoriteList != null) {
            adapterListMealFavorite.addAll(mealFavoriteList);
        } else {
            showEmptyView();
        }
    }

    @Override
    public void onErrorLoadFavoriteMeal(String message) {
        Snackbar.make(findViewById(android.R.id.content), "Check Your Connection...", Snackbar.LENGTH_SHORT).show();
    }

    private void showEmptyView() {
        if (adapterListMealFavorite.getItemCount() < 1) {
            setContentView(R.layout.empty_data_layout);
            rvRecipeListFav.setVisibility(View.GONE);
        } else {
            rvRecipeListFav.setVisibility(View.VISIBLE);
        }
    }
}
