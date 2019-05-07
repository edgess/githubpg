package com.example.fileserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileServerConfigurer {


//    @Value("${fileserver.mode}")
//    private String mode;

    @Value("${fileserver.ftpHost:}")
    private String ftpHost;

    @Bean
    public SaveFile saveFile() {
        if (!"".equals(ftpHost)) {
//            System.out.println("ftp");
            return new SaveFile2FTP();
        } else {
//            System.out.println("smb");
            return new SaveFile2SMB();
        }
    }

}
