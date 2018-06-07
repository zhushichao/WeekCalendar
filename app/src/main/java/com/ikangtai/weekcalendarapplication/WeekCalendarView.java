package com.ikangtai.weekcalendarapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import org.joda.time.Days;
import org.joda.time.LocalDate;

/**
 * @author zsc
 * @date 2018/6/6
 * @Description
 */
public class WeekCalendarView extends ViewGroup {



    private Context context;
    private WeekPagerView pagerView;
    private ViewPager contentPager;
    private LocalDate min;

    public WeekCalendarView(Context context) {
        this(context, null);
    }

    public WeekCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);

    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WeekCalendarView);
        LabelView labelView = new LabelView(context, typedArray);
        addView(labelView);
        pagerView = new WeekPagerView(context, typedArray);
        setRangeDate(LocalDate.now().plusDays(-10), LocalDate.now().plusDays(20));
        addView(pagerView);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        int h = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            h += child.getMeasuredHeight();
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int top = 0;
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            childView.layout(l, top, r, top + childView.getMeasuredHeight());
            top += childView.getMeasuredHeight();
        }
    }


    public void setUpAdapter(ViewPager viewPager){
        this.contentPager = viewPager;
        contentPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagerView.setSelectedDate(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        pagerView.setOnClickDateListener(new OnClickDateListener() {
            @Override
            public void onClickDate(LocalDate localDate) {
                contentPager.setCurrentItem(Days.daysBetween(min, localDate).getDays());
            }
        });
    }

    public void setOnClickDateListener(OnClickDateListener listener){

        pagerView.setOnClickDateListener(listener);
    }

    public void setRangeDate(LocalDate minDate, LocalDate maxDate){
        min = minDate.plusDays(-minDate.getDayOfWeek() % 7);
        LocalDate max = maxDate.plusDays(6 - maxDate.getDayOfWeek() % 7);
        pagerView.setRangeDate(min, max);
    }


}
