package com.edge.dao.mapper;

import com.edge.entity.Utest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author edge
 * @date 2019/7/24-11:03
 */
@Repository
public interface UtestMapper {
    @Select("select * from utest limit 100")
    List<Utest> findall();

    @Insert("insert into utest (id, createtime) values (#{id}, #{createtime})")
    int insert(Utest utest);
}
