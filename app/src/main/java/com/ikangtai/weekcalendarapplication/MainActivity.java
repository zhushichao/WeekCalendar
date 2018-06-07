package com.ikangtai.weekcalendarapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeekCalendarView weekCalendarView = findViewById(R.id.week_calendar);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyAdapter());
        weekCalendarView.setUpAdapter(viewPager);

    }


    private class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 200;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            TextView textView = new TextView(getApplicationContext());
            textView.setText(position + "");
            container.addView(textView);
            return textView;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object
                object) {
            container.removeView((View)object);
        }
    }
}
