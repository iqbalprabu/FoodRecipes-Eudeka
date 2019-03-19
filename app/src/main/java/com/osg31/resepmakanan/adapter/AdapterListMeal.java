package com.osg31.resepmakanan.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.osg31.resepmakanan.R;
import com.osg31.resepmakanan.databinding.ItemMealBinding;
import com.osg31.resepmakanan.model.MealDetail;
import com.osg31.resepmakanan.utils.RecyclerItemTouchListener;

import java.util.List;

public class AdapterListMeal extends RecyclerView.Adapter<AdapterListMeal.MealViewHolder> {

    private List<MealDetail> dataMealSet;
    private Context context;
    private RecyclerItemTouchListener recyclerItemTouchListener;

    public AdapterListMeal(List<MealDetail> dataMealSet, Context context) {
        this.dataMealSet = dataMealSet;
        this.context = context;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemMealBinding mealBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.item_meal, viewGroup, false);
        return new MealViewHolder(mealBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder mealViewHolder, int i) {
        mealViewHolder.mealBinding.setItemmealVM(dataMealSet.get(i));
    }

    @Override
    public int getItemCount() {
        return dataMealSet.size();
    }

    public void addAll(List<MealDetail> dataBind) {
        this.dataMealSet.addAll(dataBind);
        notifyDataSetChanged();
    }

    public void clearAll() {
        this.dataMealSet.clear();
        notifyDataSetChanged();
    }

    public MealDetail getItem(int position) {
        return dataMealSet.get(position);
    }

//    public boolean isEmpty() {
//        return getItemCount() < 1;
//    }
//
//    public void setRecyclerItemTouchListener(RecyclerItemTouchListener recyclerItemTouchListener) {
//        this.recyclerItemTouchListener = recyclerItemTouchListener;
//    }

    class MealViewHolder extends RecyclerView.ViewHolder {

        private final ItemMealBinding mealBinding;

        MealViewHolder(ItemMealBinding mealBinding) {
            super(mealBinding.getRoot());
            this.mealBinding = mealBinding;
        }
    }
}
