package com.example.demo.services;


import java.io.File;
import java.io.FileOutputStream;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
	ExcelStructureService excelStructureService;
	
	
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
		XSSFWorkbook workbook = new XSSFWorkbook(tempFile); 
		XSSFSheet sheet = workbook.getSheetAt(0); 
        Row row;
        
		List<Incidence> incidenceList = new ArrayList<Incidence>();
		Type incidenciaInternaType = typeService.getTypeByName("Incidencia interna");
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		List<String> originalColumns = new ArrayList<String>();
		List<String> fileColumns = new ArrayList<String>();
		
		for (Equivalence colonne : incidenciaInternaType.getColonnes())
			if(colonne.getJiraEquivalence() != null)
				originalColumns.add(colonne.getJiraEquivalence());
		
		List<String> originalColumnWithoutDuplicates =  originalColumns.stream().distinct().collect(Collectors.toList()); // Remove duplicates
		
		for(int j=0; j < sheet.getRow(3).getLastCellNum(); j++)
			fileColumns.add(sheet.getRow(3).getCell(j).toString());
		
		if(!fileColumns.containsAll(originalColumnWithoutDuplicates)) {
	        workbook.close();
			throw new ResourceNotFoundException("Columns not found in this file !!");
		}
		
		for(int j=0; j < sheet.getRow(3).getLastCellNum(); j++)
			for(Equivalence colonne : incidenciaInternaType.getColonnes())
				if(sheet.getRow(3).getCell(j).toString().equals(colonne.getJiraEquivalence()))
					hm.put(colonne.getColumnName(), j);
					
		for(int i=4; i <= sheet.getLastRowNum()-1; i++){        	               	
            row = (Row) sheet.getRow(i);  //sheet number
			Incidence incidence = new Incidence();
			incidence.setKey(row.getCell(hm.get("key")).toString());
			incidence.setStatus(row.getCell(hm.get("status")).toString());
			incidence.setTimeSpent(row.getCell(hm.get("timeSpent")).toString());
			incidence.setAssignedUser(userService.getUserByJiraUsername(row.getCell(hm.get("assignedUser")).toString()));
			incidence.setCausedUser(userService.getUserByJiraUsername(row.getCell(hm.get("causedUser")).toString()));
			incidence.setSummary(row.getCell(hm.get("summary")).toString());
			incidence.setOriginalEstimate(row.getCell(hm.get("originalEstimate")).toString());
			incidence.setDescription(row.getCell(hm.get("description")).toString());
			incidence.setLinkedIssues(row.getCell(hm.get("linkedIssues")).toString());
			incidence.setJiraSas(row.getCell(hm.get("jiraSas")).toString());
			//incidence.setComment(row.getCell(hm.get("comment")).toString());
			//if(row.getCell(hm.get("created")).getCellType()== Cell.CELL_TYPE_STRING) {
			//	Date date1=new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(row.getCell(hm.get("created")).toString());
			//	incidence.setCreated(date1);
			//}else 
			//	incidence.setCreated(row.getCell(hm.get("created")).getDateCellValue());	
			
			incidence.setIncidenceType(row.getCell(hm.get("incidenceType")).toString());
			if(row.getCell(hm.get("updated")).getCellType()== Cell.CELL_TYPE_STRING) {
				Date date2=new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(row.getCell(3).toString());
				incidence.setUpdated(date2);
			}else 
				incidence.setUpdated(row.getCell(hm.get("updated")).getDateCellValue());	
			
			if(row.getCell(hm.get("resolved")).getCellType()== Cell.CELL_TYPE_STRING) {
				Date date3=new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(row.getCell(8).toString());
				incidence.setResolved(date3);
			}else 
				incidence.setResolved(row.getCell(hm.get("resolved")).getDateCellValue());	
			
			incidence.setProject(projectService.getProjectByName(row.getCell(hm.get("project")).toString()));
			
			if(row.getCell(hm.get("summary")).toString().contains("INC_INT"))
				incidence.setFileType(typeService.getTypeByName("Incidencia interna"));
			else
				incidence.setFileType(typeService.getTypeByName("Incidencia externa"));
			
			incidence.setDate(new Date());
			
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
		List<Task> tasks = taskService.getTaskByDatesAndIdOt(startDate, endDate, filter.getIdOt());
			try {
				XSSFWorkbook workbook = new XSSFWorkbook();
				Date date = new Date();
				String fileName = Long.toString(date.getTime());
				FileOutputStream out = new FileOutputStream(new File(Paths.get(getClass().getClassLoader().getResource("files/tasks").toURI()).toString() + "/" + fileName +".xls"));
				Sheet sheet = workbook.createSheet("Sheet1");
	
		        CreationHelper createHelper = workbook.getCreationHelper();
	
		        Font headerFont = workbook.createFont();
		        excelStructureService.createFont(headerFont, "white");		    
		        CellStyle headerCellStyle = workbook.createCellStyle();
		        excelStructureService.setCellStyle(headerCellStyle, "green", headerFont);
	
		        Row headerRow = sheet.createRow(0);		        
		        Type taskType = typeService.getTypeByName("Tarea cargable");
				HashMap<Integer, String> columnsName = new HashMap<Integer, String>();
				
				for (Equivalence colonne : taskType.getColonnes())
					if(colonne.getFenixEquivalence() != null)
						columnsName.put(colonne.getColumnOrder(), colonne.getFenixEquivalence());		    
			      
		        excelStructureService.setHeader(columnsName, headerRow, headerCellStyle, headerCellStyle);
	
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
		Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse(filter.getStartDate());
		Date endDate=new SimpleDateFormat("yyyy-MM-dd").parse(filter.getEndDate());
		Optional < List<Incidence> > result = incidenceRepository.findByDate(startDate, endDate);
		if(!result.isPresent()) {
			throw new ResourceNotFoundException("No incidence is found in these dates !!!");
		}else {
			List<Incidence> incidences = result.get();
			try {
				//String path = Paths.get("").toAbsolutePath().toString() + "\\src\\main\\resources\\files\\incidences\\";
				XSSFWorkbook workbook = new XSSFWorkbook();
				Date date = new Date();
				String fileName = Long.toString(date.getTime());
				FileOutputStream out = new FileOutputStream(new File(Paths.get(getClass().getClassLoader().getResource("files/incidences").toURI()).toString() + "/" + fileName +".xls"));
				Sheet sheet = workbook.createSheet("Sheet1");
	
		        CreationHelper createHelper = workbook.getCreationHelper();
		        Font headerFont = workbook.createFont();
		        excelStructureService.createFont(headerFont, "white");
		        CellStyle headerCellStyle = workbook.createCellStyle();		       		        
		        CellStyle headerCellStyleGreen = workbook.createCellStyle();		       
		        excelStructureService.setCellStyle(headerCellStyle, "red", headerFont);
		        excelStructureService.setCellStyle(headerCellStyleGreen, "green", headerFont);
		        Row rw = sheet.createRow(1);
		        sheet.addMergedRegion(new CellRangeAddress(1,1,1,7));
		        Cell mergedCell = rw.createCell(1);
		        mergedCell.setCellValue("Campos obligatorios para la Creación");		    
		        CellStyle headerCellStyle2 = workbook.createCellStyle();		       
		        headerCellStyle2.setAlignment(CellStyle.ALIGN_CENTER);
		        excelStructureService.setCellStyle(headerCellStyle2, "black", headerFont);
		        mergedCell.setCellStyle(headerCellStyle2);		        

				Type incidenciaInternaType = typeService.getTypeByName("Incidencia interna");
				HashMap<Integer, String> columnsName = new HashMap<Integer, String>();
				
				for (Equivalence colonne : incidenciaInternaType.getColonnes())
					if(colonne.getFenixEquivalence() != null)
						columnsName.put(colonne.getColumnOrder(), colonne.getFenixEquivalence());		    
	
		        Row headerRow = sheet.createRow(2);
		        excelStructureService.setHeader(columnsName, headerRow, headerCellStyle, headerCellStyleGreen);        
		        Row headerRow2 = sheet.createRow(3);
		        excelStructureService.setHeader2(columnsName, headerRow2, headerCellStyle, headerCellStyleGreen);		  		        	        		 		        		 		        
		        List<Row> rows = new ArrayList<Row>();
		        rows.add(headerRow2);
		        for (int i = 4; i <= 13; i++)
		        	rows.add(sheet.createRow(i));
		        rows.add(headerRow);
		       
		        excelStructureService.seedAdditionalCells(rows, columnsName);
		        		       		        
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
			    	c.add(Calendar.MONTH, 2);// after 1 day			 
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
		        workbook.write(out);
		        out.close();
		        workbook.close();	
			return fileName;
			}catch(Exception e) {
				throw new ResourceNotFoundException(e.getMessage());
			}
		}
	}
	
	public List<String> getUsernames(Date startDate, Date endDate){
		Optional < List<Incidence> > result = incidenceRepository.findByDate(startDate, endDate);
		List<String> usernames = new ArrayList<String>();
		if(!result.isPresent()) {
			throw new ResourceNotFoundException("No incidence is found in these dates !!!");
		}else {
			List<Incidence> incidences = result.get();
			for (Incidence incidence : incidences)
				usernames.add(incidence.getCausedUser().getJiraUsername());
			return usernames.stream().distinct().collect(Collectors.toList()); // Remove duplicates
		}
	}
	
	
	public List<Task> getDataRequerimientoFile(MultipartFile file, Integer idOt) throws Exception {	
		Path tempDir = Files.createTempDirectory("");
		File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
		file.transferTo(tempFile);		
		XSSFWorkbook workbook = new XSSFWorkbook(tempFile); 
		XSSFSheet sheet = workbook.getSheetAt(0); 
        Row row;
        
        Type requerimientoType = typeService.getTypeByName("Requerimiento");
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		Set<String> originalColumns = new HashSet<String>();
		List<String> fileColumns = new ArrayList<String>();
		
		for (Equivalence colonne : requerimientoType.getColonnes())
			if(colonne.getJiraEquivalence() != null)
				originalColumns.add(colonne.getJiraEquivalence());
				
		for(int j=0; j < sheet.getRow(3).getLastCellNum(); j++)
			fileColumns.add(sheet.getRow(3).getCell(j).toString());	//get file columns
		
		if(!fileColumns.containsAll(originalColumns)) {
	        workbook.close();
			throw new ResourceNotFoundException("Columns not found in this file !!");
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
			
			String originalEstimate = null;
			if(!row.getCell(hm.get("originalEstimate")).toString().isEmpty()) {
				double originalEstimateInHours = ((double) row.getCell(hm.get("originalEstimate")).getNumericCellValue()) / 3600;
				DecimalFormat df = new DecimalFormat("0.0");
				originalEstimate = df.format(originalEstimateInHours);
			}
			String remainingEstimate = null;
			if(!row.getCell(hm.get("remainingEstimate")).toString().isEmpty()) {
				double remainingEstimateInHours = ((double) row.getCell(hm.get("remainingEstimate")).getNumericCellValue()) / 3600;
				DecimalFormat df = new DecimalFormat("0.0");
				remainingEstimate = df.format(remainingEstimateInHours);
			}
			String timeSpent = null;
			if(!row.getCell(hm.get("timeSpent")).toString().isEmpty()) {
				double timeSpentInHours = ((double) row.getCell(hm.get("timeSpent")).getNumericCellValue()) / 3600;
				DecimalFormat df = new DecimalFormat("0.0");
				timeSpent = df.format(timeSpentInHours);
			}
			
			task.setOriginalEstimate(originalEstimate);
			task.setRemainingEstimate(remainingEstimate);
			task.setStatus(row.getCell(hm.get("status")).toString());
			task.setTimeSpent(timeSpent);
			task.setTaskType(row.getCell(hm.get("taskType")).toString());
			task.setDate(new Date());
			task.setAssignedUser(userService.getUserByJiraUsernameOrNull(row.getCell(hm.get("assignedUser")).toString())); //return user or null
			task.setFileType(requerimientoType);
			
			if(row.getCell(hm.get("created"))==null || row.getCell(hm.get("created")).getCellTypeEnum()== CellType.BLANK)
				task.setCreated(null);
			else
				task.setCreated(row.getCell(hm.get("created")).getDateCellValue());
			
			if(row.getCell(hm.get("updated"))==null || row.getCell(hm.get("updated")).getCellTypeEnum()== CellType.BLANK)
				task.setUpdated(null);
			else
				task.setUpdated(row.getCell(hm.get("updated")).getDateCellValue());
			
			if(row.getCell(hm.get("resolved"))==null || row.getCell(hm.get("resolved")).getCellTypeEnum()== CellType.BLANK)
				task.setResolved(null);
			else
				task.setResolved(row.getCell(hm.get("resolved")).getDateCellValue());
			
			task.setIdOt(idOt);
			
			taskList.add(task);
        }
        workbook.close();
        return taskList;
    }
	
	
	public String generateRequerimientoFile(taskFilterDto filter) throws Exception {
		Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse(filter.getStartDate());
		Date endDate=new SimpleDateFormat("yyyy-MM-dd").parse(filter.getEndDate());
		List<Task> tasks = taskService.getTaskByDatesAndIdOt(startDate, endDate, filter.getIdOt());
			try {
				XSSFWorkbook workbook = new XSSFWorkbook();
				Date date = new Date();
				String fileName = Long.toString(date.getTime());
				FileOutputStream out = new FileOutputStream(new File(Paths.get(getClass().getClassLoader().getResource("files/tasks").toURI()).toString() + "/" + fileName +".xls"));
				Sheet sheet = workbook.createSheet("Sheet1");
	
		        CreationHelper createHelper = workbook.getCreationHelper();
	
		        Font row0font = workbook.createFont();
		        excelStructureService.createFont(row0font, "white");		    
		        CellStyle blackStyle = workbook.createCellStyle();
		        CellStyle greenStyle = workbook.createCellStyle();
		        CellStyle redStyle = workbook.createCellStyle();
		        excelStructureService.setCellStyle(blackStyle, "black", row0font);
		        excelStructureService.setCellStyle(greenStyle, "green", row0font);
		        excelStructureService.setCellStyle(redStyle, "red", row0font);
		        Row row0 = sheet.createRow(0);	 
		        excelStructureService.setMergedCells(sheet, 1, 4, row0, blackStyle, "Campos obligatorios para la Creación");
		        excelStructureService.setMergedCells(sheet, 7, 13, row0, greenStyle, "Campos solo lectura");
			      
		        Type taskType = typeService.getTypeByName("Requerimiento");
				HashMap<Integer, String> columnsName = new HashMap<Integer, String>();
				
				for (Equivalence colonne : taskType.getColonnes())
					if(colonne.getFenixEquivalence() != null)
						columnsName.put(colonne.getColumnOrder(), colonne.getFenixEquivalence());		    
			      
				Row row1 = sheet.createRow(1);
		        excelStructureService.setHeader(columnsName, row1, 1, 4, redStyle, greenStyle);			        
		        
	        	List<String> names = new ArrayList<String>();
		        for (Task task : tasks)
		        	names.add(task.getSummary());
		        java.util.Collections.sort(names); // sort alphabetically?
		        
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
			        row.createCell(7).setCellValue(task.getIdOt());			        		        	        
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

	
}
