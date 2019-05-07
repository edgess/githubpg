package com.mav.controller;

import com.mav.dao.Agent;
import com.mav.dao.Cwit;
import com.mav.dao.ItMapper;
import com.mav.token.TokenMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


@Controller
public class Control {

	@Autowired
	private ItMapper itMapper;
	@Autowired
	private Agent agent;
	@Autowired
	private Cwit cwit;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping("tagpaper")
	public String tagpaper(){
		return "tagpaper";
	}

	@RequestMapping("error")
	public String error(){
		return "error";
	}

	@RequestMapping({"login","/"})
	public String login(){
		return "login";
	}

	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("jwt")) {
					cookie.setValue(null);
					cookie.setMaxAge(0);
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
				}
			}
		}
		return "redirect:login";
	}

	@RequestMapping("dologin")
	public String login(String user, String pawd, String authCode, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {

		if (authCode.equals("") && user.equals("") && pawd.equals("")) {
			return "login";
		}

		if (!session.getAttribute("strCode").equals(authCode) || user == null || pawd == null) {
			request.setAttribute("flag", "codeerr");
			return "login";
		}
		String sql = "SELECT id,name from Operator_info where login_name=? and pwd=?";
		List<Map<String, Object>> map2 = new ArrayList<Map<String, Object>>();
		map2 = jdbcTemplate.queryForList(sql, user, pawd);
		if (map2.isEmpty()) {
			request.setAttribute("flag", "loginerr");
			return "login";
		} else {
			request.getSession().setAttribute("name", map2.get(0).get("name"));
			String payload = "{id:" + map2.get(0).get("id") + ",name:" + map2.get(0).get("name") + "}";
			String jwtstr = TokenMgr.createJWT("jwt", payload, 30 * 60 * 1000);
			Cookie cookie = new Cookie("jwt", jwtstr);// 将登录信息加入cookie中
			cookie.setMaxAge(30 * 60); // 设置cookie最大失效时间
			response.addCookie(cookie);// 将cookie返回加入
			return "redirect:dolist";
		}
	}

	@RequestMapping("authCode")
	public void getAuthCode(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		int width = 63;
		int height = 37;
		Random random = new Random();
		// 设置response头信息
		// 禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 生成缓冲区image类
		BufferedImage image = new BufferedImage(width, height, 1);
		// 产生image类的Graphics用于绘制操作
		Graphics g = image.getGraphics();
		// Graphics类的样式
		g.setColor(this.getRandColor(200, 250));
		g.setFont(new Font("Times New Roman", 0, 28));
		g.fillRect(0, 0, width, height);
		// 绘制干扰线
		for (int i = 0; i < 40; i++) {
			g.setColor(this.getRandColor(130, 200));
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			g.drawLine(x, y, x + x1, y + y1);
		}

		// 绘制字符
		String strCode = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			strCode = strCode + rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 28);
		}
		// 将字符保存到session中用于前端的验证
		session.setAttribute("strCode", strCode);
		g.dispose();

		ImageIO.write(image, "JPEG", response.getOutputStream());
		// 传送完后直接输出到页面
		response.getOutputStream().flush();
	}

	// 创建颜色
	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	@RequestMapping("getone")
	public String getone(@RequestParam(value = "equipno", required = false, defaultValue = "") String equipno,
			Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws IOException {
		map.put("page", itMapper.getoneByEquipNO("", "", "", equipno, "", "", "", 1, 1));
		return "getone";
	}

	@RequestMapping("dolist")
	public String dolist(@RequestParam(value = "p1", required = false, defaultValue = "") String p1,
			@RequestParam(value = "p2", required = false, defaultValue = "") String p2,
			@RequestParam(value = "p3", required = false, defaultValue = "") String p3,
			@RequestParam(value = "p4", required = false, defaultValue = "") String p4,
			@RequestParam(value = "p5", required = false, defaultValue = "") String p5,
			@RequestParam(value = "p6", required = false, defaultValue = "") String p6,
			@RequestParam(value = "p7", required = false, defaultValue = "") String p7,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
			Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws IOException {
		// 单页显示数设置
		int pgsiz = 20;
		// 页数判断
		int pgstr = 1;
		try {
			pgstr = Integer.parseInt(pageNoStr);
			if (pgstr < 1) {
				pgstr = 1;
			}
		} catch (Exception e) {
		}
		List<Map<Object, Object>> page = new ArrayList<Map<Object, Object>>();
		int resultCount = 0;
		// 管理分类判断
		if (p6.equals("999")) {
			// 分页带参查询数据
			// mysql
			// page = itMapper.getMangerItWithMap(p1, p2, p3, p4, p5, p6, p7,
			// (pgstr - 1) * pgsiz, pgsiz);
			// sqlserver
			page = itMapper.getMangerItWithMap(p1, p2, p3, p4, p5, p6, p7, pgstr, pgsiz);
			// 带参数据总数
			resultCount = itMapper.getMangerItWithMapCount(p1, p2, p3, p4, p5, p6, p7);
		} else {
			// 分页带参查询数据
			// mysql
			// page = itMapper.getAllItWithMap(p1, p2, p3, p4, p5, p6, p7,
			// (pgstr - 1) * pgsiz, pgsiz);
			// sqlserver
			page = itMapper.getAllItWithMap(p1, p2, p3, p4, p5, p6, p7, pgstr, pgsiz);
			// 带参数据总数
			resultCount = itMapper.getAllItWithMapCount(p1, p2, p3, p4, p5, p6, p7);
		}
		// 查部门
		List<Map<Object, Object>> page2 = new ArrayList<Map<Object, Object>>();
		page2 = agent.getDeptName();
		// 回显map
		Map<Object, Object> page3 = new HashMap<Object, Object>();
		page3.put("p1", p1);
		page3.put("p2", p2);
		page3.put("p3", p3);
		page3.put("p4", p4);
		page3.put("p5", p5);
		page3.put("p6", p6);
		page3.put("p7", p7);
		// 分页数据
		page3.put("pageNo", pageNoStr);
		page3.put("resultCount", resultCount);
		int pageCount = (resultCount + pgsiz - 1) / pgsiz;
		page3.put("pageCount", pageCount);
		// 插入返回map中
		map.put("page", page);
		map.put("page2", page2);
		map.put("page3", page3);
		return "list";
	}

	@RequestMapping("edit")
	public String edit(String id, Map<String, Object> map, HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		// id号强转
		int idi = 0;
		if (id != null) {
			idi = Integer.parseInt(id);
		}
		// 根据id取纪录
		List<Map<Object, Object>> page = new ArrayList<Map<Object, Object>>();
		page = itMapper.getItById(idi);
		map.put("page", page);
		// 取部门
		List<Map<Object, Object>> page2 = new ArrayList<Map<Object, Object>>();
		page2 = agent.getDeptName();
		map.put("page2", page2);
		return "edit";
	}

	@RequestMapping("doedit")
	public String doedit(String p0, String p1, String p2, String p3, String p4, String p5, String p6, String p7,
			String p8, String p9, String p10, String p11, String cwstr, HttpServletResponse response,
			HttpServletRequest request) throws ParseException, IOException {
		// id号强转
		int p0i = Integer.parseInt(p0);
		// 默认日期设定
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 强转日期
		Date p11d = format.parse("1980-01-01");
		// 价格强转
		int p9i = 0;
		if (p9 != "") {
			p9i = Integer.parseInt(p9);
		}
		if (p11 != "") {
			p11d = format.parse(p11.replace("/", "-"));
		}
		// sql执行
		itMapper.updateByPrimaryKeySelective(p1, p2, p3, p4.toUpperCase(), p5, p6, p7.toUpperCase(), p8, p9i, p10, p11d,
				p0i);
		if (cwit.getone(p0i) == null) {
			cwit.setone(p0i, cwstr);
		} else {
			cwit.editone(p0i, cwstr);
		}
		return "redirect:dolist";
	}

	@RequestMapping("add")
	public String add(Map<String, Object> map, HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		// 取部门
		List<Map<Object, Object>> page2 = new ArrayList<Map<Object, Object>>();
		page2 = agent.getDeptName();
		// 返回页面
		map.put("page2", page2);
		return "add";
	}

	@RequestMapping("doadd")
	public String doadd(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8,
			String p9, String p10, String p11, String cwstr, String times, HttpServletResponse response,
			HttpServletRequest request) throws ParseException, IOException {
		// 添加多次数据
		int timesi = 1;
		if (times != "") {
			timesi = Integer.parseInt(times);
		}
		if (timesi > 20) {
			timesi = 20;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 默认日期设定
		Date p11d = format.parse("2000-01-01");
		if (p11 != "") {
			p11d = format.parse(p11.replace("/", "-"));
		}
		// 价格强转
		int p9i = 0;
		if (p9 != "") {
			p9i = Integer.parseInt(p9);
		}
		// 设定默认设备号
		if (p4 == "") {
			p4 = "satctemp";
		}
		// 执行添加
		for (int i = 0; i < timesi; i++) {
			itMapper.insertSelective(p1, p2, p3, p4.toUpperCase(), p5, p6, p7.toUpperCase(), p8, p9i, p10, p11d);
			cwit.setone(itMapper.getItMaxId(), cwstr);
		}
		return "redirect:dolist";
	}

	@RequestMapping("del")
	public String del(String pagedel, HttpServletResponse response, HttpServletRequest request) throws IOException {
		int p9i = Integer.parseInt(pagedel);
		itMapper.delItByID(p9i);
		cwit.delone(p9i);
		return "redirect:dolist";
	}
}
