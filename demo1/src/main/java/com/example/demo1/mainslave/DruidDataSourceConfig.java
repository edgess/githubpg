package com.example.demo1.mainslave;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

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

    @Bean
    public DataSource slavedataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl("jdbc:mysql://192.168.10.186/mybatis");
        datasource.setUsername("root");
        datasource.setPassword("qwesza");
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        datasource.setTestOnBorrow(false);
        datasource.setTestWhileIdle(true);
        datasource.setValidationQuery("SELECT 1");
        return datasource;
    }

    @Bean(name = "multiDataSource")
    public MultiRouteDataSource exampleRouteDataSource() {
        MultiRouteDataSource multiDataSource = new MultiRouteDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("master", dataSource());
        targetDataSources.put("first", slavedataSource());
        multiDataSource.setTargetDataSources(targetDataSources);
        multiDataSource.setDefaultTargetDataSource(dataSource());
        return multiDataSource;
    }
}
