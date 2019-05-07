package com.jiajia;

public class Canrun extends Thread {
    public void run() {
        for (int j = 0; j < 10; j++) {
            System.out.println(Thread.currentThread() + "--" + j);

            
        }
    }
}
