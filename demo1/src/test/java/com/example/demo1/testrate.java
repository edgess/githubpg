package com.example.demo1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author edge
 * @date 2019/6/12-13:17
 */
public class testrate {
    public static void main(String[] args) {
        //12月计算，1年计算
        int mORy = 12;
        //20年
        int times = 20 * mORy;
        //年利率5%
        double rate = 1 + (0.05 / mORy);
        //年付
        double cash = 3336 / mORy;
        double sumcash = 0;
        double sumrate = 0;
        List<Double> ratelist = new ArrayList<>();
        for (int i = 1; i < times + 1; i++) {
            System.out.println(i);
            double y = Math.pow(rate, i);
            System.out.println(y);
            ratelist.add(y);
        }
        System.out.println(ratelist);
        for (int i = 0; i < ratelist.size(); i++) {
            System.out.println(i);
            System.out.println(ratelist.get(i));
            sumcash = sumcash + cash * ratelist.get(i);
            System.out.println(sumcash);
        }

        System.out.println(getdaysub("2018-10-25", "2018-10-30"));
        System.out.println(getday("2018-10-25", -3));
    }

    public static Date getday(String d1, long day) {
        long add = day * 24 * 60 * 60 * 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        try {
            date1 = sdf.parse(d1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long daya = date1.getTime() + add;
        return new Date(daya);
    }

    public static long getdaysub(String d1, String d2) {
        String dateStr = "2018-10-29";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        Date date2 = new Date();
        try {
            date1 = sdf.parse(d1);
            date2 = sdf.parse(d2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date1.getTime());
        System.out.println(date2.getTime());
        long add = 24 * 60 * 60 * 1000;
        long daya = date2.getTime() - date1.getTime();
        return daya / add;
    }
}
