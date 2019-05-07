package com.example.demo;

import java.util.Date;
import lombok.Data;

@Data
public class Log {
	private String host;
	private Integer file;
	private Integer md5;
	private String mdstr;
	private Date date;

	// public String getHost() {
	// return host;
	// }
	//
	// public void setHost(String host) {
	// this.host = host;
	// }
	//
	// public Integer getFile() {
	// return file;
	// }
	//
	// public void setFile(Integer file) {
	// this.file = file;
	// }
	//
	// public Integer getMd5() {
	// return md5;
	// }
	//
	// public void setMd5(Integer md5) {
	// this.md5 = md5;
	// }
	//
	// public String getMdstr() {
	// return mdstr;
	// }
	//
	// public void setMdstr(String mdstr) {
	// this.mdstr = mdstr;
	// }
	//
	// public Date getDate() {
	// return date;
	// }
	//
	// public void setDate(Date date) {
	// this.date = date;
	// }
	//
	// @Override
	// public String toString() {
	// return "Log [host=" + host + ", file=" + file + ", md5=" + md5 + ", mdstr=" +
	// mdstr + ", date=" + date + "]";
	// }

}