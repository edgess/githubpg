package com.example.demo;

import com.alibaba.fastjson.JSONArray;

import java.util.Arrays;
import java.util.List;

/**
 * @author edge
 * @date 2018/12/18-13:44
 */
public class Testlambda2 {
    public static void main(String[] args) {
        List<String> languages = Arrays.asList("Scala", "Python", "Java");
        languages.forEach(x -> System.out.println(x));
        Arrays.asList("aaa", "bbb", "ccc").forEach(x -> System.out.println(x));
        for (String s : languages) {
            System.out.println(s);
        }

    }
}
