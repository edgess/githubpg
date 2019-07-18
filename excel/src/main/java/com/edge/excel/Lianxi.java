package com.edge.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Lianxi {

	public String exam(String path, String str) throws IOException, WriteException, BiffException {
		// 导入练习模板，生成练习输出流
//		FileInputStream is = new FileInputStream("./moban.jar");
		// 导入class path下的练习模板，生成练习输出流
		InputStream is = MoreExam.class.getClassLoader().getResourceAsStream("moban.jar");
		FileOutputStream os = new FileOutputStream(path + "/" +str);

		Workbook moban = Workbook.getWorkbook(is);
		WritableWorkbook workbook = Workbook.createWorkbook(os, moban);
		WritableSheet sheet = workbook.getSheet(0);
		SetCell setCell = new SetCell(sheet);

		String txt;
		int m0, m1, m2, m3, m4, m5, m6;
		String answer = "--";

		setCell.setHeader("DATE_________", str, "SCORE_________");
		for (int k = 0; k < 50; k++) {
			// col定义题目输出到0列与3列
			int col = k < 25 ? 0 : 3;
			int i = new Random().nextInt(2);
			if (i == 0) {
				m0 = new Random().nextInt(9) + 15;
				m1 = new Random().nextInt(9) + 15;
				m2 = m0 + m1;
				// System.out.println(m0 + " + " + m1 + "=" + m2);
				// 拼接题目
				txt = m0 + " + " + m1 + "=";
				// 拼接答案
				answer = answer + m2 + "--";
				setCell.normalCell(col, k < 25 ? k : k - 25, Integer.toString(k + 1));
				setCell.normalCell(col + 1, k < 25 ? k : k - 25, txt);
				setCell.normalCell(col + 2, k < 25 ? k : k - 25, "");
				m0 = 0;
				m1 = 0;
				m2 = 0;
				m3 = 0;
				m4 = 0;
				m5 = 0;
				m6 = 0;
			}
			if (i == 1) {
				m0 = new Random().nextInt(9) + 5;
				m1 = new Random().nextInt(9) + 15;
				m2 = m0 + m1;
				// System.out.println(m2 + " - " + m1 + "=" + m0);
				txt = m2 + " - " + m1 + "=";
				answer = answer + m0 + "--";
				setCell.normalCell(col, k < 25 ? k : k - 25, Integer.toString(k + 1));
				setCell.normalCell(col + 1, k < 25 ? k : k - 25, txt);
				setCell.normalCell(col + 2, k < 25 ? k : k - 25, "");
				m0 = 0;
				m1 = 0;
				m2 = 0;
				m3 = 0;
				m4 = 0;
				m5 = 0;
				m6 = 0;
			}
			if (i == 2) {
				m0 = new Random().nextInt(99) + 1;
				m1 = new Random().nextInt(99) + 1;
				m2 = m0 * m1;
				// System.out.println(m0 + " X " + m1 + "=" + m2);
				txt = m0 + " X " + m1 + "=";
				answer = answer + m2 + "--";
				setCell.normalCell(col, k < 25 ? k : k - 25, Integer.toString(k + 1));
				setCell.normalCell(col + 1, k < 25 ? k : k - 25, txt);
				setCell.normalCell(col + 2, k < 25 ? k : k - 25, "");
				m0 = 0;
				m1 = 0;
				m2 = 0;
				m3 = 0;
				m4 = 0;
				m5 = 0;
				m6 = 0;
			}
			if (i == 3) {
				m0 = new Random().nextInt(39) + 1;
				m1 = new Random().nextInt(99) + 1;
				m2 = m0 * m1;
				m3 = new Random().nextInt(19) + 1;
				m4 = m2 + m3;
				m5 = m4 / m0;
				m6 = m4 % m0;
				if (m6 == 0) {
					// System.out.println(m4 + " / " + m0 + "=" + m5);
					txt = m4 + " / " + m0 + "=";
					answer = answer + m5 + "--";
					setCell.normalCell(col, k < 25 ? k : k - 25, Integer.toString(k + 1));
					setCell.normalCell(col + 1, k < 25 ? k : k - 25, txt);
					setCell.normalCell(col + 2, k < 25 ? k : k - 25, "");
				} else {
					// System.out.println(m4 + " / " + m0 + "=" + m5 + "..." +
					// m6);
					txt = m4 + " / " + m0 + "=";
					String m56 = m5 + ".." + m6;
					answer = answer + m56 + "--";
					setCell.normalCell(col, k < 25 ? k : k - 25, Integer.toString(k + 1));
					setCell.normalCell(col + 1, k < 25 ? k : k - 25, txt);
					setCell.normalCell(col + 2, k < 25 ? k : k - 25, "");
				}
				m0 = 0;
				m1 = 0;
				m2 = 0;
				m3 = 0;
				m4 = 0;
				m5 = 0;
				m6 = 0;
			}

		}

		// outexcel.middleCell(0, 25, str + " 答案按顺序，每题以--分割，..后为除法余数");
		// outexcel.answerCell(0, 26, answer);
		workbook.write();
		workbook.close();
		os.close();
		return answer;
	}
}
