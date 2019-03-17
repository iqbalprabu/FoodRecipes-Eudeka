package com.osg31.resepmakanan.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.osg31.resepmakanan.R;

public class ListMealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meal);
        //TODO (5) untuk menggunkan interface click recyvler view ditambahkan addOnItemTouchListener
        /*
         * Ex :
         * recylerView.addOnItemTouchListener(new RecyclerItemTouchListener (context,recyclerview,
         * new RecyclerItemTouchListener.onItemClickListener))
         * */
    }
}
