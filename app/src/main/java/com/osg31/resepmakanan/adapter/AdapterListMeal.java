package com.osg31.resepmakanan.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.osg31.resepmakanan.R;
import com.osg31.resepmakanan.databinding.ItemMealBinding;
import com.osg31.resepmakanan.model.MealDetail;
import com.osg31.resepmakanan.view.MealDetailActivity;

import java.util.List;

public class AdapterListMeal extends RecyclerView.Adapter<AdapterListMeal.MealViewHolder> {

    private List<MealDetail> dataMealSet;
    private Context context;

//    //TODO (1)initialize constructor adapter on one time
//    public AdapterListMeal(Context context) {
//        this.context = context;
//        this.dataMealSet = new ArrayList<>();
//    }

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
        mealViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idMeal = dataMealSet.get(i).idMeal;
                Intent detailMeal = new Intent(context, MealDetailActivity.class);
                detailMeal.putExtra(MealDetailActivity.DETAIL, idMeal);
                context.startActivity(detailMeal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataMealSet.size();
    }

    //TODO (2) load data from json to adapter
    public void addAll(List<MealDetail> dataBind) {
        this.dataMealSet.addAll(dataBind);
        notifyDataSetChanged();
    }

    //TODO (3) clear data on adapter
    public void clearAll() {
        this.dataMealSet.clear();
        notifyDataSetChanged();
    }

    //TODO (4) getting object adapter with position after user clicked
    public MealDetail getItem(int position) {
        return dataMealSet.get(position);
    }

    public boolean isEmpty() {
        return getItemCount() < 1;
    }

    class MealViewHolder extends RecyclerView.ViewHolder {

        private final ItemMealBinding mealBinding;

        MealViewHolder(ItemMealBinding mealBinding) {
            super(mealBinding.getRoot());
            this.mealBinding = mealBinding;
        }
    }
}
