package com.example.fileserver;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class Ccc {

    public static void main(String[] args) {
        String aa = "我的实际25";
        InputStream in0 = IOUtils.toInputStream(aa);
        File f1 = new File("ccc.txt");
        FileOutputStream fo = null;

        ObjectOutputStream oos =null;
        try {
            fo = new FileOutputStream(f1);

//            byte[] bytes1 = new byte[100];
//            int temp = 0;
//            while ((temp = in0.read(bytes)) != -1) {
//                fo.write(bytes, 0, temp);
//            }

            fo.write(aa.getBytes());
            fo.close();
            fo.close();

            oos = new ObjectOutputStream(new FileOutputStream("ddd.txt"));
            Ddd ddd = new Ddd();
            oos.writeObject(ddd);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
