package com.ikangtai.weekcalendarapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class WeekPagerView extends RecyclerView {

    private Context context;

    public WeekPagerView(Context context, TypedArray typedArray) {
        super(context);
        this.context = context;
        init(typedArray);
    }


    private void init(TypedArray typedArray) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false);
        setLayoutManager(layoutManager);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(this);
        WeekPageAdapter pageAdapter = new WeekPageAdapter(typedArray, context);
        setAdapter(pageAdapter);
    }
}
