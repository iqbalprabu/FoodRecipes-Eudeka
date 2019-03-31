package com.osg31.resepmakanan.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.osg31.resepmakanan.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchMealActivity extends AppCompatActivity {

    @BindView(R.id.ed_search_meal)
    EditText cari;
    @BindView(R.id.btn_search)
    Button ok;

    @BindView(R.id.btn_favorite)
    Button btn_favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_meal_layout);
        ButterKnife.bind(this);

        ok.setOnClickListener(v -> {
            Intent proses = new Intent(SearchMealActivity.this, ListMealActivity.class);
            proses.putExtra("data", cari.getText().toString());
            startActivity(proses);
        });

        btn_favorite.setOnClickListener(v -> {
            startActivity(new Intent(this, ListMealFavActivity.class));
        });
    }
}
