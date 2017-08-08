package com.prolificinteractive.materialcalendarview.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.sample.decorators.EventDecorator;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Shows off the most basic usage
 */
public class IccTestActivity extends AppCompatActivity implements OnDateSelectedListener {
    @BindView(R.id.calendarView)
    MaterialCalendarView widget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        ButterKnife.bind(this);

        widget.setOnDateChangedListener(this);
        widget.setTopbarVisible(false);
        widget.setShowOtherDates(MaterialCalendarView.SHOW_NONE);

        Calendar instance = Calendar.getInstance();
        widget.setSelectedDate(instance.getTime());

        Calendar instance1 = Calendar.getInstance();
        instance1.set(instance1.get(Calendar.YEAR), Calendar.JANUARY, 1);

        Calendar instance2 = Calendar.getInstance();
        instance2.set(instance2.get(Calendar.YEAR), Calendar.DECEMBER, 31);

        widget.state().edit()
                .setMinimumDate(instance1.getTime())
                .setMaximumDate(instance2.getTime())
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        ArrayList<CalendarDay> dates = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            CalendarDay day = CalendarDay.from(calendar);
            dates.add(day);
            calendar.add(Calendar.DATE, 5);
        }

        widget.addDecorator(new EventDecorator(Color.RED, dates, 3, 0));
        widget.addDecorator(new EventDecorator(Color.BLUE, dates, 3, 1));
        widget.addDecorator(new EventDecorator(Color.GREEN, dates, 3, 2));
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        // Do nothing.
    }
}
