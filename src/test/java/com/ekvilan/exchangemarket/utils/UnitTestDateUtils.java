package com.ekvilan.exchangemarket.utils;


import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class UnitTestDateUtils {
    private DateUtils dateUtils = new DateUtils();

    @Test
    public void testCreateDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = "10:56";
        String date = sdf.format(new Date());

        assertEquals(date + " " + time, dateUtils.createDate(time));
    }

    @Test
    public void testGetYesterdayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 45);
        String date = sdf.format(calendar.getTime());

        System.out.println(dateUtils.getYesterdayDate(date));
    }
}
