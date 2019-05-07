package com.jiajia.mapper;


import com.jiajia.entity.Oil;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
//@Mapper
@Repository
public interface OilMapper {
    @Select("select * from oil")
    List<Oil> getall();

    int insertOil(Oil oil);

    void insertOils(List<Oil> oils);

    @Select("select * from oil where id = #{id}")
    Oil queryById(int id);

}
