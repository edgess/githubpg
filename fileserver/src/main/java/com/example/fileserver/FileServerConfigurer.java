package com.example.fileserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileServerConfigurer {
    @Bean
    @ConfigurationProperties(prefix = "connection")
    public ConnectionSettings connectionSettings() {
        return new ConnectionSettings();
    }

    @Value("${fileserver.mode}")
    private String mode;

    @Bean
    public FastDFSClient fastDFSClient() {
        String confUrl = Thread.currentThread().getContextClassLoader().getResource("fastdfs_client.conf").getPath();
        FastDFSClient fastDFSClient = null;

        try {
            fastDFSClient = new FastDFSClient(confUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fastDFSClient;
    }


    @Bean
    public SaveFile saveFile() {

        if (mode.equals("FTP")) {
            return new SaveFile2FTP();
        } else if (mode.equals("SMB")) {
            return new SaveFile2SMB();
        } else if (mode.equals("FDFS")) {
            return new SaveFile2FDFS();
        } else {
            return new SaveFile2SYS();
        }


//    @Value("${fileserver.ftpHost:}")
//    private String ftpHost;

//    @Bean
//    public SaveFile saveFile() {
//        if (!"".equals(ftpHost)) {
////            System.out.println("ftp");
//            return new SaveFile2FTP();
//        } else {
////            System.out.println("smb");
//            return new SaveFile2SMB();
//        }
//    }
    }
}

