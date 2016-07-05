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
			 // �õ�������
            int rowNum = sheet.getLastRowNum();
            Row row = sheet.getRow(0);
            int colNum = row.getPhysicalNumberOfCells();
            Map<Integer, Map<Integer,Object>> content = new HashMap<Integer, Map<Integer,Object>>();
            // ��������Ӧ�ôӵڶ��п�ʼ,��һ��Ϊ��ͷ�ı���
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
     * ����Cell������������
     * 
     * @param cell
     * @return
     */
    private static Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null) {
            // �жϵ�ǰCell��Type
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:// �����ǰCell��TypeΪNUMERIC
            case Cell.CELL_TYPE_FORMULA: {
                // �жϵ�ǰ��cell�Ƿ�ΪDate
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    cellvalue = date;
                } else {// ����Ǵ�����
                    // ȡ�õ�ǰCell����ֵ
                    cellvalue = String.valueOf(Double.valueOf(cell.getNumericCellValue()).longValue());
                }
                break;
            }
            case Cell.CELL_TYPE_STRING:// �����ǰCell��TypeΪSTRING
                // ȡ�õ�ǰ��Cell�ַ���
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            default:// Ĭ�ϵ�Cellֵ
                cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }
}
