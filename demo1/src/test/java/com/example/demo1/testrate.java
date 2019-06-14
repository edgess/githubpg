package com.example.demo1;

import java.util.*;

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
    }
}
