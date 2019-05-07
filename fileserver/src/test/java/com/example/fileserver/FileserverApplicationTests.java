package com.example.fileserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileserverApplicationTests {
	@Autowired
	SaveFile saveFile;
	@Test
	public void contextLoads() {
//		System.out.println(saveFile.existDirectory("/wwwroot/test"));
		System.out.println(saveFile.existFile("/", "Shiro.pdf"));
	}

}
