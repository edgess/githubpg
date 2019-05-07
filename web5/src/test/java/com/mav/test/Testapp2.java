package com.mav.test;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = {"classpath:serviceContext.xml","classpath:applicationContext.xml" })
@ContextConfiguration(locations = "classpath:applicationContext.xml")

public class Testapp2 {

	@Autowired
	private com.alibaba.druid.pool.DruidDataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
//	@Autowired
//	@Qualifier("jdbcTemplate_service")
//	private JdbcTemplate jdbcTemplate_service;


	@Test
	public void test2() throws ParseException {
		// JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		// jdbcTemplate.setDataSource(dataSource);
		System.out.println(jdbcTemplate.queryForObject("select count(*) from itequipment", Integer.class));
//		System.out.println(jdbcTemplate_service.queryForObject("select count(*) from orders", Integer.class));

	}

}
