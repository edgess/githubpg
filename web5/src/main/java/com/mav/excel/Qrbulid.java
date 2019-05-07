package com.mav.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class Qrbulid {

	public String qr(String content, String path) throws IOException, WriterException {

		int width = 150; // 图像宽度
		int height = 150; // 图像高度
		String format = "png";// 图像类型
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵

		// 自定义白边边框宽度
		int margin = 10;
		// 生成新的bitMatrix
		bitMatrix = updateBit(bitMatrix, margin);
		// 输出图像
		// Path path = FileSystems.getDefault().getPath(filePath, fileName);
		// MatrixToImageWriter.writeToPath(bitMatrix, format, path);
		String filename = path + "excel/temp/tag" + System.currentTimeMillis() + ".png";
		OutputStream stream = new FileOutputStream(filename);
		MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
		return filename;
	}

	// 因为二维码边框设置那里不起作用，不管设置多少，都会生成白边，所以根据网上的例子进行修改，自定义控制白边宽度，
	// 该方法生成自定义白边框后的bitMatrix；
	private BitMatrix updateBit(BitMatrix matrix, int margin) {
		int tempM = margin * 2;
		int[] rec = matrix.getEnclosingRectangle(); // 获取二维码图案的属性
		int resWidth = rec[2] + tempM;
		int resHeight = rec[3] + tempM;
		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight); // 按照自定义边框生成新的BitMatrix
		resMatrix.clear();
		for (int i = margin; i < resWidth - margin; i++) { // 循环，将二维码图案绘制到新的bitMatrix中
			for (int j = margin; j < resHeight - margin; j++) {
				if (matrix.get(i - margin + rec[0], j - margin + rec[1])) {
					resMatrix.set(i, j);
				}
			}
		}
		return resMatrix;
	}
}
