package com.osg31.resepmakanan.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.osg31.resepmakanan.Injection;
import com.osg31.resepmakanan.R;
import com.osg31.resepmakanan.model.MealDetail;
import com.osg31.resepmakanan.navigator.AddFavoriteNavigator;
import com.osg31.resepmakanan.navigator.DeleteFavoriteNavigator;
import com.osg31.resepmakanan.navigator.DetailMealNavigator;
import com.osg31.resepmakanan.navigator.FindFavoriteNavigator;
import com.osg31.resepmakanan.viewmodel.MealDetailViewModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealDetailActivity extends AppCompatActivity implements DetailMealNavigator, AddFavoriteNavigator, DeleteFavoriteNavigator, FindFavoriteNavigator {

    public static final String DETAIL = "detail";
//    private static final String YOUTUBE_THUMBNAIL = "https://img.youtube.com/vi/%s/mqdefault.jpg";

    @BindView(R.id.tv_title_meal_detail)
    TextView tvTitleMeal;
    @BindView(R.id.tv_category_meal_detail)
    TextView tvCategoryMeal;
    @BindView(R.id.tv_description_meal_detail)
    TextView tvDescMeal;
    @BindView(R.id.iv_poster_detail)
    ImageView ivPosterDetail;
    @BindView(R.id.cl_detail_meal)
    CoordinatorLayout clDetailMeal;
    @BindView(R.id.collapsing_detail)
    CollapsingToolbarLayout collapsingDetail;
    @BindView(R.id.toolbar_detail)
    Toolbar toolbarDetail;
    @BindView(R.id.fabFavorite)
    FloatingActionButton fabFavorite;
    @BindView(R.id.recipe_video_thumbnail)
    ImageView detailStepRecipe;

    private Menu collapsedMenu;
    private boolean appBarExpanded = true;

    private MealDetailViewModel mealViewModel;
    private AppBarLayout appBarLayout;
    private String idMeal;
    private Boolean isFavorite;
    private MealDetail mealDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);
        ButterKnife.bind(this);

        idMeal = getIntent().getStringExtra(DETAIL);

        mealViewModel = new MealDetailViewModel(Injection.provideMealRepository(this),
                Injection.provideFavoriteRepository(this));

        mealViewModel.setFindFavoriteNavigator(this);
        mealViewModel.setAddFavoriteNavigator(this);
        mealViewModel.setDeleteFavoriteNavigator(this);
        mealViewModel.setNavigator(this);
        mealViewModel.getDetailMeal(idMeal);

        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            //  Vertical offset == 0 indicates appBar is fully expanded.
            if (Math.abs(verticalOffset) > 200) {
                appBarExpanded = false;
                invalidateOptionsMenu();
            } else {
                appBarExpanded = true;
                invalidateOptionsMenu();
            }
        });

        fabFavorite.setOnClickListener(v -> {
//            Toast.makeText(this, "mealFavorite: " + isFavorite, Toast.LENGTH_SHORT).show();
            if (isFavorite = false) mealViewModel.deleteFavorite(idMeal);
            else mealViewModel.addFavorite(mealDetail);
        });
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
        this.mealDetail = mealDetail;
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

        Picasso.get()
                .load(String.format(mealDetail.getStrMealThumb()))
                .into(detailStepRecipe);

        detailStepRecipe.setOnClickListener(v -> {
            if (mealDetail != null && mealDetail.getStrYoutube() != null) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mealDetail.getStrYoutube()));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (collapsedMenu != null
                && (!appBarExpanded || collapsedMenu.size() != 1)) {
            //collapsed

            if (isFavorite) {
                collapsedMenu.add("Add")
                        .setIcon(R.drawable.ic_star_checked)
                        .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            } else {
                collapsedMenu.add("Add")
                        .setIcon(R.drawable.ic_star_unchecked)
                        .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            }
        } else {
            if (isFavorite != null) {
                if (isFavorite) {
                    fabFavorite
                            .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_star_checked));
                } else {
                    fabFavorite
                            .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_star_unchecked));
                }
            }
        }
        return super.onPrepareOptionsMenu(collapsedMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        this.collapsedMenu = menu;
        mealViewModel.checkIsMealFavorite(idMeal);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != android.R.id.home) {
            if (isFavorite) mealViewModel.deleteFavorite(idMeal);
            else mealViewModel.addFavorite(mealDetail);
        } else finish();
        return true;
    }

    @Override
    public void onSuccessAddFavorite() {
        Toast.makeText(this, "Success save favorite meal", Toast.LENGTH_SHORT).show();
        isFavorite = true;
        fabFavorite
                .setImageResource(R.drawable.ic_star_checked);

    }

    @Override
    public void onFailedAddFavorite() {
        Toast.makeText(this, "Failed save favorite meal", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessDeleteFavorite() {
        Toast.makeText(this, "Success delete favorite meal", Toast.LENGTH_SHORT).show();
        isFavorite = false;

        fabFavorite
                .setImageResource(R.drawable.ic_star_unchecked);
    }

    @Override
    public void onFailedDeleteFavorite() {
        Toast.makeText(this, "Failed delete favorite meal", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealFound() {
        isFavorite = true;

        fabFavorite
                .setImageResource(R.drawable.ic_star_checked);
    }

    @Override
    public void onMealNotFound() {
        isFavorite = false;
        fabFavorite
                .setImageResource(R.drawable.ic_star_unchecked);
    }
}