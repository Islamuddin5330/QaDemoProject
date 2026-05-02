package com.demoqa.utilites;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUpdateRead {

	private Workbook wk;
	private Sheet st;
	private String filepath;
	
	public Object getDataFromExcel(String sheetName) throws IOException {
		try {
			this.filepath = System.getProperty("user.dir")+"\\src\\test\\resources\\ExcelFile\\ExcelData.xlsx";
			FileInputStream fis = new FileInputStream(filepath);
			this.wk = new XSSFWorkbook(fis);
			this.st = wk.getSheet(sheetName);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sheetName;
	}
	
	//Read Data from Excel file
	public String getCellData(int rowNum, int cellNum) {
		DataFormatter df = new DataFormatter();
		Cell cell = st.getRow(rowNum).getCell(cellNum);
		return df.formatCellValue(cell);
	}
	
	//Write data into Excel
	public synchronized void writeData(int rowNum, int cellNum, String value) throws IOException {

	    FileInputStream fis = new FileInputStream(filepath);
	    Workbook wk = new XSSFWorkbook(fis);
	    Sheet st = wk.getSheetAt(0);
	    fis.close();

	    Row row = st.getRow(rowNum);
	    if (row == null) row = st.createRow(rowNum);

	    Cell cell = row.getCell(cellNum);
	    if (cell == null) cell = row.createCell(cellNum);

	    cell.setCellValue(value);

	    FileOutputStream fos = new FileOutputStream(filepath);
	    wk.write(fos);

	    wk.close();
	    fos.close();
	}

	
}
