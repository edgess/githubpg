package com.edge.dbutils;


import com.edge.entity.User;

import java.util.List;

public interface UserDAO {

    public List<User> getAll();

    public void save(User user);

    public User get(Integer id);

    public void delete(Integer id);
}
