package com.osg31.resepmakanan;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;

public class Search_meal extends AppCompatActivity {

    @BindView(R.id.ed_search_meal)
    EditText cari;
    @BindView(R.id.btn_search)
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_meal_layout);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent proses= new Intent(Search_meal.this, List_meal.class);
                proses.putExtra("data",cari.getText().toString());
                startActivity(proses);
            }
        });

    }
}
