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
import org.springframework.web.client.RestTemplate;

import com.mav.dao.Agent;
import com.mav.dao.Cwit;
import com.mav.dao.ItMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")

public class Testapp {
	// private static Logger logger = Logger.getLogger(Testapp.class);

	@Autowired
	private ItMapper itMapper;
	@Autowired
	private Agent agent;
	@Autowired
	private Cwit cwit;

	@Test
	public void test8() {
		RestTemplate restTemplate = new RestTemplate();
		String url2 = "http://sh2.cshxsp.com/sms.aspx?action=send&account=hxsc022&password=123456a&mobile=13916703715&content=机房报警，请检查建滔机房";
		System.out.println(restTemplate.getForEntity(url2, String.class).getBody());
	}

	@Test
	public void test1() {
		List<Map<Object, Object>> map = new ArrayList<Map<Object, Object>>();
		map = itMapper.getoneByEquipNO("", "", "", "SATC1011", "", "", "", 1, 1);
		System.out.println(map);
	}

	@Test
	public void test2() throws ParseException {
		if (cwit.getone(3) == null) {
			System.out.println(1);
		}

		System.out.println(cwit.getone(3));
		// System.out.println(itMapper.getAllItWithMap("", "", "", "", "", "", "", 1,
		// 1));
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// Date startuse = format.parse("2000-01-01");

		// itMapper.insertSelective("A", "C", "Y", "satc9999", "edge", "63", "12345678",
		// "123", 3500, "123", startuse);
		// System.out.println(agent.getDeptName());
	}

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
