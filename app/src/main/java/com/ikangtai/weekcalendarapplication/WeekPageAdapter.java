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


    private TypedArray typedArray;
    private Context context;
    private LocalDate startDate;
    private LocalDate selectedDate;

    public WeekPageAdapter(TypedArray typedArray, Context context) {
        this.typedArray = typedArray;
        this.context = context;
        startDate = LocalDate.now();
    }

    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WeekView weekView = new WeekView(context, typedArray);
        return new ItemHolder(weekView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.weekView.setDayText(startDate.plusDays(position * 7), selectedDate);
    }

    @Override
    public int getItemCount() {
        return 20;
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
        notifyDataSetChanged();
    }
}
