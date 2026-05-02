package com.demoqa.utilites;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDate {

	public String[][] getData(String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\ExcelFile\\ExcelData.xlsx");
		Workbook wk = new XSSFWorkbook(fis);
		Sheet st = wk.getSheet(sheetName);
		
		int rowCount = st.getPhysicalNumberOfRows();
		int colCount = st.getRow(0).getPhysicalNumberOfCells();
		
		String[][] data = new String[rowCount-1][colCount];
		DataFormatter df = new DataFormatter();
		
		for(int i=1; i<rowCount; i++) {
			Row row = st.getRow(i);
			if(row == null) continue;
			for(int j=0; j<colCount; j++) {
				Cell cell = row.getCell(j);
				data[i-1][j] = df.formatCellValue(cell);
				System.out.println( "Row: " + i + " Col: " + j + " Value: " + data[i - 1][j]);
			}
		}
		wk.close();
		fis.close();
		return data;
	}
}
