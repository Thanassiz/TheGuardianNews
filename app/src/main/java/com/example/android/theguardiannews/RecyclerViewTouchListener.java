package com.example.android.theguardiannews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Thanassis on 4/4/2018.
 * An OnItemTouchListener allows the application to intercept touch events in progress at the view hierarchy level
 * of the RecyclerView before those touch events are considered for RecyclerView's own scrolling behavior.
 */

public class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {

    /** Class tag name */
    private static final String TAG = RecyclerViewTouchListener.class.getSimpleName();

    /** GestureDetector to intercept touch events */
    private GestureDetector gestureDetector;
    private RecyclerViewItemClickListener clickListener;

    /**
     * Public Constructor, creates a new {@link RecyclerViewTouchListener} object.
     */
    public RecyclerViewTouchListener(Context context, final RecyclerView recyclerView, final RecyclerViewItemClickListener clickListener) {
        this.clickListener = clickListener;
        this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                // Find the long pressed view
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if(child != null && clickListener != null){
                    clickListener.onLongClick(child, recyclerView.getChildLayoutPosition(child));
                    Log.e(TAG, "Long Click, Child's ID: " + child.getId());
                }
            }
        });
    }

    /**
     * Take over touch events sent to the RecyclerView before they are handled by either the RecyclerView itself or its child views.
     *
     *  Returns true if this OnItemTouchListener wishes to begin intercepting touch events,
     *  false to continue with the current behavior and continue observing future events in the gesture.
     */
    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent e) {
        View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
        if(child != null && clickListener != null && gestureDetector.onTouchEvent(e)){
            clickListener.onClick(child, recyclerView.getChildLayoutPosition(child));
            child.getParent().requestDisallowInterceptTouchEvent(false);
            Log.e(TAG, "Simple Click, Child's ID: " + child.getId());
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

}
