package com.mav.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface Agent {
	List<Map<Object, Object>> getDeptName();
}
