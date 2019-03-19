package com.osg31.resepmakanan.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.osg31.resepmakanan.Injection;
import com.osg31.resepmakanan.R;
import com.osg31.resepmakanan.adapter.AdapterListMeal;
import com.osg31.resepmakanan.model.MealDetail;
import com.osg31.resepmakanan.navigator.MealNavigator;
import com.osg31.resepmakanan.utils.RecyclerItemTouchListener;
import com.osg31.resepmakanan.viewmodel.ListMealViewModel;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMealActivity extends AppCompatActivity implements MealNavigator, RecyclerItemTouchListener.onItemClickListener {

    @BindView(R.id.rv_recipe_list) RecyclerView rvListMenu;
    @BindView(R.id.sw_refresh_layout) SwipeRefreshLayout swRefreshLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private AdapterListMeal adapterListMeal;
    private ListMealViewModel mealViewModel;
    private String meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meal);
        ButterKnife.bind(this);

        meal = getIntent().getStringExtra("data");

        mealViewModel = new ListMealViewModel(Injection.provideMealRepository(this));
        mealViewModel.setNavigator(this);
        mealViewModel.getListMeal(meal);

        initAdapter();
        initActionBar();
        initSwipeRefresh();
    }

    private void initSwipeRefresh() {
        swRefreshLayout.setColorSchemeColors(Color.GREEN);
        swRefreshLayout.setOnRefreshListener(() -> {
            swRefreshLayout.setRefreshing(true);
            if (swRefreshLayout.isRefreshing()) {
                mealViewModel.getListMeal(meal);
            } else {
                swRefreshLayout.setRefreshing(true);
            }
        });

    }

    private void initActionBar() {
        toolbar.setTitle("List Meals");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initAdapter() {
        rvListMenu.setLayoutManager(new GridLayoutManager(this, 2));
        adapterListMeal = new AdapterListMeal(new ArrayList<>(), this);
        rvListMenu.addOnItemTouchListener(new RecyclerItemTouchListener(this, rvListMenu,
                this));
        rvListMenu.setAdapter(adapterListMeal);
    }

    @Override
    public void loadListMeal(List<MealDetail> mealDetailList) {
        adapterListMeal.clearAll();
        if (mealDetailList != null) {
            adapterListMeal.addAll(mealDetailList);
        } else {
            showEmptyView();
        }
    }

    @Override
    public void onErrorLoadMeal(String message) {
        Snackbar.make(findViewById(android.R.id.content), "Check Your Connection...", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void showEmptyView() {
        if (adapterListMeal.getItemCount() < 1) {
            setContentView(R.layout.empty_data_layout);
            rvListMenu.setVisibility(View.GONE);
        } else {
            rvListMenu.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClickSingle(View view, int position) {
        String idMeal = adapterListMeal.getItem(position).idMeal;
        Intent detailMeal = new Intent(this, MealDetailActivity.class);
        detailMeal.putExtra(MealDetailActivity.DETAIL, idMeal);
        startActivity(detailMeal);
    }

    @Override
    public void onLongClick(View view, int position) {

    }
}
