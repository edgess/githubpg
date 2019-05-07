package com.example.demo1.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;

    private String name;

    private Integer age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


}