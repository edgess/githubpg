package com.mav.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItMapper {
	List<Map<Object, Object>> getoneByEquipNO(@Param("status") String a, @Param("type") String b,
			@Param("belong") String c, @Param("equip_no") String d, @Param("username") String e,
			@Param("dept_id") String f, @Param("sn") String g, @Param("pgstr") Integer h, @Param("pgsiz") Integer i); 
	
	List<Map<Object, Object>> getAllItWithMap(@Param("status") String a, @Param("type") String b,
			@Param("belong") String c, @Param("equip_no") String d, @Param("username") String e,
			@Param("dept_id") String f, @Param("sn") String g, @Param("pgstr") Integer h, @Param("pgsiz") Integer i);

	List<Map<Object, Object>> getMangerItWithMap(@Param("status") String a, @Param("type") String b,
			@Param("belong") String c, @Param("equip_no") String d, @Param("username") String e,
			@Param("dept_id") String f, @Param("sn") String g, @Param("pgstr") Integer h, @Param("pgsiz") Integer i);

	Integer getAllItWithMapCount(@Param("status") String a, @Param("type") String b, @Param("belong") String c,
			@Param("equip_no") String d, @Param("username") String e, @Param("dept_id") String f,
			@Param("sn") String g);

	Integer getMangerItWithMapCount(@Param("status") String a, @Param("type") String b, @Param("belong") String c,
			@Param("equip_no") String d, @Param("username") String e, @Param("dept_id") String f,
			@Param("sn") String g);

	List<Map<Object, Object>> getItById(@Param("id") Integer id);

	void updateByPrimaryKeySelective(@Param("status") String a, @Param("type") String b, @Param("belong") String c,
			@Param("equip_no") String d, @Param("username") String e, @Param("dept_id") String f, @Param("sn") String g,
			@Param("model") String h, @Param("price") Integer i, @Param("memo") String j, @Param("startuse") Date k,
			@Param("id") Integer l);

	void insertSelective(@Param("status") String status, @Param("type") String type, @Param("belong") String belong,
			@Param("equip_no") String equip_no, @Param("username") String username, @Param("dept_id") String dept_id, @Param("sn") String sn,
			@Param("model") String model, @Param("price") Integer price, @Param("memo") String memo, @Param("startuse") Date startuse);

	Integer getItMaxId();

	void delItByID(@Param("id") Integer id);

}
