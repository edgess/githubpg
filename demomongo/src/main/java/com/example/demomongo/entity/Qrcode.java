package com.example.demomongo.entity;

import java.util.Date;

public class Qrcode {
    private Integer id;
    private String name;
    private String code;
    private Date date;
    private String remoteaddr;

    @Override
    public String toString() {
        return "Qrcode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", date=" + date +
                ", remoteaddr='" + remoteaddr + '\'' +
                '}';
    }

    public String getRemoteaddr() {
        return remoteaddr;
    }

    public void setRemoteaddr(String remoteaddr) {
        this.remoteaddr = remoteaddr;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
