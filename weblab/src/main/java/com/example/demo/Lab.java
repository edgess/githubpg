package com.example.demo;

import java.util.Date;

import lombok.Data;

@Data
public class Lab {
	private Integer id;
	private String tp;
	private String hd;
	private Date createTime;

	// public Integer getId() {
	// return id;
	// }
	//
	// public void setId(Integer id) {
	// this.id = id;
	// }
	//
	// public String getTp() {
	// return tp;
	// }
	//
	// public void setTp(String tp) {
	// this.tp = tp;
	// }
	//
	// public String getHd() {
	// return hd;
	// }
	//
	// public void setHd(String hd) {
	// this.hd = hd;
	// }
	//
	// public Date getCreateTime() {
	// return createTime;
	// }
	//
	// public void setCreateTime(Date createTime) {
	// this.createTime = createTime;
	// }
	//
	// @Override
	// public String toString() {
	// return "Lab [id=" + id + ", tp=" + tp + ", hd=" + hd + ", createTime=" +
	// createTime + "]";
	// }

}