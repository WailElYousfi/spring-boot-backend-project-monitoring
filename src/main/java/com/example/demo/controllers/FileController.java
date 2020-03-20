package com.example.demo.controllers;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.example.demo.models.Incidence;
import com.example.demo.models.Task;
import com.example.demo.services.FileService;


@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {
	
	@Autowired
	FileService fileService;

	///////////////// tareas //////////////
	
	@PostMapping("/tasks/data")
	public ResponseEntity < List<Task> > getDataTaskFile(@RequestParam("file") MultipartFile file) throws Exception{
		return ResponseEntity.ok().body(fileService.getDataTaskFile(file));
	}
	
	@PostMapping("/tasks/upload")
	public ResponseEntity < String > uploadTasks(@RequestBody List<Task> tasks) throws Exception{
		return ResponseEntity.ok().body(fileService.uploadTasks(tasks));
	}
	
	@GetMapping("/tasks/generate")
	public ResponseEntity < String > generateTasksFile(@RequestParam(value="startDate", required=true) String startDate,
											  @RequestParam(value="endDate", required=true) String endDate,
											  @RequestParam(value="ot", required=true) long ot) throws Exception{
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
		Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		return ResponseEntity.ok().body(fileService.generateTasksFile(date1, date2, ot));
	}
	
	//////////// incidencias ////////////
	
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
		return ResponseEntity.ok().body(fileService.getUsernames(date1, date2));
	}
	
	@RequestMapping(value = "/incidences/generate", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity < String > generateIncidencesFile(@RequestBody IncidenceFilterDto filter) throws Exception{
		return ResponseEntity.ok().body(fileService.generateIncidencesFile(filter));
	}
	
	@GetMapping("/incidences/download")
	public void downloadFile(String fileName, HttpServletResponse res) throws Exception {
		res.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		res.getOutputStream().write(contentOf(fileName));
	}
	
	private byte[] contentOf(String fileName) throws Exception {
		return Files.readAllBytes( Paths.get(getClass().getClassLoader().getResource("files/incidences/" + fileName).toURI()));
	}
	
}