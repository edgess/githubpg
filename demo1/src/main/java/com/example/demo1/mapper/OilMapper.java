package com.example.demo1.mapper;

import com.example.demo1.entity.Oil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OilMapper {
    @Select("select * from oil")
    List<Oil> getall();

    int insertOil(Oil oil);

    void insertOils(List<Oil> oils);

    @Select("select * from oil where id = #{id}")
    Oil queryById(int id);

}
