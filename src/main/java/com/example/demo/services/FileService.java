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
public class FileService {
	
	@Autowired
	TaskRepository taskRepository;

	@Autowired
	IncidenceRepository incidenceRepository;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	IncidenceService incidenceService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TypeService typeService;
	
	@Autowired
	ExcelService excelService;
	
	
	public String secondsToHoursOrNull(Cell cell) {
		if(cell.toString().isEmpty()) {
			double originalEstimateInHours = ((double) cell.getNumericCellValue()) / 3600;
			DecimalFormat df = new DecimalFormat("0.0");
			return df.format(originalEstimateInHours);
		}
		return null;
	}
	
	public Date getDateFromCellOrNull(Cell cell) {
		try {
			if(cell.toString().isEmpty())
				return null;
			if(cell.getCellTypeEnum()== CellType.STRING) {
				Date date=new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(cell.toString());
				return date;
			}else 
				return cell.getDateCellValue();
		}catch(Exception e) {
			throw new IllegalArgumentException("Invalid cell format");
		}
	}
	
	
	public List<Task> getDataTaskFile(MultipartFile file, Integer idOt) throws Exception {	
		Path tempDir = Files.createTempDirectory("");
		File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
		file.transferTo(tempFile);		
		XSSFWorkbook workbook = new XSSFWorkbook(tempFile); 
		XSSFSheet sheet = workbook.getSheetAt(0); 
        Row row;
        
        Type tareaType = typeService.getTypeByName("Tarea cargable");
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		//List<String> originalColumns = new ArrayList<String>();
		Set<String> originalColumns = new HashSet<String>();
		List<String> fileColumns = new ArrayList<String>();
		
		for (Equivalence colonne : tareaType.getColonnes())
			if(colonne.getJiraEquivalence() != null)
				originalColumns.add(colonne.getJiraEquivalence());
		
	//	List<String> originalColumnWithoutDuplicates =  originalColumns.stream().distinct().collect(Collectors.toList()); // Remove duplicates if exists
		
		for(int j=0; j < sheet.getRow(3).getLastCellNum(); j++)
			fileColumns.add(sheet.getRow(3).getCell(j).toString());	//get file columns
		
		if(!fileColumns.containsAll(originalColumns)) {
	        workbook.close();
			throw new ResourceNotFoundException("Columns not found in this file !!");
		}
		
		for(int j=0; j < sheet.getRow(3).getLastCellNum(); j++)
			for(Equivalence colonne : tareaType.getColonnes())
				if(sheet.getRow(3).getCell(j).toString().equals(colonne.getJiraEquivalence()))
					hm.put(colonne.getColumnName(), j);	// pour savoir le contenu de chaque colonne
		
		List<Task> taskList = new ArrayList<Task>();
        for(int i=4; i <= sheet.getLastRowNum()-1 ; i++){  
        	row = (Row) sheet.getRow(i);
        	if(row.getCell(0).toString().isEmpty())
            	break; 
			Task task = new Task();
			task.setProject(projectService.getProjectById(4));
			task.setKey(row.getCell(hm.get("key")).toString());
			task.setSummary(row.getCell(hm.get("title")).toString());
			task.setOriginalEstimate(row.getCell(hm.get("originalEstimate")).toString());
			task.setRemainingEstimate(row.getCell(hm.get("remainingEstimate")).toString());
			task.setStatus(row.getCell(hm.get("status")).toString());
			task.setTimeSpent(row.getCell(hm.get("timeSpent")).toString());
			task.setTaskType(row.getCell(hm.get("taskType")).toString());
			task.setDate(new Date());
			task.setAssignedUser(userService.getUserByJiraUsername(row.getCell(hm.get("assignedUser")).toString()));
//			task.setComment(row.getCell(hm.get("comment")).toString());
			task.setFileType(typeService.getTypeByName("Tarea cargable"));
			
			if(row.getCell(hm.get("created")).getCellType()== Cell.CELL_TYPE_BLANK)
				task.setCreated(null);
			else
				task.setCreated(row.getCell(hm.get("created")).getDateCellValue());
			
			if(row.getCell(hm.get("updated")).getCellType()== Cell.CELL_TYPE_BLANK)
				task.setUpdated(null);
			else
				task.setUpdated(row.getCell(hm.get("updated")).getDateCellValue());
			
			if(row.getCell(hm.get("resolved"))==null || row.getCell(hm.get("resolved")).getCellType()== Cell.CELL_TYPE_BLANK)
				task.setResolved(null);
			else
				task.setResolved(row.getCell(hm.get("resolved")).getDateCellValue());
			
			task.setIdOt(idOt);
			
			taskList.add(task);
        }
        workbook.close();
        return taskList;
    }
	
	
	public String uploadTasks(List<Task> tasks) throws Exception {	
		if(!tasks.isEmpty()) {
	        for (Task task : tasks)
				taskService.updateTask(task);
	        return "Tasks successfully uploaded to Database";
		}
		return "There is no task to upload";
    }
	
	
	
	
	public List<Incidence> getDataIncidenceFile(MultipartFile file) throws Exception {	
		Path tempDir = Files.createTempDirectory("");
		File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
		file.transferTo(tempFile);	
		FileInputStream in = new FileInputStream(tempFile);
		Workbook workbook;
		Sheet sheet;
		if(tempFile.getName().endsWith(".xls")) {
			 workbook = new HSSFWorkbook(in);
			 sheet = workbook.getSheetAt(0);
		}else if(tempFile.getName().endsWith(".xlsx")) {
			workbook = new XSSFWorkbook(in);
			sheet = workbook.getSheetAt(0);
		}else {
			in.close();
			throw new IllegalArgumentException("Invalid extension ! You have to select '.xls' or '.xlsx' file");
		}	
		
        Row row;       
		List<Incidence> incidenceList = new ArrayList<Incidence>();
		Type incidenciaInternaType = typeService.getTypeByName("Incidencia interna");
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		Set<String> originalColumns = new HashSet<String>();
		Set<String> missignElements = new HashSet<String>();
		List<String> fileColumns = new ArrayList<String>();
		
		for (Equivalence colonne : incidenciaInternaType.getColonnes())
			if(colonne.getJiraEquivalence() != null)
				originalColumns.add(colonne.getJiraEquivalence());
				
		for(int j=0; j < sheet.getRow(3).getLastCellNum(); j++)
			fileColumns.add(sheet.getRow(3).getCell(j).toString());
		
		if(!fileColumns.containsAll(originalColumns)) {
			missignElements = originalColumns;
			missignElements.removeAll(fileColumns);
			String delim = ", ";
			workbook.close();
			throw new ResourceNotFoundException("( " + String.join(delim, missignElements) +" ) not found in this file !!");
		}
		
		for(int j=0; j < sheet.getRow(3).getLastCellNum(); j++)
			for(Equivalence colonne : incidenciaInternaType.getColonnes())
				if(sheet.getRow(3).getCell(j).toString().equals(colonne.getJiraEquivalence()))
					hm.put(colonne.getColumnName(), j);
					
		for(int i=4; i <= sheet.getLastRowNum()-1; i++){        	               	
            row = (Row) sheet.getRow(i);
			Incidence incidence = new Incidence();
			incidence.setKey(row.getCell(hm.get("key")).toString());
			incidence.setStatus(row.getCell(hm.get("status")).toString());
			incidence.setAssignedUser(userService.getUserByJiraUsernameOrNull(row.getCell(hm.get("assignedUser")).toString())); //return User or NULL
			incidence.setCausedUser(userService.getUserByJiraUsernameOrNull(row.getCell(hm.get("causedUser")).toString()));
			incidence.setSummary(row.getCell(hm.get("summary")).toString());
			incidence.setOriginalEstimate(secondsToHoursOrNull(row.getCell(hm.get("originalEstimate")))); // #,# or null
			incidence.setTimeSpent(secondsToHoursOrNull(row.getCell(hm.get("timeSpent"))));
			incidence.setDescription(row.getCell(hm.get("description")).toString());
			incidence.setLinkedIssues(row.getCell(hm.get("linkedIssues")).toString());
			incidence.setJiraSas(row.getCell(hm.get("jiraSas")).toString());						
			incidence.setIncidenceType(row.getCell(hm.get("incidenceType")).toString());
			incidence.setUpdated(getDateFromCellOrNull(row.getCell(hm.get("updated"))));	//return date or null
			incidence.setResolved(getDateFromCellOrNull(row.getCell(hm.get("resolved"))));					
			incidence.setProject(projectService.getProjectByName(row.getCell(hm.get("project")).toString()));
			incidence.setDate(new Date());
			if(row.getCell(hm.get("summary")).toString().contains("INC_INT"))
				incidence.setFileType(typeService.getTypeByName("Incidencia interna"));
			else if(row.getCell(hm.get("summary")).toString().contains("INC_EXT"))
				incidence.setFileType(typeService.getTypeByName("Incidencia externa"));
						
			incidenceList.add(incidence);
        }
        workbook.close();
        return incidenceList;
    }
	
	
	public String uploadIncidences(List<Incidence> incidences) throws Exception {	
		if(!incidences.isEmpty()) {
	        for (Incidence incidence : incidences)
				incidenceService.updateIncidence(incidence);
	        return "Incidences successfully uploaded to Database";
		}
		return "There is no incidence to upload";
    }
	
	

	public String generateTasksFile(taskFilterDto filter) throws Exception {
		Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse(filter.getStartDate());
		Date endDate=new SimpleDateFormat("yyyy-MM-dd").parse(filter.getEndDate());
		List<Task> tasks = taskService.getTaskByDatesAndIdOt(startDate, endDate, 11111);
			try {
				XSSFWorkbook workbook = new XSSFWorkbook();
				Date date = new Date();
				String fileName = Long.toString(date.getTime());
				FileOutputStream out = new FileOutputStream(new File(Paths.get(getClass().getClassLoader().getResource("files/tasks").toURI()).toString() + "/" + fileName +".xls"));
				Sheet sheet = workbook.createSheet("Sheet1");
	
		        CreationHelper createHelper = workbook.getCreationHelper();
	
		        Font headerFont = workbook.createFont();
		        CellStyle headerCellStyle = workbook.createCellStyle();
		        excelService.setCellStyle(headerCellStyle, "green", headerFont, true, "white");
	
		        Row headerRow = sheet.createRow(0);		        
		        Type taskType = typeService.getTypeByName("Tarea cargable");
				HashMap<Integer, String> columnsName = new HashMap<Integer, String>();
				
				for (Equivalence colonne : taskType.getColonnes())
					if(colonne.getFenixEquivalence() != null)
						columnsName.put(colonne.getColumnOrder(), colonne.getFenixEquivalence());		    
			      
				excelService.setHeader(columnsName, headerRow, headerCellStyle, headerCellStyle);
	
		        CellStyle dateCellStyle = workbook.createCellStyle();
		        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy HH:mm:ss"));
		        
		        int rowNum = 1;
		        for(Task task: tasks) {		     
		        	Row row = sheet.createRow(rowNum++);
			        row.createCell(1).setCellValue(task.getSummary());
			        //row.createCell(3).setCellValue(task.getDescription());
			        row.createCell(4).setCellValue("En Ejecución");
			        row.createCell(5).setCellValue("Evolutivo(ENP)");
			        row.createCell(6).setCellValue(task.getIdOt());
			        row.createCell(7).setCellValue(task.getAssignedUser().getUserCode());
			        row.createCell(8).setCellValue(task.getTaskType());
			        row.createCell(9).setCellValue(0);
			        row.createCell(10).setCellValue("Media");
			            
			        //String originalEstimateString = StringUtils.chop(task.getOriginalEstimate());
			        float originalEstimateFloat = Float.parseFloat(task.getOriginalEstimate());
			        String st = Float.toString(originalEstimateFloat);
			        row.createCell(11).setCellValue(st);
	
			        Date date2 = new Date();
			        Cell CellDate = row.createCell(13);
			        CellDate.setCellValue(date2);
			        CellDate.setCellStyle(dateCellStyle);
			        Cell CellDate2 = row.createCell(14);
			        CellDate2.setCellValue(date2);
			        CellDate2.setCellStyle(dateCellStyle);
		        	          
		        }
	
		        for(int i = 0; i < columnsName.size(); i++) {
		            sheet.autoSizeColumn(i);
		        }
		        workbook.write(out);
		        out.close();
		        workbook.close();		
			}catch(Exception e) {
				System.out.println(e);
			}
		return "Tasks file successfully generated";
	}
	
	
	public String generateIncidencesFile(IncidenceFilterDto filter) throws Exception {
		excelService.createTemplateIncidenciaFile();
		Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse(filter.getStartDate());
		Date endDate=new SimpleDateFormat("yyyy-MM-dd").parse(filter.getEndDate());
		Optional < List<Incidence> > result = incidenceRepository.findByDate(startDate, endDate);
		Date date = new Date();
		if(!result.isPresent()) {
			throw new ResourceNotFoundException("No incidence is found in these dates !!!");
		}else {
			List<Incidence> incidences = result.get();
			try {
				FileInputStream inputStream = new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/resources/files/templates".toString() + "/incidenciaTemplate.xls"));
	            Workbook workbook = WorkbookFactory.create(inputStream);
		        Sheet sheet = workbook.getSheetAt(0);
		        CreationHelper createHelper = workbook.getCreationHelper();

				Type incidenciaInternaType = typeService.getTypeByName("Incidencia interna");
				HashMap<Integer, String> columnsName = new HashMap<Integer, String>();
				
				for (Equivalence colonne : incidenciaInternaType.getColonnes())
					if(colonne.getFenixEquivalence() != null)
						columnsName.put(colonne.getColumnOrder(), colonne.getFenixEquivalence());		    
	
		        Row row2 = sheet.getRow(2);
				excelService.setHeader(columnsName, row2);	
		        Row row3 = sheet.getRow(3);
		        List<Row> rows = new ArrayList<Row>();
		        rows.add(row3);
		        for (int i = 4; i <= 13; i++)
		        	rows.add(sheet.createRow(i));
		        rows.add(row2);
		        excelService.seedAdditionalCells(rows, columnsName);
		        		       		        
		        CellStyle dateCellStyle = workbook.createCellStyle(); // Create Cell Style for formatting Date
		        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));	        		     
		        
		        int rowNum = 4;
		        for(Incidence incidence: incidences) {
		        	Row row;
		        	if(rowNum <= 13)
		        		row = sheet.getRow(rowNum++);
		        	else
			        	row = sheet.createRow(rowNum++);
		        	row.createCell(2).setCellValue(incidence.getSummary());
		        	row.createCell(3).setCellValue("PRUEBAS_UNITARIAS");
		        	if(incidence.getIncidenceType().equals("Construcción"))
			        	row.createCell(4).setCellValue("ERROR_EN_CODIGO");
			        else
			        	row.createCell(4).setCellValue("ERROR_OD");
		        	row.createCell(5).setCellValue(incidence.getDescription());
		        	Date fechaInicio = new Date();
			        Cell CellDate1 = row.createCell(6);
			        CellDate1.setCellValue(fechaInicio);
			        CellDate1.setCellStyle(dateCellStyle);

			        Calendar c = Calendar.getInstance();
			    	c.setTime(fechaInicio);
			    	c.add(Calendar.MONTH, 2);// add 1 month			 
			    	Date finCliente = new SimpleDateFormat("dd/MM/yyyy").parse(Integer.toString(c.get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(c.get(Calendar.MONTH))+"/"+Integer.toString(c.get(Calendar.YEAR)));  			    	
				    Cell CellDate2 = row.createCell(7);
				    CellDate2.setCellValue(finCliente);
				    CellDate2.setCellStyle(dateCellStyle);
				    
				    row.createCell(8).setCellValue(0.10);
				    row.createCell(9).setCellValue("MEDIA");
				    row.createCell(10).setCellValue("MEDIO");
				    row.createCell(11).setCellValue("NO");
				    row.createCell(12).setCellValue("MEDIA");
				    Cell CellDate3 = row.createCell(13);
				    CellDate3.setCellValue(fechaInicio);
				    CellDate3.setCellStyle(dateCellStyle);
				    row.createCell(14).setCellValue(filter.getTareasCausante().get(incidence.getCausedUser().getJiraUsername()));
				    if(incidence.getFileType().getTypeName().equals("Incidencia interna")) {
			        	row.createCell(1).setCellValue(filter.getIdOt());
				    	row.createCell(15).setCellValue(filter.getIdOt());
				    }else {
			        	row.createCell(1).setCellValue(filter.getIdPeticion());
				    	row.createCell(15).setCellValue(filter.getOtCorrector());
				    }
				    row.createCell(16).setCellValue(filter.getAcc());
				    row.createCell(17).setCellValue("En ejecución");			    			
		        }		      				
		        for(int i = 0; i < columnsName.size()+10; i++) { // Resize all columns to fit the content size
		            sheet.autoSizeColumn(i);
		        }
		        inputStream.close();		    
	            FileOutputStream outputStream = new FileOutputStream(new File(System.getProperty("user.dir")+"/src/main/resources/files/incidencias".toString() + "/incidencia_" + date.getTime()+ ".xls"));
	            workbook.write(outputStream);
	            workbook.close();
	            outputStream.close();
			} catch (IOException | EncryptedDocumentException| InvalidFormatException ex) {
				ex.printStackTrace();
			}
		}
		return "incidencia_" + date.getTime();
	}
	
	
	
	public List<Task> getDataRequerimientoFile(MultipartFile file) throws Exception {	

		Path tempDir = Files.createTempDirectory("");
		File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
		file.transferTo(tempFile);	
		FileInputStream in = new FileInputStream(tempFile);
		Workbook workbook;
		Sheet sheet;
		if(tempFile.getName().endsWith(".xls")) {
			 workbook = new HSSFWorkbook(in);
			 sheet = workbook.getSheetAt(0);
		}else if(tempFile.getName().endsWith(".xlsx")) {
			workbook = new XSSFWorkbook(in);
			sheet = workbook.getSheetAt(0);
		}else {
			in.close();
			throw new IllegalArgumentException("Invalid extension ! You have to select 'xls' or 'xlsx' file");
		}	
	       
		Row row;        
        Type requerimientoType = typeService.getTypeByName("Requerimiento");
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		Set<String> originalColumns = new HashSet<String>();
		List<String> fileColumns = new ArrayList<String>();
		Set<String> missignElements =  new HashSet<String>();
		
		for (Equivalence colonne : requerimientoType.getColonnes())
			if(colonne.getJiraEquivalence() != null)
				originalColumns.add(colonne.getJiraEquivalence());
				
		for(int j=0; j < sheet.getRow(3).getLastCellNum(); j++)
			fileColumns.add(sheet.getRow(3).getCell(j).toString());	//get file columns
		
		if(!fileColumns.containsAll(originalColumns)) {
	        workbook.close();
			missignElements = originalColumns;
			missignElements.removeAll(fileColumns);
			String delim = ", ";
			throw new ResourceNotFoundException("( " + String.join(delim, missignElements) +" ) not found in this file !!");
		}
		
		for(int j=0; j < sheet.getRow(3).getLastCellNum(); j++)
			for(Equivalence colonne : requerimientoType.getColonnes())
				if(sheet.getRow(3).getCell(j).toString().equals(colonne.getJiraEquivalence()))
					hm.put(colonne.getColumnName(), j);	// pour savoir le contenu de chaque colonne
		
		List<Task> taskList = new ArrayList<Task>();
        for(int i=4; i <= sheet.getLastRowNum()-1 ; i++){  
        	row = (Row) sheet.getRow(i);
        	if(row.getCell(0).toString().isEmpty())
            	break; 
			Task task = new Task();
			task.setProject(projectService.getProjectById(4));
			task.setKey(row.getCell(hm.get("key")).toString());
			task.setSummary(row.getCell(hm.get("summary")).toString());
			task.setOriginalEstimate(secondsToHoursOrNull(row.getCell(hm.get("originalEstimate")))); // #,# or null
			task.setRemainingEstimate(secondsToHoursOrNull(row.getCell(hm.get("remainingEstimate")))); // #,# or null
			task.setTimeSpent(secondsToHoursOrNull(row.getCell(hm.get("timeSpent")))); // #,# or null
			task.setStatus(row.getCell(hm.get("status")).toString());
			task.setTaskType(row.getCell(hm.get("taskType")).toString());
			task.setDate(new Date());
			task.setAssignedUser(userService.getUserByJiraUsernameOrNull(row.getCell(hm.get("assignedUser")).toString())); //return user or null
			task.setFileType(requerimientoType);
			task.setCreated(getDateFromCellOrNull(row.getCell(hm.get("created"))));
			task.setUpdated(getDateFromCellOrNull(row.getCell(hm.get("updated"))));
			task.setResolved(getDateFromCellOrNull(row.getCell(hm.get("resolved"))));

		/*	if(row.getCell(hm.get("created"))==null || row.getCell(hm.get("created")).getCellTypeEnum()== CellType.BLANK)
				task.setCreated(null);*/
			taskList.add(task);
        }
        workbook.close();
        return taskList;
    }
	
	
	public String generateRequerimientoFile(taskFilterDto filter) throws Exception {
		excelService.createTemplateRequerimientoFile();
		Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse(filter.getStartDate());
		Date endDate=new SimpleDateFormat("yyyy-MM-dd").parse(filter.getEndDate());
		List<Task> tasks = taskService.getTaskByDatesAndSummary(startDate, endDate, filter.getSummary());
		try {
			FileInputStream inputStream = new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/resources/files/templates".toString() + "/requerimientoTemplate.xls"));
            Workbook workbook = WorkbookFactory.create(inputStream);
	        Sheet sheet = workbook.getSheetAt(0);
	        Date date = new Date();
		        			      
	        Type taskType = typeService.getTypeByName("Requerimiento");
			HashMap<Integer, String> columnsName = new HashMap<Integer, String>();				
			for (Equivalence colonne : taskType.getColonnes())
				if(colonne.getFenixEquivalence() != null)
					columnsName.put(colonne.getColumnOrder(), colonne.getFenixEquivalence());		    
			      
			Row row1 = sheet.getRow(1);
			excelService.setHeader(columnsName, row1);			        
	        
        	List<String> names = new ArrayList<String>();
	        for (Task task : tasks)
	        	names.add(task.getSummary());
	        java.util.Collections.sort(names); // sort names alphabetically
		        
	        int rowNum = 2;
	        for(Task task: tasks) {		     
	        	Row row = sheet.createRow(rowNum++);
		        row.createCell(1).setCellValue(names.indexOf(task.getSummary()) + 1);
		        row.createCell(2).setCellValue("Codificación / Pruebas Unitarias");
		        row.createCell(3).setCellValue(task.getSummary());
		        row.createCell(4).setCellValue("Disponibilidad");
		        if(task.getAssignedUser() != null)
		        	row.createCell(5).setCellValue(task.getAssignedUser().getUserCode());
			        
		        String originalEstimate = task.getOriginalEstimate();
		        CellStyle decimalStyle = workbook.createCellStyle();
                DataFormat format = workbook.createDataFormat();
                decimalStyle.setDataFormat(format.getFormat("0.0"));
                
		        if(task.getOriginalEstimate() != null) {
		        	Cell cell6 = row.createCell(6);
			        cell6.setCellStyle(decimalStyle);
			        cell6.setCellValue(Float.parseFloat(originalEstimate.replace(",", ".")));
			        Cell cell10 = row.createCell(10);
			        cell10.setCellStyle(decimalStyle);
			        cell10.setCellValue(Float.parseFloat(originalEstimate.replace(",", ".")));				       
		        }			      
		        row.createCell(7).setCellValue(filter.getIdOt());			        		        	        
	        }

	        for(int i = 0; i < columnsName.size(); i++) {
	            sheet.autoSizeColumn(i);
	        }
	        inputStream.close();		    
            FileOutputStream outputStream = new FileOutputStream(new File(System.getProperty("user.dir")+"/src/main/resources/files/requerimientos".toString() + "/requerimiento_" + date.getTime()+ ".xls"));
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
		} catch (IOException | EncryptedDocumentException| InvalidFormatException ex) {
			ex.printStackTrace();
		}
		return "Requerimiento file successfully generated";
	}
	

	
}
