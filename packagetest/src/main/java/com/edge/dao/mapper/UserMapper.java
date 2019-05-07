package com.edge.dao.mapper;


import com.edge.entity.User;
import com.edge.entity.UserExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    int deleteByExample(UserExample example);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKey(User record);

    int countByExample(UserExample example);

    //need
    User selectByPrimaryKey(Integer id);

    //need
    List<User> selectByExample(UserExample example);

    //need
    int insert(User record);

    //need
    int deleteByPrimaryKey(Integer id);

    //need
    int insertSelective(User record);

    //need
    int updateByPrimaryKeySelective(User record);
}
