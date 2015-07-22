package com.ekvilan.exchangemarket.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class DateUtils {
    private final int DAY = 1000 * 60 * 60 * 24;
    private SimpleDateFormat todayFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat yesterdayFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public String createDate(String time) {
        return todayFormat.format(getTodayDate()) + " " + time;
    }

    private Date getTodayDate() {
        Calendar calendar = getCalendar(0, 0);
        return calendar.getTime();
    }

    public String getYesterdayDate(String date) {
        String[] split = date.split(" ");
        String[] splitTime = split[split.length - 1].split(":");

        Calendar calendar = getCalendar(Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));
        long yesterday = calendar.getTimeInMillis() - DAY;

        return yesterdayFormat.format(new Date(yesterday));
    }

    private Calendar getCalendar(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        return calendar;
    }
}
