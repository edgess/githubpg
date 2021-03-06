package com.example.demo1.entity;

import java.util.Date;

public class Log2 {
    private Integer id;

    private String name;

    private String code;

    private Date date;

    private String remoteaddr;

    @Override
    public String toString() {
        return "Log2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", date=" + date +
                ", remoteaddr='" + remoteaddr + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemoteaddr() {
        return remoteaddr;
    }

    public void setRemoteaddr(String remoteaddr) {
        this.remoteaddr = remoteaddr == null ? null : remoteaddr.trim();
    }
}