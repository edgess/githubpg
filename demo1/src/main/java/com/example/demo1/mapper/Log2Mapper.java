package com.example.demo1.mapper;

import com.example.demo1.entity.Log2;
import com.example.demo1.entity.Log2Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Log2Mapper {
    int countByExample(Log2Example example);

    int deleteByExample(Log2Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(Log2 record);

    int insertSelective(Log2 record);

    List<Log2> selectByExample(Log2Example example);

    Log2 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Log2 record, @Param("example") Log2Example example);

    int updateByExample(@Param("record") Log2 record, @Param("example") Log2Example example);

    int updateByPrimaryKeySelective(Log2 record);

    int updateByPrimaryKey(Log2 record);
}