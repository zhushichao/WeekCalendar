package com.ikangtai.weekcalendarapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import org.joda.time.LocalDate;

import java.util.ArrayList;

/**
 * @author zsc
 * @date 2018/6/6
 * @Description
 */
public class WeekView extends View {

    private Context context;

    private TextPaint txtPaint = new TextPaint();
    private Paint bgPaint = new Paint();
    private int width;
    private int heigh;
    private int daySize;
    private int dayColorRes;
    private int dayColor;
    private int selectedDayColorRes;
    private int selectedDayColor;
    private ArrayList<String> days = new ArrayList<>();

    public WeekView(Context context, TypedArray typedArray) {
        super(context);
        setWH();
        initAttrs(typedArray);

        txtPaint.setStrokeWidth(2);
        txtPaint.setColor(dayColor);
        txtPaint.setTextSize(daySize);

        bgPaint.setColor(selectedDayColor);
        bgPaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(heigh, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float dis = width / 14;
        for (int i = 0; i < days.size(); i++) {
            String day = days.get(i);
            if (day != null) {
                canvas.drawText(day, dis * (2 * i + 1), heigh - heigh / 4, txtPaint);
            }
        }
    }

    public void setDayText(LocalDate localDate) {
        days.clear();
        for (int i = 0; i < 7; i++) {
            int dayOfMonth = localDate.plusDays(i).getDayOfMonth();
            days.add(String.valueOf(dayOfMonth));
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            float x = event.getX();
            float v = x / (width / 7);




            return true;
        }
        return super.onTouchEvent(event);
    }

    private void setWH() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        heigh = (int) (metrics.density * 45 + 0.5);
    }

    private void initAttrs(TypedArray typedArray) {
        daySize = typedArray.getDimensionPixelSize(R.styleable
                .WeekCalendarView_weekTextSize, 0);
        dayColorRes = typedArray.getResourceId(R.styleable.WeekCalendarView_weekTextColor, 0);
        dayColor = typedArray.getColor(R.styleable.WeekCalendarView_weekTextColor, Color.BLACK);
        selectedDayColorRes = typedArray.getResourceId(R.styleable
                .WeekCalendarView_weekSelectedTextColor, 0);
        selectedDayColor = typedArray.getColor(R.styleable
                .WeekCalendarView_weekSelectedTextColor, Color.BLUE);
    }


}
