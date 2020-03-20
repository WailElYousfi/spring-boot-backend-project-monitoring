package com.example.demo.services;


import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
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
	
	
	public List<Task> getDataTaskFile(MultipartFile file) throws Exception {	
		Path tempDir = Files.createTempDirectory("");
		File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
		file.transferTo(tempFile);		
		XSSFWorkbook workbook = new XSSFWorkbook(tempFile); 
		XSSFSheet sheet = workbook.getSheetAt(0); 
        Row row;
        
        Type incidenciaInternaType = typeService.getTypeByName("Tarea cargable");
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		List<String> originalColumns = new ArrayList<String>();
		List<String> fileColumns = new ArrayList<String>();
		
		for (Equivalence colonne : incidenciaInternaType.getColonnes())
			if(colonne.getJiraEquivalence() != null)
				originalColumns.add(colonne.getJiraEquivalence());
		
		List<String> originalColumnWithoutDuplicates =  originalColumns.stream().distinct().collect(Collectors.toList()); // Remove duplicates if exists
		
		for(int j=0; j < sheet.getRow(0).getLastCellNum(); j++)
			fileColumns.add(sheet.getRow(0).getCell(j).toString());
		
		if(!fileColumns.containsAll(originalColumnWithoutDuplicates)) {
	        workbook.close();
			throw new ResourceNotFoundException("Columns not found in this file !!");
		}
		
		for(int j=0; j < sheet.getRow(0).getLastCellNum(); j++)
			for(Equivalence colonne : incidenciaInternaType.getColonnes())
				if(sheet.getRow(0).getCell(j).toString().equals(colonne.getJiraEquivalence()))
					hm.put(colonne.getColumnName(), j);
		
		List<Task> taskList = new ArrayList<Task>();
        for(int i=1; i <= sheet.getLastRowNum(); i++){  
        	row = (Row) sheet.getRow(i);
        	if(row.getCell(1).toString().isEmpty())
            	break; 
			Task task = new Task();
			task.setProject(projectService.getProjectByName(row.getCell(hm.get("project")).toString()));
			task.setKey(row.getCell(hm.get("key")).toString());
			task.setTitle(row.getCell(hm.get("title")).toString());
			task.setDescription(row.getCell(hm.get("description")).toString());
			task.setFixVersion(row.getCell(hm.get("fixVersion")).toString());
			task.setOriginalEstimate(row.getCell(hm.get("originalEstimate")).toString());
			task.setRemainingEstimate(row.getCell(hm.get("remainingEstimate")).toString());
			task.setStatus(row.getCell(hm.get("status")).toString());
			task.setTimeSpent(row.getCell(hm.get("timeSpent")).toString());
			task.setTaskType(row.getCell(hm.get("taskType")).toString());
			task.setDate(new Date());
			task.setUser(userService.getUserByJiraUsername(row.getCell(hm.get("user")).toString()));
			task.setComment(row.getCell(hm.get("comment")).toString());
			task.setFileType(typeService.getTypeByName("Tarea cargable"));
			
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
	
	

	public String generateTasksFile(Date startDate, Date endDate, long ot) throws Exception {
		Optional < List<Task> > result = taskRepository.findByDate(startDate, endDate);
		if(!result.isPresent()) {
			throw new ResourceNotFoundException("No task is found in these dates !!!");
		}else {
			List<Task> tasks = result.get();
			try {
				String path = Paths.get("").toAbsolutePath().toString() + "\\src\\main\\resources\\files\\tasks\\";
				XSSFWorkbook workbook = new XSSFWorkbook();
				Date date = new Date();
				String fileName = Long.toString(date.getTime());
				FileOutputStream out = new FileOutputStream(new File(path + fileName +".xlsx"));
				Sheet sheet = workbook.createSheet("Sheet1");
	
		        CreationHelper createHelper = workbook.getCreationHelper();
	
		        // Create a Font for styling header cells
		        Font headerFont = workbook.createFont();
		        headerFont.setBold(true);
		        headerFont.setFontHeightInPoints((short) 13);
		        headerFont.setColor(IndexedColors.WHITE.getIndex());	
		        
		        // Create a CellStyle
		        CellStyle headerCellStyle = workbook.createCellStyle();
		        headerCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		        headerCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		        headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		        headerCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		        headerCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		        headerCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		        headerCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		        headerCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		        headerCellStyle.setFont(headerFont);
	
		        // Create a Row
		        Row headerRow = sheet.createRow(0);
	
		        // Create cells
		        String[] columns = {"ID ACC", "Nombre", "Código Petición Cliente", "Descripción", "Estado", "Tipo", "Pet/Ot. Asociada", "Responsable", "Subtipo", "Rechazos Entrega",
		        					"Criticidad", "Esfuerzo", "Esfuerzo Cliente", "Fecha Creación", "Fecha Solicitud Cliente", "Fecha Prevista Proyecto", "Fecha Entrega", "Fecha Cierre",
		        					"Fecha Desestimación", "Fecha Inicio Centro", "Resultado Testing", "Puntos Historia", "Historia Usuario", "Épica"};
		        for(int i = 0; i < columns.length; i++) {
		            Cell cell = headerRow.createCell(i);
		            cell.setCellValue(columns[i]);
		            cell.setCellStyle(headerCellStyle);
		        }
	
		        // Create Cell Style for formatting Date
		        CellStyle dateCellStyle = workbook.createCellStyle();
		        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy HH:mm:ss"));
		        
		        // Create Other rows and cells with employees data
		        int rowNum = 1;
		        for(Task task: tasks) {		     
		        	Row row = sheet.createRow(rowNum++);
			        row.createCell(1).setCellValue(task.getTitle());
			        row.createCell(3).setCellValue(task.getDescription());
			        row.createCell(4).setCellValue("En Ejecución");
			        row.createCell(5).setCellValue("Evolutivo(ENP)");
			        row.createCell(6).setCellValue(ot);
			        row.createCell(7).setCellValue(task.getUser().getUserCode());
			        row.createCell(8).setCellValue(task.getTaskType());
			        row.createCell(9).setCellValue(0);
			        row.createCell(10).setCellValue("Media");
			            
			        String originalEstimateString = StringUtils.chop(task.getOriginalEstimate());
			        float originalEstimateFloat = Float.parseFloat(originalEstimateString);
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
	
				// Resize all columns to fit the content size
		        for(int i = 0; i < columns.length; i++) {
		            sheet.autoSizeColumn(i);
		        }
		        // Write the output to a file
		        workbook.write(out);
		        out.close();
		        workbook.close();		
			}catch(Exception e) {
				System.out.println(e);
			}
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
		        excelStructureService.createFont(headerFont);
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
	
	
}
