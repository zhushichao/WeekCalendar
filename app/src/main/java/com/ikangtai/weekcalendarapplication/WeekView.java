package com.ikangtai.weekcalendarapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
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
    private ArrayList<LocalDate> days = new ArrayList<>();

    private OnDayClickListener onDayClickListener;

    private LocalDate selectedDate;

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
            LocalDate date = days.get(i);
            if (date != null){
                if (selectedDate != null && selectedDate.getDayOfMonth() == date.getDayOfMonth() &&
                        selectedDate.getMonthOfYear() == date.getMonthOfYear()
                        && selectedDate.getYear() == date.getYear()){
                    txtPaint.setColor(selectedDayColor);
                } else {
                    txtPaint.setColor(dayColor);
                }
                canvas.drawText(String.valueOf(date.getDayOfMonth()), dis * (2 * i + 1), heigh - heigh / 4, txtPaint);
            }
        }
    }

    public void setDayText(LocalDate startDate, LocalDate selectedDate) {
        this.selectedDate = selectedDate;
        days.clear();
        for (int i = 0; i < 7; i++) {
            days.add(startDate.plusDays(i));
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("zsc", event.getAction() + "");
        if (event.getAction() == MotionEvent.ACTION_UP) {
            int index = (int) (event.getX() / (width / 7));
            if (index < days.size() && onDayClickListener != null){
                onDayClickListener.onClickDay(days.get(index));
            }
        }
        return true;
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

    public interface OnDayClickListener{
        void onClickDay(LocalDate localDate);
    }

    public void setOnDayClickListener(OnDayClickListener onDayClickListener){
        this.onDayClickListener = onDayClickListener;
    }


}
