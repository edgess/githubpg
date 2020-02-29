package com.example.tkm.test.entity;

import java.io.Serializable;

/**
 * (Oil)实体类
 *
 * @author makejava
 * @since 2020-02-27 13:01:46
 */
public class Oil implements Serializable {
    private static final long serialVersionUID = 897863271100050281L;
    
    private Integer id;
    
    private Integer mile;
    
    private Integer cash;
    
    private Object price;
    
    private Object date;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMile() {
        return mile;
    }

    public void setMile(Integer mile) {
        this.mile = mile;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Oil{" +
                "id=" + id +
                ", mile=" + mile +
                ", cash=" + cash +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}