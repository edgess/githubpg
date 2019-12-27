package com.edge.dao.mapper;

import com.edge.entity.Log2;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Log2Mapper {

    @Select("select * from log2 order by id DESC")
    List<Log2> findall();

    @Insert("insert into log2 (id, name, code, date,remoteaddr) values (#{id}, #{name}, #{code}, #{date},#{remoteaddr})")
    int insert(Log2 log2);
}