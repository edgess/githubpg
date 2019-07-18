package com.edge.excel;

import java.io.IOException;

import jxl.HeaderFooter;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class SetCell {

	public WritableSheet sheet;

	public SetCell(WritableSheet sheet) throws IOException {
		this.sheet = sheet;
	}

	public void setHeader(String left, String center, String right) {
		HeaderFooter hf = new HeaderFooter();
		//页眉页脚的左中右
		hf.getLeft().append(left);
		hf.getCentre().append(center);
		hf.getRight().append(right);
		// 加入页眉
		sheet.getSettings().setHeader(hf);
		// 加入页脚
		// dataSheet.getSettings().setFooter(hf);
	}

	public void normalCell(int col, int row, String txt) throws RowsExceededException, WriteException, IOException {
		//cell内字体
		WritableFont wf = new WritableFont(WritableFont.ARIAL, 16);
		//cell内格式
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		//输出到cell
		sheet.addCell(new Label(col, row, txt, wcf));
	}
	
	
	public void middleCell(int col, int row, String txt) throws RowsExceededException, WriteException, IOException {
		WritableFont wf = new WritableFont(WritableFont.ARIAL, 16);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		sheet.addCell(new Label(col, row, txt, wcf));
	}
	
	public void answerCell(int col, int row, String txt) throws RowsExceededException, WriteException, IOException {
		WritableFont wf = new WritableFont(WritableFont.ARIAL, 11);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setWrap(true);
		wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		sheet.addCell(new Label(col, row, txt, wcf));
	}

}
