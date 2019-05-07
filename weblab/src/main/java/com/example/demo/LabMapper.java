package com.example.demo;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface LabMapper {

    @Select("SELECT id,tp,hd,createtime FROM lab order by id desc limit 72")
    List<Lab> getOneDayLog();

    @Select("SELECT id,tp,hd,createtime FROM lab order by id desc limit 504")
    List<Lab> getOneWeekLog();

    @Select("SELECT id,tp,hd,createtime FROM lab order by id desc limit 2160")
    List<Lab> getOneMonthLog();

    @Select("SELECT id,tp,hd,createtime FROM lab order by id desc limit 1")
    Lab getlastone();

    @Select("select * from log ORDER BY date desc limit 60")
    List<Log> getbak();

    @Select("select * from log ORDER BY date desc")
    List<Log> getallbak();

    @Select("select * from oil where id > #{gid} ORDER BY id desc")
    List<Oil> getalloil(Integer gid);

    @Delete("delete from oil where id = #{id}")
    int deloilbyid(Integer id);

    @Insert("insert into oil (MILE,CASH,PRICE,DATE) values (#{mile}, #{cash}, #{price}, #{date})")
    int insertOil(Oil oil);

    @Update("update oil set MILE=#{mile},CASH=#{cash},PRICE=#{price},DATE=#{date} where id = #{id}")
    int updateoilbyid(Oil oil);

}