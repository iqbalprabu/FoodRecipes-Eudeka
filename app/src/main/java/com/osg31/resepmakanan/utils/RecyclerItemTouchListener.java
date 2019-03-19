package com.osg31.resepmakanan.utils;
/*
 * Create by Alikhsan on 3/17/2019.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemTouchListener implements RecyclerView.OnItemTouchListener {

    public interface onItemClickListener {
        void onClickSingle(View view, int position);

        void onLongClick(View view, int position);
    }

    private GestureDetector gestureDetector;
    private onItemClickListener onItemClickListener;

    public RecyclerItemTouchListener(Context context, RecyclerView recyclerView, RecyclerItemTouchListener.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View xView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (xView != null && onItemClickListener != null) {
                    onItemClickListener.onLongClick(xView, recyclerView.getChildAdapterPosition(xView));
                }
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View xView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (xView != null && onItemClickListener != null && gestureDetector != null) {
            onItemClickListener.onClickSingle(xView, recyclerView.getChildAdapterPosition(xView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
