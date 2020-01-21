package com.example.fileserver;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.stream.Collectors;

public class Bbb {

    public static void main(String[] args) {

//        String confUrl = Thread.currentThread().getContextClassLoader().getResource("fastdfs_client.conf").getPath();
//        FastDFSClient fastDFSClient = new FastDFSClient(confUrl);

//        FileInfo file=fastDFSClient.getFile("group1","M00/00/00/wKgKuF4EX2eAZ6beAADkW_3yAvw960.jpg");
//        System.out.println("获取文件信息成功："+file.getFileSize());

//        String  filePath= fastDFSClient.uploadFile("C:\\Users\\Admini\\Desktop\\tw.jpg");
//        System.out.println("返回路径："+filePath);

//        FileSystemView fsv = FileSystemView.getFileSystemView();
//        File com = fsv.getHomeDirectory(); //读取桌面路径
//        int downFlag = fastDFSClient.download_file("group1/M00/00/00/wKgKuF4DES6AOoitAAQ5sg9aABU945.jpg", new BufferedOutputStream(new FileOutputStream(com.getPath() + "\\aedgea.jpg")));
//        System.out.println("下载结果为：" + (downFlag == 0 ? "下载文件成功" : "下载文件失败"));

//        int flag=fastDFSClient.delete_file("group1/M00/00/00/wKgKuF4EX2eAZ6beAADkW_3yAvw960.jpg");
//        System.out.println("删除结果：" +(flag==0?"删除成功":"删除失败"));

        InputStream in1 = FileserverApplication.class.getClassLoader().getResourceAsStream("fastdfs_client.conf");
        String result = new BufferedReader(new InputStreamReader(in1)).lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println("result1---" + result);

        InputStream in2 = FileserverApplication.class.getClassLoader().getResourceAsStream("fastdfs_client.conf");
        byte[] bytes = new byte[0];
        try {
            bytes = new byte[in2.available()];
            in2.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result2 = new String(bytes);
        System.out.println("result2---" + result2);

        InputStream in3 = FileserverApplication.class.getClassLoader().getResourceAsStream("fastdfs_client.conf");
        StringBuilder result3 = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(in3));
        try {
            while ((line = br.readLine()) != null) {
                result3.append(line);
                result3.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("result3---" + result3);

        InputStream in4 = FileserverApplication.class.getClassLoader().getResourceAsStream("fastdfs_client.conf");
        ByteArrayOutputStream result4 = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = in4.read(buffer)) != -1) {
                result4.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("result4---" + result4);


        InputStream in5 = FileserverApplication.class.getClassLoader().getResourceAsStream("fastdfs_client.conf");
        BufferedInputStream bis = new BufferedInputStream(in5);
        ByteArrayOutputStream result5 = new ByteArrayOutputStream();
        int i = 0;
        try {
            i = bis.read();
            while (i != -1) {
                result5.write((byte) i);
                i = bis.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("result5---" + result5);


        InputStream in6 = FileserverApplication.class.getClassLoader().getResourceAsStream("fastdfs_client.conf");
        try {
            System.out.println("result6---" + IOUtils.toString(in6));
        } catch (IOException e) {
            e.printStackTrace();
        }


        String aa = "我的实际23";
        InputStream in0 = IOUtils.toInputStream(aa);
        File f1 = new File("d:\\java.txt");
        FileOutputStream fo = null;

        try {
            fo = new FileOutputStream(f1);

//            byte[] bytes1 = new byte[100];
//            int temp = 0;
//            while ((temp = in0.read(bytes)) != -1) {
//                fo.write(bytes, 0, temp);
//            }

            fo.write(aa.getBytes());

            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

