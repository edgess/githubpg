package com.example.demo5;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name = "oil")
@Entity
public class Oil {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private long mile;

	private int cash;

	private double price;

	@Temporal(TemporalType.DATE)
	// @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getMile() {
		return mile;
	}

	public void setMile(long mile) {
		this.mile = mile;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Oil [id=" + id + ", mile=" + mile + ", cash=" + cash + ", price=" + price + ", date=" + date + "]";
	}

	public Oil(long mile, int cash, double price, Date date) {
		super();
		this.mile = mile;
		this.cash = cash;
		this.price = price;
		this.date = date;
	}

	public Oil() {
	}

}
