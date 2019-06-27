package com.example.demomongo.mapper;

import com.example.demomongo.entity.Lab;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author edge
 * @date 2019/6/27-14:17
 */
@Mapper
public interface LabMapper {
    @Select("select * from lab")
    List<Lab> findall();
}
