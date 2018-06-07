package com.ikangtai.weekcalendarapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.view.View;

public class LabelView extends View {


    private final CharSequence[] labels;

    public LabelView(Context context, TypedArray typedArray) {
        super(context);

        labels = typedArray.getTextArray(R.styleable.WeekCalendarView_weekLabels);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(70, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (labels != null && labels.length == 7){

        }
    }
}
