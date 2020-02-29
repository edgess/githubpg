package com.example.tkm.test.entity;

import java.io.Serializable;

/**
 * (Demodepts)实体类
 *
 * @author edge
 * @since 2020-02-29 19:13:17
 */
public class Demodepts implements Serializable {
    private static final long serialVersionUID = 874210757652825045L;
    
    private Integer id;
    
    private String deptname;
    
    private String deptdesc;


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

    public String getDeptdesc() {
        return deptdesc;
    }

    public void setDeptdesc(String deptdesc) {
        this.deptdesc = deptdesc;
    }

}