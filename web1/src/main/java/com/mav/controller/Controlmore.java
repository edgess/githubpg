package com.mav.controller;

import com.google.zxing.WriterException;
import com.mav.dao.ItMapper;
import com.mav.excel.ExcelUtil;
import com.mav.excel.Tagserver;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class Controlmore {

	@Autowired
	private ItMapper itMapper;

	// 标签纸输出
	@RequestMapping("gettagpaper")
	public void gettagpaper(
			@RequestParam(value = "equpstart", required = false, defaultValue = "1001") String equpstart,
			@RequestParam(value = "equpstr", required = false, defaultValue = "http://192.168.10.30:12380/it/getone?equipno=SATC") String equpstr,
			HttpServletResponse response, HttpServletRequest request) throws IOException, WriterException {
		// 获取目录
		String path = request.getSession().getServletContext().getRealPath("/");
		int equipno = Integer.parseInt(equpstart);
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 24; i++) {
			list.add(i, equipno + i);
		}
		String fileName = "tag-" + System.currentTimeMillis() + ".xls";
		HSSFWorkbook wb = Tagserver.getTagPaper(list, equpstr, path);
		// 发送文件
		try {
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 台账输出
	@RequestMapping("getfile")
	public void getfile(@RequestParam(value = "p1", required = false, defaultValue = "") String p1,
			@RequestParam(value = "p2", required = false, defaultValue = "") String p2,
			@RequestParam(value = "p3", required = false, defaultValue = "") String p3,
			@RequestParam(value = "p4", required = false, defaultValue = "") String p4,
			@RequestParam(value = "p5", required = false, defaultValue = "") String p5,
			@RequestParam(value = "p6", required = false, defaultValue = "") String p6,
			@RequestParam(value = "p7", required = false, defaultValue = "") String p7, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		List<Map<Object, Object>> page = new ArrayList<Map<Object, Object>>();
		// 管理分类判断
		if (p6.equals("999")) {
			page = itMapper.getMangerItWithMap(p1, p2, p3, p4, p5, p6, p7, 1, 2000);
		} else {
			page = itMapper.getAllItWithMap(p1, p2, p3, p4, p5, p6, p7, 1, 2000);
		}

		String fileName = "data" + System.currentTimeMillis() + ".xls"; // 文件名
		String sheetName = "data";// sheet名

		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, page, null);
		// 发送文件
		try {
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
