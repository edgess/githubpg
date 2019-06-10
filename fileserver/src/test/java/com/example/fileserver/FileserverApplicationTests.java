package com.example.fileserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileserverApplicationTests {
//    @Autowired
//    SaveFile2SYS saveFile2SYS;
//    @Autowired
//    SaveFile2SMB saveFile2SMB;

    @Test
    public void contextLoads() throws FileNotFoundException {
//		System.out.println(saveFile.existDirectory("/wwwroot/test"));
//		System.out.println(saveFile.existFile("/", "Shiro.pdf"));
//		System.out.println(saveFile2SMB.listDirectory("/"));

//		OutputStream os =new FileOutputStream("d:\\aa.txt") ;
//		saveFile2SYS.downloadFile("/ggg","sss.txt",os);


        ////测试上传
//        String filein = "D:\\Python36\\NEWS.txt";
//        InputStream is = new FileInputStream(filein);
//        saveFile2SYS.uploadFile("/ggg/", "bbb.txt", is);
    }

}
