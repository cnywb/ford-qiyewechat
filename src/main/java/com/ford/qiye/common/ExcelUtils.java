package com.ford.qiye.common;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;



public class ExcelUtils {
	public static HSSFWorkbook createExcelSheet(HSSFWorkbook wb,List<Object[]> list,String sheetName,String[] header){
	    HSSFSheet sheet = wb.createSheet(sheetName); // create new sheet for workbook
	   
	    HSSFRow row;
	    HSSFCell cell;
	    row = sheet.createRow(0);
	    for(int i=0;i<header.length;i++){
	   	 cell=row.createCell((short)i);
	   	 cell.setCellValue(header[i]);
	    }
	   	for(int i=0;i<list.size();i++){
		    row = sheet.createRow((short) i + 1); // create new row for
		    Object[] t=list.get(i);
			for(int j=0;j<header.length;j++){
				cell =row.createCell((short) (j));
		        
				Object obj=t[j];
				if(obj instanceof String){
					cell.setCellValue((String)obj);
				}
				if(obj instanceof Long){
					cell.setCellValue(String.valueOf((Long)obj));
				}
				if(obj instanceof Short){
					cell.setCellValue(String.valueOf((Short)obj));
				}
				if(obj instanceof Integer){
					cell.setCellValue(String.valueOf((Integer)obj));
				}
				if(obj instanceof BigDecimal){
					cell.setCellValue(String.valueOf((BigDecimal)obj));
				}
				if(obj instanceof Date){
					cell.setCellValue(DateUtils.format((Date)obj, DateUtils.FORMAT_DATE_TIME_DEFAULT));
				}
		        
			}
		}
	   	return wb;
	}
	
	public static   String getStringCellValue(Cell cell,String numericFormat){  
        if(cell==null){  
            return "";  
        }  
        switch(cell.getCellType()){  
        case Cell.CELL_TYPE_BOOLEAN:  
            return String.valueOf(cell.getBooleanCellValue());
        case Cell.CELL_TYPE_NUMERIC:  
            if(DateUtil.isCellDateFormatted(cell)){  
               return DateUtils.format(cell.getDateCellValue(),DateUtils.FORMAT_DATE_TIME_DEFAULT);
            }else{  
          	    DecimalFormat dfs = new DecimalFormat(numericFormat);
                return dfs.format(cell.getNumericCellValue());  
            }  
          
        case Cell.CELL_TYPE_FORMULA:  
            return cell.getCellFormula();
        case Cell.CELL_TYPE_STRING:  
            return cell.getStringCellValue();               
     }
		return "";  
     }  
	public static   String getStringCellValue(Cell cell){  
		return getStringCellValue(cell, "0.00");
    }  


}
