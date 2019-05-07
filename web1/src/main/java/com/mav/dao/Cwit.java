package com.mav.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Cwit {
	String getone(@Param("cwid") int cwid);

	void setone(@Param("cwid") int cwid, @Param("cwstr") String cwstr);

	void editone(@Param("cwid") int cwid, @Param("cwstr") String cwstr);

	void delone(@Param("cwid") int cwid);

}
