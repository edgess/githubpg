package com.example.demo9.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Lab {
	private Integer id;
	private String tp;
	private String hd;
	private Date createTime;
}