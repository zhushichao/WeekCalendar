package com.ikangtai.weekcalendarapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * @author zsc
 * @date 2018/6/6
 * @Description
 */
public class WeekCalendarView extends ViewGroup {



    private Context context;

    public WeekCalendarView(Context context) {
        this(context, null);
    }

    public WeekCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WeekCalendarView);
        LabelView labelView = new LabelView(context, typedArray);
        addView(labelView);
        WeekPagerView pagerView = new WeekPagerView(context, typedArray);
        addView(pagerView);
    }

}
