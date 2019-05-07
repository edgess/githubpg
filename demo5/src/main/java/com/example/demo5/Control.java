package com.example.demo5;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Control {

	@Autowired
	private OilService oilService;
	@RequestMapping("/test")
	public String test(){
		return "test";
	}

	@RequestMapping(value = { "list", "/" })
	public String list(@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
			Map<String, Object> map) {
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
			if (pageNo < 1) {
				pageNo = 1;
			}
		} catch (Exception e) {
		}
		Page<Oil> page = oilService.getPage(pageNo, 10);
		map.put("page", page);
		return "list";
	}

	@RequestMapping(value = "edit")
	public String edit(String id, Map<String, Object> map) {
		Integer idi;
		if (null != id) {
			idi = Integer.parseInt(id);
		} else {
			idi = oilService.getLastOne().getId();
		}
		Oil oil = oilService.getOneById(idi);
		map.put("oil", oil);
		return "edit";
	}

	@RequestMapping(value = "add")
	public String input(Map<String, Object> map) {
		Oil oil = oilService.getLastOne();
		map.put("oil", oil);
		return "add";
	}

	@ResponseBody
	@RequestMapping(value = "addedit")
	public void addedit(String id, String mile, String cash, String price) {
		Oil oil = new Oil();
		if (null != id) {
			oil.setId(Integer.parseInt(id));
		}
		oil.setMile(Integer.parseInt(mile));
		oil.setCash(Integer.parseInt(cash));
		oil.setPrice(Double.parseDouble(price));
		oilService.saveNew(oil);
	}

	@ResponseBody
	@RequestMapping(value = "dodel")
	public void dodel(String id) {
		if (null != id) {
			oilService.deleteById(Integer.parseInt(id));
			// System.out.println(Integer.parseInt(id));
		}
	}

	// @ResponseBody
	// @RequestMapping(value = "/doadd")
	// public void doadd(String mile, String cash, String price) {
	// Oil oil = new Oil();
	// oil.setMile(Integer.parseInt(mile));
	// oil.setCash(Integer.parseInt(cash));
	// oil.setPrice(Double.parseDouble(price));
	// oilService.saveNew(oil);
	// }

	@ResponseBody
	@RequestMapping(value = "savewithAjax", method = RequestMethod.POST)
	public String savewithAjax(@RequestParam(value = "mile", required = true) String milestr,
			@RequestParam(value = "cash", required = true) String cashstr,
			@RequestParam(value = "price", required = true) String pricestr) {

		int mile = 0;
		int cash = 0;
		double price = 0;

		try {
			mile = Integer.parseInt(milestr);
			cash = Integer.parseInt(cashstr);
			price = Double.parseDouble(pricestr);
			Oil oil = oilService.getLastOne();
			System.out.println(oil);
			if (mile < oil.getMile()) {
				return "0";
			}
			if (cash < 100 || cash > 650) {
				return "0";
			}
			if (price < 2) {
				return "0";
			}
			System.out.println(mile + "--" + cash + "--" + price);
			Oil oil2 = new Oil(mile, cash, price, null);
			oilService.saveNew(oil2);
			return "1";

		} catch (Exception e) {
			return "0";

		}

	}

}
