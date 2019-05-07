package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class MoreExam {
	public static void main(String[] args) throws Exception, IOException {
		//原始输出目录
		String path = "./done";
		//new File(path).mkdir();

		File dir = new File(path);
		while (dir.exists()) {
			path = path + "a";
			dir = new File(path);
		}

		dir.mkdir();
		// 导入答案模板，生成答案输出流
		FileInputStream is = new FileInputStream("./answer.jar");
		FileOutputStream os = new FileOutputStream(path+ "/" + "answer.xls");
		Workbook moban = Workbook.getWorkbook(is);
		WritableWorkbook workbook = Workbook.createWorkbook(os, moban);
		WritableSheet sheet = workbook.getSheet(0);
		SetCell setCell = new SetCell(sheet);
		// 生成10个练习
		for (int i = 1; i < 11; i++) {
			// 拼接目录和文件名
			String file = "exam_" + i + ".xls";
			Lianxi lianxi = new Lianxi();
			// 生成练习，返回值为答案
			String answer = lianxi.exam(path, file);
			// System.out.println(file);
			// System.out.println(answer);
			// 答案写入answer.xls
			setCell.middleCell(0, 0, "答案按顺序，每题以--分割，..后为除法余数");
			setCell.answerCell(0, i, file);
			setCell.answerCell(1, i, answer);
		}
		// save
		workbook.write();
		workbook.close();
		os.close();
		System.out.println("---job done---");
	}
}
