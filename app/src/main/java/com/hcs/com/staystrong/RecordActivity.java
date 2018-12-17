package com.hcs.com.staystrong;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;

// 운동기록 Activity
public class RecordActivity extends Activity {

    private DBContactHelper dbContactHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        dbContactHelper = new DBContactHelper(this);

        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -2);

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 2);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .startDate(startDate.getTime())
                .endDate(endDate.getTime())
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Date date, int position) {
                refreshView(date);
            }
        });
    }

    private void refreshView(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String strYear = year + "";
        String strMonth;
        String strDay;

        if(month < 10) {
            strMonth = "0" + month;
        } else {
            strMonth = month + "";
        }

        if(day < 10) {
            strDay = "0" + day;
        } else {
            strDay = day + "";
        }

        List<String> list = dbContactHelper.get(strYear, strMonth, strDay);
        String[] arr = new String[list.size()];
        list.toArray(arr);
        ListView listview = findViewById(R.id.list);
        ContentsAdapter adapter = new ContentsAdapter(this, arr);
        listview.setAdapter(adapter);
    }
}
