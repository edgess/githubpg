package com.example.demomongo.mapper;


import com.example.demomongo.entity.Qrcode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QrcodeMapper {

    @Select("select * from qrcode order by id DESC")
    List<Qrcode> findall();

    @Insert("insert into qrcode (id, name, code, date,remoteaddr) values (#{id}, #{name}, #{code}, #{date},#{remoteaddr})")
    int insert(Qrcode Qrcode);
}
