package com.mav.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mav.dao.Cwit;
import com.mav.dao.ItMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")

public class Testapp {
	// private static Logger logger = Logger.getLogger(Testapp.class);

	@Autowired
	private ItMapper itMapper;
//	@Autowired
//	private Agent agent;
//	@Autowired
//	private Cwit cwit;

	@Test
	public void test1() {
		List<Map<Object, Object>> map = new ArrayList<>();
		map = itMapper.getoneByEquipNO("", "", "", "SATC1011", "", "", "", 1, 1);
		System.out.println("==========");
		System.out.println("==========");
		System.out.println(map);
		System.out.println("==========");
		System.out.println("==========");
	}

//	@Test
//	public void test2() throws ParseException {
//		if (cwit.getone(3)==null) {
//			System.out.println(1);
//		}
//	    System.out.println(cwit.getone(3));
//		System.out.println(itMapper.getAllItWithMap("", "", "", "", "", "", "", 1, 1));
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Date startuse = format.parse("2000-01-01");
//		itMapper.insertSelective("A", "C", "Y", "satc9999", "edge", "63", "12345678", "123", 3500, "123", startuse);
		// System.out.println(agent.getDeptName());
//	}

	// @Test
	// public void test3() {
	// List<Map<Object, Object>> map = new ArrayList<Map<Object, Object>>();
	// map = agent.getDeptName();
	// System.out.println(map);
	// }

	// @Test
	// public void test2() {
	//
	// List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
	// list = itMapper.getAllItWithMap("a", "", "y", "", "", "", "", 1, 500);
	// System.out.println(list.size());
	// Buildexcel buildexcel = new Buildexcel();
	// buildexcel.build(list);
	// }

}
