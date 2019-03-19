package com.osg31.resepmakanan.view;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.osg31.resepmakanan.Injection;
import com.osg31.resepmakanan.R;
import com.osg31.resepmakanan.model.MealDetail;
import com.osg31.resepmakanan.navigator.DetailMealNavigator;
import com.osg31.resepmakanan.viewmodel.MealDetailViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealDetailActivity extends AppCompatActivity implements DetailMealNavigator {

    public static final String DETAIL = "detail";

    @BindView(R.id.tv_title_meal_detail) TextView tvTitleMeal;
    @BindView(R.id.tv_category_meal_detail) TextView tvCategoryMeal;
    @BindView(R.id.tv_description_meal_detail) TextView tvDescMeal;
    @BindView(R.id.iv_poster_detail) ImageView ivPosterDetail;
    @BindView(R.id.cl_detail_meal) CoordinatorLayout clDetailMeal;
    @BindView(R.id.collapsing_detail) CollapsingToolbarLayout collapsingDetail;
    @BindView(R.id.toolbar_detail) Toolbar toolbarDetail;

    private MealDetailViewModel mealViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);
        ButterKnife.bind(this);

        String idMeal = getIntent().getStringExtra(DETAIL);

        mealViewModel = new MealDetailViewModel(Injection.provideMealRepository(this));
        mealViewModel.setNavigator(this);
        mealViewModel.getDetailMeal(idMeal);
    }

    private void initActionBar(MealDetail mealDetail) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbarDetail.setTitle(mealDetail.strMeal);
            setSupportActionBar(toolbarDetail);
        }
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void loadDetailtMeal(MealDetail mealDetail) {
        initActionBar(mealDetail);
        loadMealDetail(mealDetail);
    }

    @Override
    public void onErrorLoadMeal(String message) {
        Snackbar.make(findViewById(android.R.id.content), "Check Your Connection", Snackbar.LENGTH_SHORT).show();
    }

    private void loadMealDetail(MealDetail mealDetail) {
        MealDetail.loadImage(ivPosterDetail, mealDetail.strMealThumb);

        tvTitleMeal.setText(mealDetail.strMeal);
        tvCategoryMeal.setText(mealDetail.strCategory);
        tvDescMeal.setText(mealDetail.strInstructions);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
