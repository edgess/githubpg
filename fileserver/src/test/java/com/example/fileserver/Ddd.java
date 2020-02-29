package com.example.fileserver;

import java.io.Serializable;

public class Ddd implements Serializable {
    public static String abc = "free | awk '/Mem/{printf(\"%.2f%\"), $3/$2*100}'";
    public static void main(String[] args) {
        System.out.println("ddd");
        Eee eee = new Eee();
        eee.say();
        Eee.say();
        System.out.println(Eee.var2);
        Eee.sayvar();
        System.out.println(Eee.var2);
    }

}
