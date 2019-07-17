package com.example.demo1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author edge
 * @date 2019/6/12-13:17
 */
public class testrate {
    public static void main(String[] args) {
        Date date = new Date();
        dt();
        List<String> aa = Arrays.asList("aa", "bb");
        aa.forEach(System.out::print);

        String[] strs = new String[]{"张三", "李四", "王五"};
        for (String str : strs) {
            System.out.println(str);
        }

        String a = "gg";
        switch (a) {
            case "gg":
                System.out.println("case1");
                break;
            case "aa":
                System.out.println("case2");
                break;
            default:
                System.out.println("def");
        }


        System.out.println(getdaysub("2018-10-25", "2018-10-30"));
        System.out.println(getday("2018-10-25", -3));

    }

    public static void dt() {
        //12月计算，1年计算
        int mORy = 12;
        //20年
        int times = 15 * mORy;
        //年利率5%
        double rate = 1 + (0.05 / mORy);
        //年付
        double cash = 387;
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
        long add = 24 * 60 * 60 * 1000;
        long daya = date2.getTime() - date1.getTime();
        return daya / add;
    }
}
