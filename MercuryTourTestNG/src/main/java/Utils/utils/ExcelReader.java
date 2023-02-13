package com.visaops.ustest.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;


public class ExcelReader {

	private FileInputStream fis;
	private FileOutputStream fileOut;
	private Workbook wb;
	private Sheet sh;
	private Cell cell;
	private Row row;
	private CellStyle cellstyle;
	private Color mycolor;
	private String excelFilePath;
	private Map<String, Integer> columns = new HashMap<>();
	static private int i;
	
	 
	public void setExcelFile(String ExcelPath, String SheetName) throws Exception {
		try {
			File fis = new File(ExcelPath);
			wb = WorkbookFactory.create(fis);
			sh = wb.getSheet(SheetName);
			//sh = wb.getSheetAt(0); //0 - index of 1st sheet
			/*if (sh == null) {
	                sh = wb.createSheet(SheetName);
	            }*/

			this.excelFilePath = ExcelPath;
			//adding all the column header names to the map 'columns'
			sh.getRow(0).forEach(cell -> {
				columns.put(cell.getStringCellValue(), cell.getColumnIndex());
			});

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String getCellData(int rownum, int colnum) throws Exception {
		try {
			cell = sh.getRow(rownum).getCell(colnum);
			String CellData = null;
			switch (cell.getCellType()) {
			case STRING:
				CellData = cell.getStringCellValue();
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					CellData = String.valueOf(cell.getDateCellValue());
				} else {
					CellData = String.valueOf((long) cell.getNumericCellValue());
				}
				break;
			case BOOLEAN:
				CellData = Boolean.toString(cell.getBooleanCellValue());
				break;
			case BLANK:
				CellData = "";
				break;
			}
			return CellData;
		} catch (Exception e) {
			return "";
		}
	}

	public String getCellData(String columnName, int rownum) throws Exception {
		
					
		int noOfRows = getNoOfRows()-1;
		int noOfCells = getNoOfColumns();
		
		Object obj[][] = new Object[noOfRows][noOfCells];

		for(int i=0; i<noOfRows; i++){ 
			
			for(int j=0; j<noOfCells; j++){
				obj[i][j] = getCellData(i+1,j); //i = 1 2 3			 
			}
		} 
		
		return getCellData(rownum, columns.get(columnName));
	}

	public static void main(String[] args) throws Exception {
		ExcelReader excel = new ExcelReader();
		
		excel.setExcelFile("./src/main/resources/testData/TestDataAutomation.xlsx", "Application_Flow");
		Object obj[][] = excel.to2DArray();
		for(int i=0; i< obj.length;i++){
			for(int j=0;j<obj[i].length;j++){
				//System.out.println(obj[i][j]);
			}
		}
		
		excel.setExcelFile("./src/main/resources/testData/TestDataAutomation.xlsx", "Signup");
		 int i = 1;
		System.out.println(excel.getCellData("Mission", i));
		System.out.println(excel.getCellData("UserEmail", i));
		System.out.println(excel.getCellData("Password", i));
		
		excel.setExcelFile("./src/main/resources/testData/TestDataAutomation.xlsx", "Application_Flow");
		System.out.println(excel.getCellData("Conditions" , i));
		System.out.println(excel.getCellData("DOB", i));
		System.out.println(excel.getCellData("Visa Class", i));

		
	}

	public int getNoOfRows(){
		return sh.getPhysicalNumberOfRows();
	}

	public int getNoOfColumns(){
		return sh.getRow(0).getLastCellNum();
	}

	public Object[][] to2DArray() throws Exception {
		int noOfRows = getNoOfRows()-1;
		int noOfCells = getNoOfColumns();
		
		Object obj[][] = new Object[noOfRows][noOfCells];

		for(int i=0; i<noOfRows; i++){ 
			
			for(int j=0; j<noOfCells; j++){
				obj[i][j] = getCellData(i+1,j); //i = 1 2 3
			}
		}
		return obj;

	}
	 
}
