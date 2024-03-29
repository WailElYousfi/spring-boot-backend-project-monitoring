package com.example.demo.controllers;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.IncidenceFilterDto;
import com.example.demo.dto.taskFilterDto;
import com.example.demo.models.Incidence;
import com.example.demo.models.Task;
import com.example.demo.services.FileService;
import com.example.demo.services.IncidenceService;


@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {
	
	@Autowired
	FileService fileService;
	
	@Autowired
	IncidenceService incidenceService;
	
	///////////////////////////////////// ACC ////////////////////////////////////////////
	
	@PostMapping("/acc/data")
	public ResponseEntity < List<Task> > getDataTaskFile(@RequestParam("file") MultipartFile file) throws Exception{
		return ResponseEntity.ok().body(fileService.getDataAccFile(file));
	}
	
	@PostMapping("/acc/upload")
	public ResponseEntity < String > uploadTasksAcc(@RequestBody List<Task> tasks) throws Exception{
		return ResponseEntity.ok().body(fileService.uploadTasks(tasks));
	}
	
	@RequestMapping(value = "/acc/generate", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity < Map<String, String> > generateAccFile(@RequestBody taskFilterDto filter) throws Exception{
		Map<String, String> response = new HashMap<String, String>();
		response.put("body", fileService.generateAccFile(filter));
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/acc/download")
	public void downloadAccFile(String fileName, HttpServletResponse res) throws Exception {
		res.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		res.getOutputStream().write(contentOf(fileName, "accs"));
	}

	////////////////////////////////////// Requerimiento ///////////////////////////////////
	
	
	@PostMapping("/requerimiento/data")
	public ResponseEntity < List<Task> > getDataRequerimientoFile(@RequestParam("file") MultipartFile file) throws Exception{
//		Integer idOt= 1396004;
		return ResponseEntity.ok().body(fileService.getDataRequerimientoFile(file));
	}
	
	@PostMapping("/requerimiento/upload")
	public ResponseEntity < String > uploadTasks(@RequestBody List<Task> tasks) throws Exception{
		return ResponseEntity.ok().body(fileService.uploadTasks(tasks));
	}
	
	
	@RequestMapping(value = "/requerimiento/generate", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity < Map<String, String> > generateRequerimientoFile(@RequestBody taskFilterDto filter) throws Exception{
		Map<String, String> response = new HashMap<String, String>();
		response.put("body", fileService.generateRequerimientoFile(filter));
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/requerimiento/download")
	public void downloadRequerimientoFile(String fileName, HttpServletResponse res) throws Exception {
		res.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		res.getOutputStream().write(contentOf(fileName, "requerimientos"));
	}
	
	////////////////////////// incidencias /////////////////////////////////
	
	@PostMapping("/incidences/data")
	public ResponseEntity < List<Incidence> > getDataIncidenceFile(@RequestParam("file") MultipartFile file) throws Exception{
		return ResponseEntity.ok().body(fileService.getDataIncidenceFile(file));
	}
	
	@PostMapping("/incidences/upload")
	public ResponseEntity < String > uploadIncidences(@RequestBody List<Incidence> incidences) throws Exception{
		return ResponseEntity.ok().body(fileService.uploadIncidences(incidences));
	}
	
	@GetMapping("/incidences/getUsernames")
	public ResponseEntity < List<String> > getUsernames(@RequestParam(value="startDate", required=true) String startDate,
														@RequestParam(value="endDate", required=true) String endDate) throws Exception{
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
		Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		return ResponseEntity.ok().body(incidenceService.getUsernamesOfIncidencesByDates(date1, date2));
	}
	
	@RequestMapping(value = "/incidences/generate", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity < Map<String, String> > generateIncidencesFile(@RequestBody IncidenceFilterDto filter) throws Exception{
		Map<String, String> response = new HashMap<String, String>();
		response.put("body", fileService.generateIncidencesFile(filter));
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/incidences/download")
	public void downloadIncidenciaFile(String fileName, HttpServletResponse res) throws Exception {
		res.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		res.getOutputStream().write(contentOf(fileName, "incidencias"));
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private byte[] contentOf(String fileName, String type) throws Exception {
		
		return Files.readAllBytes( Paths.get(System.getProperty("user.dir")+"/src/main/resources/files/"+type+"/" + fileName));
	}
	
}
