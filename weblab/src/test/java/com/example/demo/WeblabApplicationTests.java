package com.example.demo;
import java.util.Date;

import java.util.List;

import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageHelper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeblabApplicationTests {
	@Autowired
	private LabMapper labMapper;
//	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void getlastone() {
		PageHelper.startPage(1, 10);
		List<Log> logs = labMapper.getallbak();
		PageInfo<Log> pageInfo = new PageInfo<>(logs);
		System.out.println(logs.size());
		logs.forEach(x -> System.out.println(x));
		System.out.println(pageInfo);


	}
}