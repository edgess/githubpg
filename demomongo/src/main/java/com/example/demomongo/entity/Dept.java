package com.example.demomongo.entity;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class Dept {
    private Integer id;
    private String deptname;
    private Integer pid;
    private List<Dept> children = new ArrayList<>();

}
