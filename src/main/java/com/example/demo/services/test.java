package com.example.demo.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class test {
	
	public static void main(String[] args)  throws Exception {
		/*String originalEstimate= null;
		double originalEstimateInHours = ((double) 5000) / 3600;
		DecimalFormat df = new DecimalFormat("0.0");
		originalEstimate = df.format(originalEstimateInHours);
		System.out.println(originalEstimate);
		System.out.println(originalEstimateInHours);*/
		/*File tempFile = new File(Paths.get(test.class.getClassLoader().getResource("files").toURI()).toString() + "/rwwwwequerimientoFile.xls");
		boolean exists = tempFile.exists();
		System.out.println(exists);*/
//		System.out.println(System.getProperty("user.dir")+"/src/main/resources/files/templates");
		/*
		System.out.println(Paths.get(System.getProperty("user.dir")+"/src/main/resources/files/incidencias/"));
		System.out.println(Paths.get(test.class.getClassLoader().getResource("/").toURI()));*/
//		System.out.println(System.getProperty("user.dir"));
	
				Set<String> list = new HashSet<String>();
				list.add("sss");
				list.add("ppp");
				String delim = "-";

				String res = String.join(delim, list);
				System.out.println(res);

	}

}
