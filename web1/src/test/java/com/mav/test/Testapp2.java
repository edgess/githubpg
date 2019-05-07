package com.mav.test;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")

public class Testapp2 {

//	@Autowired
//	private com.alibaba.druid.pool.DruidDataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void test2() throws ParseException {
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		// jdbcTemplate.setDataSource(dataSource);
		System.out.println(jdbcTemplate.queryForObject("select count(*) from ITequipment", Integer.class));

	}

}
