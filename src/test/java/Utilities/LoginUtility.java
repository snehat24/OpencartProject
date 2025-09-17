package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LoginUtility {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	public String path;
	
	public LoginUtility(String path) {
		this.path=path;
	}
	
public int getRowCount(String sheet) throws IOException {
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		XSSFSheet sheet1 = wb.getSheet(sheet);
		int rows=sheet1.getLastRowNum();
		wb.close();
		fi.close();
		return rows;
		
	}

public int getCellCount(String sheet,int rownum) throws IOException {
	
	fi=new FileInputStream(path);
	wb=new XSSFWorkbook(fi);
	XSSFSheet sheet1 = wb.getSheet(sheet);
	row=sheet1.getRow(rownum);
	short cellcount = row.getLastCellNum();
	wb.close();
	fi.close();
	return cellcount;
	
}

public String getCellData(String sheet,int rownum,int colnum) throws IOException {
	
	fi=new FileInputStream(path);
	wb=new XSSFWorkbook(fi);
	XSSFSheet sheet1 = wb.getSheet(sheet);
	row=sheet1.getRow(rownum);
	cell=row.getCell(colnum);
	
	DataFormatter formatter=new DataFormatter();
	String data;
    data=formatter.formatCellValue(cell);
	wb.close();
	fi.close();
	return data;
	
}

public void setCellData(String sheet,int rownum,int colnum,String data) throws IOException {
	
	fi=new FileInputStream(path);
	wb=new XSSFWorkbook(fi);
	XSSFSheet sheet1 = wb.getSheet(sheet);
	row=sheet1.getRow(rownum);
	cell=row.createCell(colnum);
	cell.setCellValue(data);
	fo=new FileOutputStream(path);
    wb.write(fo);
    wb.close();
	fi.close();
	
}
}
