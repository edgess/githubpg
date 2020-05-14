package com.example.tkm.cms.abc;

import java.util.Arrays;
import java.util.List;

public class Testabc {

    public static void main(String[] args) {

        String aa = "114,104,105,106,35,36,37,38,46,47,48,107,108,109,110,131,";
        List<String> bb = Arrays.asList(aa.split(","));
        System.out.println(bb);

        String[] arrString = bb.toArray(new String[bb.size()]);
        for (String s : arrString) {
            System.out.println(s);
        }

        String str = String.join(",", bb.toArray(new String[bb.size()]));
        System.out.println(str);
        System.out.println("----------------");
        System.out.println(bb.stream().count());
    }
}
