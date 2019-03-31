package com.osg31.resepmakanan.adapter;
/*
 * Create by Alikhsan on 3/21/2019.
 */

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.osg31.resepmakanan.R;
import com.osg31.resepmakanan.databinding.ItemMealFavoriteBinding;
import com.osg31.resepmakanan.model.MealDetail;
import com.osg31.resepmakanan.model.MealFavorite;

import java.util.List;

public class AdapterListMealFavorite extends RecyclerView.Adapter<AdapterListMealFavorite.MealViewHolder> {
    private List<MealFavorite> dataMealSet;
    private Context context;

    public AdapterListMealFavorite(List<MealFavorite> dataMealSet, Context context) {
        this.dataMealSet = dataMealSet;
        this.context = context;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemMealFavoriteBinding mealFavoriteBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.item_meal_favorite, viewGroup, false);

        return new MealViewHolder(mealFavoriteBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder mealViewHolder, int i) {
        mealViewHolder.mealFavoriteBinding.setItemmealFavVM(dataMealSet.get(i));
    }

    @Override
    public int getItemCount() {
        return dataMealSet.size();
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(dataMealSet.get(position).getMealId());
    }

    public MealFavorite getItem(int position) {
        return dataMealSet.get(position);
    }

    public void addAll(List<MealFavorite> dataBind) {
        this.dataMealSet.addAll(dataBind);
        notifyDataSetChanged();
    }

    public void clearAll() {
        this.dataMealSet.clear();
        notifyDataSetChanged();
    }


    class MealViewHolder extends RecyclerView.ViewHolder {
        private final ItemMealFavoriteBinding mealFavoriteBinding;

        MealViewHolder(ItemMealFavoriteBinding mealFavoriteBinding) {
            super(mealFavoriteBinding.getRoot());
            this.mealFavoriteBinding = mealFavoriteBinding;
        }
    }
}
