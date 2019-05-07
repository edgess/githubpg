package com.example.demo9.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo9.entity.Lab;
import com.example.demo9.entity.Oil;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface LabMapper extends BaseMapper<Lab> {

    @Select("SELECT id,tp,hd,createtime FROM lab order by id desc limit 72")
    List<Lab> getOneDayLog();

    @Select("SELECT id,tp,hd,createtime FROM lab order by id desc limit 504")
    List<Lab> getOneWeekLog();

    @Select("SELECT id,tp,hd,createtime FROM lab order by id desc limit 2160")
    List<Lab> getOneMonthLog();

    @Select("SELECT id,tp,hd,createtime FROM lab order by id desc limit 1")
    Lab getlastone();

}