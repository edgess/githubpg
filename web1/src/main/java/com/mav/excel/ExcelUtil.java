package com.mav.excel;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {

	public static HSSFWorkbook getHSSFWorkbook(String sheetName, List<Map<Object, Object>> list, HSSFWorkbook wb) {
		// 标题
		String[] title = new String[] { "设备编号", "使用者", "部门", "设备类型", "出厂编号", "启用日期", "型号", "备注", "采购价格", "财务编号"};
		// 第一步，创建一个webbook，对应一个Excel文件
		if (wb == null) {
			wb = new HSSFWorkbook();
		}
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 创建标题
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}
		// 创建内容 equip_no,username,Dept_name,type,sn,startuse,model,memo,price
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 1);
			row.createCell(0).setCellValue((String) list.get(i).get("equip_no"));
			row.createCell(1).setCellValue((String) list.get(i).get("username"));
			row.createCell(2).setCellValue((String) list.get(i).get("Dept_name"));

			row.createCell(3).setCellValue(gettype((String) list.get(i).get("type")));
			row.createCell(4).setCellValue((String) list.get(i).get("sn"));
			row.createCell(5).setCellValue(sdf.format(list.get(i).get("startuse")));
			row.createCell(6).setCellValue((String) list.get(i).get("model"));
			row.createCell(7).setCellValue((String) list.get(i).get("memo"));
			row.createCell(8).setCellValue(list.get(i).get("price").toString());
			row.createCell(9).setCellValue((String) list.get(i).get("cwstr"));
		}
		return wb;
	}

	// C-台式电脑;N-笔记本电脑;P-针式打印机;L-激光打印机;M-喷墨打印机;S-服务器
	public static String gettype(String type) {
		switch (type) {
		case "C":
			return "台式电脑";
		case "N":
			return "笔记本电脑";
		case "P":
			return "针式打印机";
		case "L":
			return "激光打印机";
		case "M":
			return "喷墨打印机";
		default:
			return "服务器";
		}
	}

}
