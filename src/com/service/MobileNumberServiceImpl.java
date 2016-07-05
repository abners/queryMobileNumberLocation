package com.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.factory.Factory;
import com.util.ExcelReadUtil;

public class MobileNumberServiceImpl implements MobileNumberService {

	@Override
	public void addMobileNumberLocationToDoc(String filePath) {
		Map<Integer, Map<Integer, Object>> content=ExcelReadUtil.readDocumentByFilePath(filePath);
		Iterator<?> iterator = content.entrySet().iterator();
		Factory factory = new QueryMobileNumberLocationFactory();
		QueryMobileNumberLocationService mobileNumberLocationService = factory.getInstance(QueryMobileNumberLocationServiceImpl.class);
		List<String> locationList = new ArrayList<String>();
		while(iterator.hasNext()){
			Map.Entry entry = (Entry) iterator.next();
			Map<Integer, Object> value = (Map<Integer, Object>) entry.getValue();
			String mobileNumber = String.valueOf(value.get(1));
			String location = mobileNumberLocationService.findMobileProvince(mobileNumber);
			locationList.add(location);
		}
		if(locationList.isEmpty()){
			JOptionPane.showMessageDialog(null, "所选Excel文档中不存在手机号！");
		}else{
			addLocationToDoc(filePath,locationList);
		}
		JOptionPane.showMessageDialog(null, "处理完成！");
	}

	private void addLocationToDoc(String filePath, List<String> locationList) {
		Workbook workbook = null;
		
		File file = new File(filePath);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			if(filePath.endsWith(".xls")){
				workbook = new HSSFWorkbook(fileInputStream);
			}else{
				workbook = new XSSFWorkbook(fileInputStream);
			}
		}catch(IOException exception){
			exception.printStackTrace();
		}
		Sheet sheet = workbook.getSheetAt(0);
		int x = sheet.getLastRowNum();
		for(int i=1;i<=x;i++){
			Row row = sheet.getRow(i);
			Cell cell = row.createCell(2);
			cell.setCellValue(locationList.get(i-1));
		}
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
