package com.example.fileserver;

import java.util.Random;
import java.util.UUID;

/**
 * @author edge
 * @date 2019/7/22-15:36
 */
public class Aaa {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("os.arch"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("java.vendor"));
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("java.vm.version"));
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(System.getProperty("java.library.path "));
        System.out.println(System.getProperty("java.io.tmpdir"));
        System.out.println(System.currentTimeMillis());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString().replaceAll("-", "").length());
    }
}
