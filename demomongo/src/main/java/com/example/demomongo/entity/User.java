package com.example.demomongo.entity;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class User {
    private Integer id;
    private String password;
    private String username;
    private Boolean locked = Boolean.FALSE;
    private Date expireddate;
    private Integer deptid;
}
