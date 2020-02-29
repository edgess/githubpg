package com.edge.daomy.entity;

import java.io.Serializable;

/**
 * (Demousers)实体类
 *
 * @author edge
 * @since 2020-02-29 21:17:44
 */
public class Demousers implements Serializable {
    private static final long serialVersionUID = -28081318624449425L;

    private Integer id;

    private String username;

    private String email;

    private String password;

    private Integer demodeptid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDemodeptid() {
        return demodeptid;
    }

    public void setDemodeptid(Integer demodeptid) {
        this.demodeptid = demodeptid;
    }

    @Override
    public String toString() {
        return "Demousers{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", demodeptid=" + demodeptid +
                '}';
    }
}