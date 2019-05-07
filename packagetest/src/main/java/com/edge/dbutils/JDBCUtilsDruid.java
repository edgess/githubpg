package com.edge.dbutils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtilsDruid {
    public static void release(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static DataSource dataSource;

    // 初始化连接池（仅一次）
    static {

        // 创建连接池对象 使用工具类
        try {
            // 创建配置文件对象
            Properties properties = new Properties();
            //设置值
            properties.setProperty("driverClassName", "com.mysql.jdbc.Driver");
            properties.setProperty("url", "jdbc:mysql://192.168.10.183:3306/mybatis");
            properties.setProperty("username", "root");
            properties.setProperty("password", "qwesza");
            properties.setProperty("validationQuery", "SELECT 1");
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnC3() throws SQLException {
        return dataSource.getConnection();
    }

    public static DataSource getDataSource() throws SQLException {
        return dataSource;
    }
}
