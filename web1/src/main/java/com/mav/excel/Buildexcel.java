package com.mav.excel;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Buildexcel {
	public void build(List<Map<Object, Object>> list) {
		String fileName = "data" + System.currentTimeMillis() + ".xls"; // 文件名
		String sheetName = "123";// sheet名

		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName,list, null);
		try {
			FileOutputStream os = new FileOutputStream("/Users/edge/Desktop/" + fileName);
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
