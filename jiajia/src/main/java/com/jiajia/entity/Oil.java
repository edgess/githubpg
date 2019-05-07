package com.jiajia.entity;


import lombok.Data;

import java.util.Date;

@Data
public class Oil {

    private Integer id;
    private Integer mile;
    private Integer cash;
    private double price;
    private Date date;

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
