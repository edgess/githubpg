package com.example.tkm.test.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Lab)实体类
 *
 * @author makejava
 * @since 2020-02-27 13:01:46
 */
public class Lab implements Serializable {
    private static final long serialVersionUID = 269634271539002966L;
    
    private Integer id;
    
    private String tp;
    
    private String hd;
    /**
    * 数据插入时间
    */
    private Date createtime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getHd() {
        return hd;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

}