package com.edge.entity;
import java.time.LocalDate;
import java.util.Date;

public class Name2FastDFS {
    private Integer id;
    private String name;
    private String fdfs;
    private String ext;
    private Date date;

    @Override
    public String toString() {
        return "Name2FastDFS{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fdfs='" + fdfs + '\'' +
                ", ext='" + ext + '\'' +
                ", date=" + date +
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
        this.name = name;
    }

    public String getFdfs() {
        return fdfs;
    }

    public void setFdfs(String fdfs) {
        this.fdfs = fdfs;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
