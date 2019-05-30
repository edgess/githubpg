package com.example.demo1;

import com.sun.org.apache.bcel.internal.generic.IfInstruction;

/**
 * @author edge
 * @date 2019/5/27-14:43
 */
public class Testnum {
    public static void main(String[] args) {
        String a = "31010219801228441";
//        String a = "31022819800906362";
        int sum = 0;
        for (int i = 0; i < a.length(); i++) {
            System.out.println(a.charAt(i));
            sum = sum + Integer.parseInt(String.valueOf(a.charAt(i)));
        }
        System.out.println(sum);


        String str2 = new String();
        str2 = "A/B/C";
        String s[] = str2.split("/");
        System.out.println("s[0] = " + s[0]);
        //return"A"
        for (String ss : s) {
            System.out.print(ss + " ");
        }
    }
}
