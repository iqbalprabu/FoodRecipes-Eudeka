package com.osg31.resepmakanan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.osg31.resepmakanan.R;
import com.osg31.resepmakanan.model.MealDetail;

import java.util.List;

public class AdapterListMeal extends RecyclerView.Adapter<AdapterListMeal.MealViewHolder> {

    List<MealDetail> list;
    Context context;

    public AdapterListMeal(List<MealDetail> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_meal, viewGroup, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder mealViewHolder, int i) {
        MealDetail mealDetail = list.get(i);
        if (mealDetail != null) {
            mealDetail.loadImage(mealViewHolder.ivListMeal, mealDetail.strMealThumb);
            mealViewHolder.tvTitleMeal.setText(mealDetail.strMeal);
            mealViewHolder.tvDescMeal.setText(mealDetail.strMeal);
        } else {
            Toast.makeText(context, "Check Your Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MealViewHolder extends RecyclerView.ViewHolder {
        ImageView ivListMeal;
        TextView tvTitleMeal, tvDescMeal;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            ivListMeal = itemView.findViewById(R.id.iv_list_meal);
            tvTitleMeal = itemView.findViewById(R.id.tv_title_meal);
            tvDescMeal = itemView.findViewById(R.id.tv_description_meal);
        }
    }
}
