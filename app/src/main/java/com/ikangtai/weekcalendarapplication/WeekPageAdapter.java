package com.ikangtai.weekcalendarapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;

/**
 * @author zsc
 * @date 2018/6/6
 * @Description
 */
public class WeekPageAdapter extends RecyclerView.Adapter<WeekPageAdapter.ItemHolder> implements WeekView.OnDayClickListener {

    private OnClickDateListener listener;
    private TypedArray typedArray;
    private Context context;
    private LocalDate minDate;
    private LocalDate selectedDate;
    private int countDays;

    public WeekPageAdapter(TypedArray typedArray, Context context) {
        this.typedArray = typedArray;
        this.context = context;
    }

    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WeekView weekView = new WeekView(context, typedArray);
        return new ItemHolder(weekView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.weekView.setDayText(minDate.plusDays(position * 7), selectedDate);
    }

    @Override
    public int getItemCount() {
        return countDays;
    }




    static class ItemHolder extends RecyclerView.ViewHolder{

        WeekView weekView;
        public ItemHolder(View itemView, WeekView.OnDayClickListener onDayClickListener) {
            super(itemView);
            weekView = (WeekView) itemView;
            weekView.setOnDayClickListener(onDayClickListener);
        }
    }

    @Override
    public void onClickDay(LocalDate localDate) {
        Log.d("zsc", new SimpleDateFormat("yyyy-MM-dd HH:ss").format(localDate.toDate()));
        selectedDate = localDate;
        if (listener != null){
            listener.onClickDate(localDate);
        }
//        notifyDataSetChanged();
    }

    public void setOnClickDateListener(OnClickDateListener listener){
        this.listener = listener;
    }

    public void setSelectedDate(LocalDate localDate){
        this.selectedDate = localDate;
        notifyDataSetChanged();
    }

    public void setSelectedDate(int indexDay){
        this.setSelectedDate(minDate.plusDays(indexDay));
        notifyDataSetChanged();
    }

    public void setCount(int days, LocalDate min, LocalDate max) {
        this.countDays = days;
        this.minDate = min;
        notifyDataSetChanged();
    }

}
