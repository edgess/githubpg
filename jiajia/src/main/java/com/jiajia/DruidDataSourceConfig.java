package com.jiajia;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DruidDataSourceConfig {

	@Bean
	public DataSource dataSource() {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl("jdbc:mysql://192.168.10.183/mybatis");
		datasource.setUsername("root");
		datasource.setPassword("qwesza");
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setTestOnBorrow(false);
		datasource.setTestWhileIdle(true);
		datasource.setValidationQuery("SELECT 1");
		return datasource;
	}
}
