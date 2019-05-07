package com.example.demomongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@ToString

//@Document(collection = "jiajia2")
public class Lab implements Cloneable {
    @Id
    private Integer id;
    private String tp;
    private String hd;
    //	private String abc;
    private Date createTime;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}