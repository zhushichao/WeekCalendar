package com.ikangtai.weekcalendarapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class WeekPagerView extends RecyclerView {

    private Context context;
    private OnClickDateListener listener;
    private WeekPageAdapter pageAdapter;
    private LinearLayoutManager layoutManager;
    private LocalDate minDate;
    private MyGes myGes;

    public WeekPagerView(Context context, TypedArray typedArray) {
        super(context);
        this.context = context;
        init(typedArray);
    }


    private void init(TypedArray typedArray) {
        layoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false);
        setLayoutManager(layoutManager);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(this);
        pageAdapter = new WeekPageAdapter(typedArray, context);
        setAdapter(pageAdapter);
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        myGes = new MyGes();

    }

    public void setOnClickDateListener(OnClickDateListener listener){
        pageAdapter.setOnClickDateListener(listener);
    }

    public void setSelectedDate(LocalDate localDate){
        int days = Days.daysBetween(minDate, localDate).getDays();
        pageAdapter.setSelectedDate(days);
    }

    public void setSelectedDate(int position){
        if (position / 7 != layoutManager.findFirstVisibleItemPosition()){
            scrollToPosition(position / 7);
        }
        pageAdapter.setSelectedDate(position);
    }

    public void setRangeDate(LocalDate minDate, LocalDate maxDate) {
        this.minDate = minDate;
        int days = Days.daysBetween(minDate, maxDate).getDays();
        pageAdapter.setCount(days, minDate, maxDate);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        new GestureDetector(context, myGes).onTouchEvent(e);
        return super.onTouchEvent(e);
    }

    class MyGes extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.e("aaaaaa", "手势滑动");
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
