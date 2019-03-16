package com.osg31.resepmakanan.view;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.osg31.resepmakanan.Injection;
import com.osg31.resepmakanan.R;
import com.osg31.resepmakanan.adapter.AdapterListMeal;
import com.osg31.resepmakanan.model.MealDetail;
import com.osg31.resepmakanan.navigator.MealNavigator;
import com.osg31.resepmakanan.viewmodel.ListMealViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMealActivity extends AppCompatActivity implements MealNavigator {

    @BindView(R.id.rv_list_menu)
    RecyclerView rvListMenu;

    private AdapterListMeal adapterListMeal;
    private List<MealDetail> list;
    private ListMealViewModel mealViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meal);
        ButterKnife.bind(this);

        String meal = getIntent().getStringExtra("data");

        list = new ArrayList<>();
        mealViewModel = new ListMealViewModel(Injection.provideMealRepository(this), this);
        mealViewModel.setNavigator(this);
        mealViewModel.getListMeal(meal);
        initAdapter();
    }

    private void initAdapter() {
        adapterListMeal = new AdapterListMeal(list, this);
        rvListMenu.setLayoutManager(new LinearLayoutManager(this));
        rvListMenu.setAdapter(adapterListMeal);
    }

    @Override
    public void loadListMeal(List<MealDetail> mealDetailList) {
        list.addAll(mealDetailList);
        adapterListMeal.notifyDataSetChanged();
    }

    @Override
    public void onErrorLoadMeal(String message) {
        Snackbar.make(findViewById(android.R.id.content), "Check Your Connection...", Snackbar.LENGTH_SHORT).show();
    }
}
