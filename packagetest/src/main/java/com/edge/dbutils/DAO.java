package com.edge.dbutils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.jdbc.core.JdbcTemplate;


public class DAO<T> {

    private QueryRunner queryRunner = null;
    private Class<T> type;


    public DAO() {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            Type[] types = parameterizedType.getActualTypeArguments();
            if (types != null && types.length > 0) {
                if (types[0] instanceof Type) {
                    type = (Class<T>) types[0];
                }
            }
        }
        queryRunner = new QueryRunner();
    }


    public <E> E getForValue(String sql, Object... args) {
        Connection connection = null;
        try {
            connection = JDBCUtilsDruid.getConnC3();
            return (E) queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtilsDruid.release(null, null, connection);
        }
        return null;
    }

    public List<T> getForList(String sql, Object... args) {
        Connection connection = null;
        try {
            connection = JDBCUtilsDruid.getConnC3();
            return queryRunner.query(connection, sql, new BeanListHandler<>(type), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtilsDruid.release(null, null, connection);
        }
        return null;
    }

    public T get(String sql, Object... args) {
        Connection connection = null;
        try {
            connection = JDBCUtilsDruid.getConnC3();
            return queryRunner.query(connection, sql, new BeanHandler<>(type), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtilsDruid.release(null, null, connection);
        }
        return null;
    }

    public void batch(String sql, Object[]... args) {
        Connection connection = null;
        try {
            connection = JDBCUtilsDruid.getConnC3();
            queryRunner.batch(connection, sql, args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtilsDruid.release(null, null, connection);
        }
    }

    public void update(String sql, Object... args) {
        Connection connection = null;
        try {
            connection = JDBCUtilsDruid.getConnC3();
            queryRunner.update(connection, sql, args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtilsDruid.release(null, null, connection);
        }
    }

    public Integer test() {
        Connection connection = null;
        try {
            connection = JDBCUtilsDruid.getConnC3();

            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtilsDruid.getDataSource());

            return jdbcTemplate.queryForObject("select count(*) from user", Integer.class);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtilsDruid.release(null, null, connection);
        }
        return 0;
    }
}
