package com.example.demo9.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Oil {
    private Integer id;
    private Integer mile;
    private Integer cash;
    private Double price;
    //页面将数据传到后台，是以字符串的形式。所以时间格式会出错。加上此注解，后台可解析时间格式的字符串
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
