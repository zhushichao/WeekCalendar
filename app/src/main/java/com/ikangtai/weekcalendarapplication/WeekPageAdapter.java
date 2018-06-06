package com.ikangtai.weekcalendarapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.joda.time.LocalDate;

/**
 * @author zsc
 * @date 2018/6/6
 * @Description
 */
public class WeekPageAdapter extends RecyclerView.Adapter<WeekPageAdapter.ItemHolder> {


    private TypedArray typedArray;
    private Context context;
    private LocalDate startDate;

    public WeekPageAdapter(TypedArray typedArray, Context context) {
        this.typedArray = typedArray;
        this.context = context;
        startDate = LocalDate.now();
    }

    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WeekView weekView = new WeekView(context, typedArray);
        return new ItemHolder(weekView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.weekView.setDayText(startDate.plusDays(position * 7));
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class ItemHolder extends RecyclerView.ViewHolder{

        WeekView weekView;
        public ItemHolder(View itemView) {
            super(itemView);
            weekView = (WeekView) itemView;
        }
    }
}
