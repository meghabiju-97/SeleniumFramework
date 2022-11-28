package com.supermarket.utilities;


import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.supermarket.constants.Constants;



public class ExcelUtil {
	public static XSSFSheet sheet;
	public static XSSFWorkbook workbook;
	public static FileInputStream file;


	public String readDataFromExcel(int i,int j,String sheetname) throws IOException {    
		File newfile=new File(System.getProperty("user.dir")+Constants.EXCELFILE);
		file=new FileInputStream(newfile);
		workbook=new XSSFWorkbook(file);	
		sheet=workbook.getSheet(sheetname);
		Row row=sheet.getRow(i)	;
		Cell cell=row.getCell(j);	
		CellType type=cell.getCellType(); 
		switch(type)				
		{		
		case NUMERIC:
			return String.valueOf(cell.getNumericCellValue());	

		case STRING:
			return cell.getStringCellValue();			
		default:
			break;
		}

		return null;
	}





}
