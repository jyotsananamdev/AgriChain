package com.agrichain.utility;

import com.agrichain.PreprocessorTest;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class ExcelReader extends PreprocessorTest {

	public static DataFormatter dataFormatter = new DataFormatter();
	public static FileInputStream fis = null;
	public static XSSFWorkbook excelWB;
	public static XSSFSheet excelSheet;
	public static ExcelReader er;
	private static String path;
	private static String sheetNameTxt;
	static ExcelReader excel=new ExcelReader();

	public static void main(String[] args) throws IOException {
	}

	public static synchronized XSSFWorkbook setExcelPath(String key) throws IOException {
		try {
			path=key;
			fis = new FileInputStream(System.getProperty("user.dir") +"/src/main/"+key);
			excelWB = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.exit(0);

		}
		return excelWB;
	}

	public static synchronized XSSFSheet setExcelSheet(String name) {
		try {
		excelSheet = excelWB.getSheet(name);
		sheetNameTxt=name;
			}catch(Exception e)
		{
			e.printStackTrace();//System.exit(0);
		}
		return excelSheet;
	}

	public static synchronized int getColumnCount() throws IOException {
		int cellNo=-1;
		try {
			XSSFRow row = excelSheet.getRow(0);
			cellNo = row.getLastCellNum();
			return cellNo;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(cellNo==-1)
		{
			throw new IOException("Error in getting column count.Possible no column found in this sheet.Pls check sheet");
		}

		return cellNo;
	}

	public static synchronized int getRowCount() throws IOException {
		int row=-1;
		try {
		row = excelSheet.getLastRowNum();
		return row;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//return row;
		if(row==-1)
		{
			throw new IOException("Error in getting row count");
		}
		return row;
	}

	public static  HashMap<String, HashMap<String, String>> getTestData(String platform) throws IOException {
		HashMap<String, HashMap<String, String>> excelData = new HashMap<>();
		er.setExcelPath(env_excel);
		er.setExcelSheet(platform);
		int colNo = er.getColumnCount();
		int rowNo = er.getRowCount();
		for (int i = 1; i <= rowNo; i++) {
			HashMap<String, String> data = new HashMap<>();
			for (int j = 0; j < colNo; j++) {
				XSSFRow row = er.excelSheet.getRow(i);
				XSSFCell cell = row.getCell(j);
				data.put(er.excelSheet.getRow(0).getCell(j).getStringCellValue(), new DataFormatter().formatCellValue(cell).toString().trim());
			}
			excelData.put(String.valueOf(i), data);

		}
		if(excelData.isEmpty())
		{
			System.exit(0);
		}
		System.out.println(excelData);
		return excelData;
	}

}
