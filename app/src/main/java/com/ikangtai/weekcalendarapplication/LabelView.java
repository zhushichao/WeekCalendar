package com.ikangtai.weekcalendarapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LabelView extends View {


    private final CharSequence[] labels;

    public LabelView(Context context, TypedArray typedArray) {
        super(context);

        labels = typedArray.getTextArray(R.styleable.WeekCalendarView_weekLabels);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (labels != null && labels.length == 7){

        }
    }
}
