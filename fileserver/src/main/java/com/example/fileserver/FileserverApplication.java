package com.example.fileserver;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleIfStatement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class FileserverApplication {

    public static void main(String[] args)  {

//        InputStream in = FileserverApplication.class.getClassLoader().getResourceAsStream("application.properties");
//        Properties properties = new Properties();
//        properties.load(in);
//        if ((System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0)) {
//            properties.setProperty("fileserver.sys.defaultPath", properties.getProperty("fileserver.sys.win.defaultPath"));
//        } else {
//            properties.setProperty("fileserver.sys.defaultPath", properties.getProperty("fileserver.sys.uix.defaultPath"));
//        }

        SpringApplication.run(FileserverApplication.class, args);
    }

}
