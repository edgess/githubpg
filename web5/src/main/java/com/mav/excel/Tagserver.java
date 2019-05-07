package com.mav.excel;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.google.zxing.WriterException;

public class Tagserver {
	public static HSSFWorkbook getTagPaper(List<Integer> equipno, String serv, String path)
			throws IOException, WriterException {
		// String fileName = "tag-"+equipno + System.currentTimeMillis() + ".xls"; //
		// 文件名
		// FileInputStream is = new FileInputStream("./src/main/resources/xmoban.xls");
		FileInputStream is = new FileInputStream(path + "excel/xmoban.xls");
		HSSFWorkbook hssfWorkbookis = new HSSFWorkbook(is);
		HSSFSheet sheet = hssfWorkbookis.getSheet("sheet");
		HSSFCell cell = null;
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// insert logo
		// 抽出logo的读入，及ByteArrayOutputStream
		ByteArrayOutputStream byteArrayOutlogo = new ByteArrayOutputStream();
		BufferedImage bufferImglogo = ImageIO.read(new File(path + "excel/logo.jpg"));
		ImageIO.write(bufferImglogo, "png", byteArrayOutlogo);
		for (int i = 0; i < equipno.size(); i++) {
			int col = (i / 8) * 2;
			int rol = i % 8;
			HSSFRow row = sheet.getRow(rol);
			cell = row.createCell(col);
			cell.setCellValue("\r\rSATC" + equipno.get(i));
			HSSFClientAnchor anchorlogo = new HSSFClientAnchor(200, 20, 800, 170, (short) col, rol, (short) col, rol);
			patriarch.createPicture(anchorlogo,
					hssfWorkbookis.addPicture(byteArrayOutlogo.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
		}

		// insert qr
		// 每次图片不一样，所以只抽出ByteArrayOutputStream
		for (int i = 0; i < equipno.size(); i++) {
			int col = (i / 8) * 2;
			int rol = i % 8;
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1000, 250, (short) (col + 1), rol, (short) (col + 1),
					rol);
			Qrbulid qrbulid = new Qrbulid();
			String str = qrbulid.qr(serv + equipno.get(i), path);
			BufferedImage bufferImg = ImageIO.read(new File(str));
			ImageIO.write(bufferImg, "png", byteArrayOut);
			patriarch.createPicture(anchor,
					hssfWorkbookis.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
		}

		// FileOutputStream os = new FileOutputStream("./temp/" + fileName);
		// hssfWorkbookis.write(os);
		// os.flush();
		// os.close();

		// 删除空文件夹
		delAllFile(path + "excel/temp");
		return hssfWorkbookis;
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				// delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

}
