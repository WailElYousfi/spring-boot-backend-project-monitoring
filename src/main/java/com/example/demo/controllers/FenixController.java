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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.IncidenceFilterDto;
import com.example.demo.dto.taskFilterDto;
import com.example.demo.models.Incidence;
import com.example.demo.models.Task;
import com.example.demo.services.FenixService;
import com.example.demo.services.FileService;
import com.example.demo.services.IncidenceService;


@RestController
@RequestMapping("/fenix")
@CrossOrigin(origins = "http://localhost:4200")
public class FenixController {
	@Autowired
	FenixService fenixService;
	
	
	@PostMapping("/creartarea")
	public ResponseEntity < String > crearTarea(@RequestParam("file") MultipartFile file) throws Exception{
		Map<Integer, Map<String, String>> hmap = fenixService.crearTarea(file);
		String uri = "http://fenix.car.int/fenix/actuaciones/guardarACCAction.dojo?perform=Crearacc";
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br");

		for(int i=1; i <= hmap.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idACC", "");
			map.put("tipoEstado", "");
			map.put("esFunctional", true);
			map.put("esCentro", false);
			map.put("edicion", false);
			map.put("esVaciaOT", true);
			map.put("nombre", hmap.get(i).get("nombre"));
			map.put("description", hmap.get(i).get("description"));
			map.put("tipo", hmap.get(i).get("tipo"));
			map.put("subtipo", hmap.get(i).get("subtipo"));
			map.put("codPetCliente", "");
			map.put("petAsociada", hmap.get(i).get("petAsociada"));
			map.put("criticidad", "MEDIA");
			map.put("fechaSolicitudCliente", "");
			map.put("esfuerzo", hmap.get(i).get("esfuerzo"));
			map.put("esfuerzoCliente", "");
			map.put("fechaPrevEntrega", "");
			map.put("fechaIniCentro", "");
			map.put("resultadoTesting", "-1");
			
			HttpEntity<Map<String, Object>> request = new HttpEntity<>(map, headers);
			ResponseEntity<?> response = restTemplate.postForObject(uri, request, null);
			if (response.getStatusCode() == HttpStatus.OK)
		        System.out.println("Request Successful");
		    else
		        System.out.println("Request Failed");
		}
		return ResponseEntity.ok().body("Successfull");

	}
	
	
	
	
}
