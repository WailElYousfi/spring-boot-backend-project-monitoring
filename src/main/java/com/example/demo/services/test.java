package com.example.demo.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DecimalFormat;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class test {
	
	public static void main(String[] args)  throws Exception {
		String originalEstimate= null;
		double originalEstimateInHours = ((double) 5000) / 3600;
		DecimalFormat df = new DecimalFormat("0.0");
		originalEstimate = df.format(originalEstimateInHours);
		System.out.println(originalEstimate);
		System.out.println(originalEstimateInHours);
		/*File tempFile = new File(Paths.get(test.class.getClassLoader().getResource("files").toURI()).toString() + "/rwwwwequerimientoFile.xls");
		boolean exists = tempFile.exists();
		System.out.println(exists);*/
		
		/*try {
            FileInputStream inputStream = new FileInputStream(new File(Paths.get(test.class.getClassLoader().getResource("files").toURI()).toString() + "/rwwwwequerimientoFile.xls"));
            Workbook workbook = WorkbookFactory.create(inputStream);
 
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(1);
            row.createCell(0).setCellValue("ok");
            
            inputStream.close();
 
            FileOutputStream outputStream = new FileOutputStream(new File(Paths.get(test.class.getClassLoader().getResource("files").toURI()).toString() + "/ssss.xls"));
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
             
        } catch (IOException | EncryptedDocumentException| InvalidFormatException ex) {
            ex.printStackTrace();
        }*/
	}

}
