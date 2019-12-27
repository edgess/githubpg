package com.edge.dao.mapper;

import com.edge.entity.Name2FastDFS;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface Name2FastDFSMapper {

    @Select("select * from name2FastDFS order by id DESC")
    Set<Name2FastDFS> findall();

    @Insert("insert into name2FastDFS (id, name, fdfs, date, ext) values (#{id}, #{name}, #{fdfs}, #{date},#{ext})")
    int insert(Name2FastDFS name2FastDFS);

    @Select("select * from name2FastDFS where fdfs = #{fdfs} order by date DESC LIMIT 1")
    Name2FastDFS findNamebyFdfs(Name2FastDFS name2FastDFS);

    @Select("select * from name2FastDFS where name = #{name} order by date DESC LIMIT 1")
    Name2FastDFS findFdfsbyName(Name2FastDFS name2FastDFS);
}