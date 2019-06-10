package com.example.fileserver;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartLoader implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("==================Parameter Configuration==================");
        System.out.println("java -jar fileserver.jar --fileserver.mode=SYS (D:\\smb) def");
        System.out.println("java -jar fileserver.jar --fileserver.mode=SMB (SMB:20.8)");
        System.out.println("java -jar fileserver.jar --fileserver.mode=FTP (FTP:10.30)");
        System.out.println("===============Interface Document Description===============");
        System.out.println("http://127.0.0.1:9090/swagger-ui.html");
        System.out.println("===========================================================");
    }
}