package com.edge.entity;

import java.util.Date;

public class Utest {
    private String id;
    private Date createtime;

    @Override
    public String toString() {
        return "Utest{" +
                "id='" + id + '\'' +
                ", createtime=" + createtime +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
