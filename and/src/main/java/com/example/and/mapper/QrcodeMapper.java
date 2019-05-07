package com.example.and.mapper;

import com.example.and.entity.Qrcode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QrcodeMapper {

    @Select("select * from qrcode order by id DESC")
    List<Qrcode> findall();

    @Insert("insert into qrcode (id, name, code, date,remoteaddr) values (#{id}, #{name}, #{code}, #{date},#{remoteaddr})")
    int insert(Qrcode Qrcode);
}
