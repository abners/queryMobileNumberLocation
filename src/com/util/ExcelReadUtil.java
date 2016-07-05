package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadUtil {
	public static Map<Integer, Map<Integer,Object>> readDocumentByFilePath(String absolutelyFilePath){
		if(absolutelyFilePath==null){
			return null;
		}
		Workbook workbook = null;
		
		File file = new File(absolutelyFilePath);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			if(absolutelyFilePath.endsWith(".xls")){
				workbook = new HSSFWorkbook(fileInputStream);
			}else{
				workbook = new XSSFWorkbook(fileInputStream);
			}
			Sheet sheet = workbook.getSheetAt(0);
			 // 得到总行数
            int rowNum = sheet.getLastRowNum();
            Row row = sheet.getRow(0);
            int colNum = row.getPhysicalNumberOfCells();
            Map<Integer, Map<Integer,Object>> content = new HashMap<Integer, Map<Integer,Object>>();
            // 正文内容应该从第二行开始,第一行为表头的标题
            for (int i = 1; i <= rowNum; i++) {
            	 row = sheet.getRow(i);
                 int j = 0;
                 Map<Integer,Object> cellValue = new HashMap<Integer, Object>();
                 while (j < colNum) {
                     Object obj = getCellFormatValue(row.getCell(j));
                     cellValue.put(j, obj);
                     j++;
                 }
                 content.put(i-1, cellValue);
            }
            return content;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	/**
     * 
     * 根据Cell类型设置数据
     * 
     * @param cell
     * @return
     */
    private static Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
            case Cell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    cellvalue = date;
                } else {// 如果是纯数字
                    // 取得当前Cell的数值
                    cellvalue = String.valueOf(Double.valueOf(cell.getNumericCellValue()).longValue());
                }
                break;
            }
            case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            default:// 默认的Cell值
                cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }
}
