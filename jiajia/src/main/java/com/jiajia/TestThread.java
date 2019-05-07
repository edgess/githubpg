package com.jiajia;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThread {

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 2  ; i++) {
//            executorService.execute(new Canrun());
//        }


        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("111");
                        Thread.sleep(200);
                        System.out.println(index);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }

        fixedThreadPool.shutdown();


//        Canrun R1 = new Canrun();
//        Canrun R2 = new Canrun();
//        R1.start();
//        R2.start();
    }


}
