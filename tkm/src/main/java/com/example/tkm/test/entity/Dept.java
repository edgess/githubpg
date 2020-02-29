package com.example.tkm.test.entity;

import java.io.Serializable;

/**
 * (Dept)实体类
 *
 * @author makejava
 * @since 2020-02-27 12:58:54
 */
public class Dept implements Serializable {
    private static final long serialVersionUID = 806269393033457752L;
    
    private Integer id;
    
    private String deptname;
    
    private Integer pid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

}