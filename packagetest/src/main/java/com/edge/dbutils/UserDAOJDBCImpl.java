package com.edge.dbutils;

import com.edge.entity.User;

import java.util.List;

public class UserDAOJDBCImpl extends DAO<User> implements UserDAO {

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM user";
        return getForList(sql);
    }

    @Override
    public void save(User user) {

    }

    @Override
    public User get(Integer id) {
        String sql = "SELECT * FROM user where id=?";
        return get(sql, id);
    }


    @Override
    public void delete(Integer id) {
        String sql = "delete from user where id=?";
        update(sql, id);
    }

    public Integer aa() {
        return Integer.valueOf(test());
    }
}
