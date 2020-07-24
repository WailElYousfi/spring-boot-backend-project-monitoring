package com.example.demo.services;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dao.IncidenceRepository;
import com.example.demo.dao.TaskRepository;
import com.example.demo.dto.IncidenceFilterDto;
import com.example.demo.dto.taskFilterDto;
import com.example.demo.models.Equivalence;
import com.example.demo.models.Incidence;
import com.example.demo.models.Task;
import com.example.demo.models.Type;

@Service
public class FenixService {

	public Map<Integer, Map<String, String> > crearTarea(MultipartFile file) throws Exception {
		Map<Integer, Map<String, String>> hmap = new HashMap<Integer, Map<String, String>>();
		Path tempDir = Files.createTempDirectory("");
		File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
		file.transferTo(tempFile);	
		FileInputStream in = new FileInputStream(tempFile);
		Workbook workbook;
		Sheet sheet;
		if(tempFile.getName().endsWith(".xls") || tempFile.getName().endsWith(".xlsx")) {
			workbook = new XSSFWorkbook(in);
			 sheet = workbook.getSheetAt(0);
		}else{
			in.close();
			throw new IllegalArgumentException("Invalid extension !");
		}	
					
		for(int i=1; i <= sheet.getLastRowNum(); i++){ 
            Row row = (Row) sheet.getRow(i);
            Map <String, String> map = new HashMap<String, String>();
            map.put("nombre", row.getCell(1).toString());
            map.put("description", row.getCell(3).toString());
            map.put("tipo", row.getCell(5).toString());
            map.put("subtipo", row.getCell(8).toString());
            map.put("petAsociada", row.getCell(6).toString());
            map.put("esfuerzo", row.getCell(11).toString());
    		hmap.put(i, map);
        }
        workbook.close();
        in.close();
        return hmap;
	}
	

	
}
