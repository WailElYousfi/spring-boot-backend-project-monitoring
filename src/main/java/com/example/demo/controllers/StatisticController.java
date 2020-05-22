package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.ERole;
import com.example.demo.models.User;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.services.IncidenceService;
import com.example.demo.services.ProjectService;
import com.example.demo.services.StatisticService;
import com.example.demo.services.TaskService;
import com.example.demo.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/statistics")
public class StatisticController {

    @Autowired
    StatisticService statisticService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    ProjectService projectService;

    @Autowired
    TaskService taskService;
    
    @Autowired
    IncidenceService incidenceService;
    
    @GetMapping("/efficiency/user/{userId}/{month}")
    public Float getEfficiencyByUserId(@PathVariable("userId") Long userId, @PathVariable("month") int month) throws Exception {
        return statisticService.getEfficiencyByUserIdOrProjectId(userId, null, month);
    }
    
    @GetMapping("/efficiency/project/{projectId}/{month}")
    public Float getEfficiencyByProjectId(@PathVariable("projectId") Integer projectId, @PathVariable("month") int month) throws Exception {
        return statisticService.getEfficiencyByUserIdOrProjectId(null, projectId, month);
    }
    
    @GetMapping("/retrabajo/{type}/user/{userId}/{month}")
    public Float getRetrabajoByUserId(@PathVariable("type") String type, @PathVariable("userId") Long userId, @PathVariable("month") int month) throws Exception {
        return statisticService.getRetrabajoByUserIdOrProjectId(userId, null, month, "Incidencia " + type);
    }
    
    @GetMapping("/retrabajo/{type}/project/{projectId}/{month}")
    public Float getRetrabajoByProjectId(@PathVariable("type") String type, @PathVariable("projectId") Integer projectId, @PathVariable("month") int month) throws Exception {
       	return statisticService.getRetrabajoByUserIdOrProjectId(null, projectId, month, "Incidencia " + type);
    }
    
    @GetMapping("/ratio/{type}/user/{userId}/{month}")
    public Float getRatioByUserId(@PathVariable("type") String type, @PathVariable("userId") Long userId, @PathVariable("month") int month) throws Exception {
        return statisticService.getRatioIncidenciasByUserIdOrPrjectId(userId, null, month, "Incidencia " + type);
    }
    
    @GetMapping("/ratio/{type}/project/{projectId}/{month}")
    public Float getRatioByProjectId(@PathVariable("type") String type, @PathVariable("projectId") Integer projectId, @PathVariable("month") int month) throws Exception {
        return statisticService.getRatioIncidenciasByUserIdOrPrjectId(null, projectId, month, "Incidencia " + type);
    }
    
    @GetMapping("/productividad/user/{userId}/{month}")
    public Float getProductividadByUserId(@PathVariable("userId") Long userId, @PathVariable("month")  int month) throws Exception {
        return statisticService.getProductividadByUserIdOrProjectId(userId, null, month);
    }
    
    @GetMapping("/productividad/project/{projectId}/{month}")
    public Float getProductividadByProjectId(@PathVariable("projectId") Integer projectId, @PathVariable("month") int month) throws Exception {
        return statisticService.getProductividadByUserIdOrProjectId(null, projectId, month);
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    @GetMapping("/efficiency/user/{userId}")
    public ResponseEntity < Map<Integer, Float> > getTotalEfficiencyByUserId(@PathVariable("userId") Long userId) throws Exception {
    	LocalDate now = LocalDate.now();
		Map<Integer, Float> response = new LinkedHashMap<Integer, Float>();
    	for(int i=0 ; i<7 ; i++)
    		response.put(now.minusMonths(i).getMonthValue(), getEfficiencyByUserId(userId, now.minusMonths(i).getMonthValue()));
        return ResponseEntity.ok().body(response);
    }
    
    @GetMapping("/efficiency/project/{projectId}")
    public ResponseEntity < Map<Integer, Float> > getTotalEfficiencyByProjectId(@PathVariable("projectId") Integer projectId) throws Exception {
    	LocalDate now = LocalDate.now();
		Map<Integer, Float> response = new LinkedHashMap<Integer, Float>();
    	for(int i=0 ; i<7 ; i++)
    		response.put(now.minusMonths(i).getMonthValue(), getEfficiencyByProjectId(projectId, now.minusMonths(i).getMonthValue()));
        return ResponseEntity.ok().body(response);
    }
    
    @GetMapping("/retrabajo/{type}/user/{userId}")
    public ResponseEntity < Map<Integer, Float> > getTotalRetrabajoByUserId(@PathVariable("type") String type, @PathVariable("userId") Long userId) throws Exception {
    	LocalDate now = LocalDate.now();
		Map<Integer, Float> response = new LinkedHashMap<Integer, Float>();
    	for(int i=0 ; i<7 ; i++)
    		response.put(now.minusMonths(i).getMonthValue(), getRetrabajoByUserId(type, userId, now.minusMonths(i).getMonthValue()));
        return ResponseEntity.ok().body(response);
    }
    
    @GetMapping("/retrabajo/{type}/project/{projectId}")
    public ResponseEntity < Map<Integer, Float> > getTotalRetrabajoByProjectId(@PathVariable("type") String type, @PathVariable("projectId") Integer projectId) throws Exception {
    	LocalDate now = LocalDate.now();
		Map<Integer, Float> response = new LinkedHashMap<Integer, Float>();
    	for(int i=0 ; i<7 ; i++)
    		response.put(now.minusMonths(i).getMonthValue(), getRetrabajoByProjectId(type, projectId, now.minusMonths(i).getMonthValue()));
        return ResponseEntity.ok().body(response);    
    }
    
    @GetMapping("/ratio/{type}/user/{userId}")
    public ResponseEntity < Map<Integer, Float> > getTotalRatioByUserId(@PathVariable("type") String type, @PathVariable("userId") Long userId) throws Exception {
    	LocalDate now = LocalDate.now();
		Map<Integer, Float> response = new LinkedHashMap<Integer, Float>();
    	for(int i=0 ; i<7 ; i++)
    		response.put(now.minusMonths(i).getMonthValue(), getRatioByUserId(type, userId, now.minusMonths(i).getMonthValue()));
        return ResponseEntity.ok().body(response);
    }
    
    @GetMapping("/ratio/{type}/project/{projectId}")
    public ResponseEntity < Map<Integer, Float> > getTotalRatioByProjectId(@PathVariable("type") String type, @PathVariable("projectId") Integer projectId) throws Exception {
    	LocalDate now = LocalDate.now();
		Map<Integer, Float> response = new LinkedHashMap<Integer, Float>();
    	for(int i=0 ; i<7 ; i++)
    		response.put(now.minusMonths(i).getMonthValue(), getRatioByProjectId(type, projectId, now.minusMonths(i).getMonthValue()));
        return ResponseEntity.ok().body(response);
    }
    
    @GetMapping("/productividad/user/{userId}")
    public ResponseEntity < Map<Integer, Float> > getTotalProductividadByUserId(@PathVariable("userId") Long userId) throws Exception {
    	LocalDate now = LocalDate.now();
		Map<Integer, Float> response = new LinkedHashMap<Integer, Float>();
    	for(int i=0 ; i<7 ; i++)
    		response.put(now.minusMonths(i).getMonthValue(), getProductividadByUserId(userId, now.minusMonths(i).getMonthValue()));
        return ResponseEntity.ok().body(response);
    }
    
    @GetMapping("/productividad/project/{projectId}")
    public ResponseEntity < Map<Integer, Float> > getTotalProductividadByProjectId(@PathVariable("projectId") Integer projectId) throws Exception {
    	LocalDate now = LocalDate.now();
		Map<Integer, Float> response = new LinkedHashMap<Integer, Float>();
    	for(int i=0 ; i<7 ; i++)
    		response.put(now.minusMonths(i).getMonthValue(), getProductividadByProjectId(projectId, now.minusMonths(i).getMonthValue()));
        return ResponseEntity.ok().body(response);
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
    @GetMapping("/user/{userId}")
    public ResponseEntity < Map<String, Map<Integer, Float>> > getStatisticsByUserId(@PathVariable("userId") Long userId) throws Exception {
    	LocalDate now = LocalDate.now();
		Map<String, Map<Integer, Float>> response = new LinkedHashMap<String, Map<Integer, Float>>();
		Map<Integer, Float> efficiency = new LinkedHashMap<Integer, Float>();
		Map<Integer, Float> retrabajoInterno = new LinkedHashMap<Integer, Float>();
		Map<Integer, Float> retrabajoExtreno = new LinkedHashMap<Integer, Float>();
		Map<Integer, Float> ratioInterno = new LinkedHashMap<Integer, Float>();
		Map<Integer, Float> ratioExterno = new LinkedHashMap<Integer, Float>();
		Map<Integer, Float> productividad = new LinkedHashMap<Integer, Float>();

    	for(int i=0 ; i<7 ; i++) {
    		efficiency.put(now.minusMonths(i).getMonthValue(), getEfficiencyByUserId(userId, now.minusMonths(i).getMonthValue()));
    		retrabajoInterno.put(now.minusMonths(i).getMonthValue(), getRetrabajoByUserId("interna", userId, now.minusMonths(i).getMonthValue()));
    		retrabajoExtreno.put(now.minusMonths(i).getMonthValue(), getRetrabajoByUserId("externa", userId, now.minusMonths(i).getMonthValue()));
    		ratioInterno.put(now.minusMonths(i).getMonthValue(), getRatioByUserId("interna", userId, now.minusMonths(i).getMonthValue()));
    		ratioExterno.put(now.minusMonths(i).getMonthValue(), getRatioByUserId("externa", userId, now.minusMonths(i).getMonthValue()));
    		productividad.put(now.minusMonths(i).getMonthValue(), getProductividadByUserId(userId, now.minusMonths(i).getMonthValue()));
    	}
    	response.put("efficiency", efficiency);
    	response.put("retrabajoInterno", retrabajoInterno);
    	response.put("retrabajoExterno", retrabajoExtreno);
    	response.put("ratioInterno", ratioInterno);
    	response.put("ratioExterno", ratioExterno);
    	response.put("productividad", productividad);
        return ResponseEntity.ok().body(response);
    }
    
    
    @GetMapping("/project/{projectId}")
    public ResponseEntity < Map<String, Map<Integer, Float>> > getStatisticsByProjectId(@PathVariable("projectId") Integer projectId) throws Exception {
    	LocalDate now = LocalDate.now();
		Map<String, Map<Integer, Float>> response = new LinkedHashMap<String, Map<Integer, Float>>();
		Map<Integer, Float> efficiency = new LinkedHashMap<Integer, Float>();
		Map<Integer, Float> retrabajoInterno = new LinkedHashMap<Integer, Float>();
		Map<Integer, Float> retrabajoExtreno = new LinkedHashMap<Integer, Float>();
		Map<Integer, Float> ratioInterno = new LinkedHashMap<Integer, Float>();
		Map<Integer, Float> ratioExterno = new LinkedHashMap<Integer, Float>();
		Map<Integer, Float> productividad = new LinkedHashMap<Integer, Float>();

    	for(int i=0 ; i<7 ; i++) {
    		efficiency.put(now.minusMonths(i).getMonthValue(), getEfficiencyByProjectId(projectId, now.minusMonths(i).getMonthValue()));
    		retrabajoInterno.put(now.minusMonths(i).getMonthValue(), getRetrabajoByProjectId("interna", projectId, now.minusMonths(i).getMonthValue()));
    		retrabajoExtreno.put(now.minusMonths(i).getMonthValue(), getRetrabajoByProjectId("externa", projectId, now.minusMonths(i).getMonthValue()));
    		ratioInterno.put(now.minusMonths(i).getMonthValue(), getRatioByProjectId("interna", projectId, now.minusMonths(i).getMonthValue()));
    		ratioExterno.put(now.minusMonths(i).getMonthValue(), getRatioByProjectId("externa", projectId, now.minusMonths(i).getMonthValue()));
    		productividad.put(now.minusMonths(i).getMonthValue(), getProductividadByProjectId(projectId, now.minusMonths(i).getMonthValue()));
    	}
    	response.put("efficiency", efficiency);
    	response.put("retrabajoInterno", retrabajoInterno);
    	response.put("retrabajoExterno", retrabajoExtreno);
    	response.put("ratioInterno", ratioInterno);
    	response.put("ratioExterno", ratioExterno);
    	response.put("productividad", productividad);
    	
        return ResponseEntity.ok().body(response);
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    @GetMapping("/user/{userId}/project/{projectId}")
    public ResponseEntity < List<Map<String, Map<Integer, Float>>> > geStatisticsByUserIdAndProjectId(@PathVariable("userId") Long userId, @PathVariable("projectId") Integer projectId) throws Exception {
    	List<Map<String, Map<Integer, Float>>> response = new ArrayList<Map<String,Map<Integer,Float>>>();
    	response.add(getStatisticsByUserId(userId).getBody());
    	response.add(getStatisticsByProjectId(projectId).getBody());
        return ResponseEntity.ok().body(response);
    }
    
    @GetMapping("/projects")
    public ResponseEntity < List<Map<String, Map<Integer, Float>>> > geStatisticsByprojectIds(@RequestParam List<Integer> projectIds) throws Exception {
    	List<Map<String, Map<Integer, Float>>> response = new ArrayList<Map<String,Map<Integer,Float>>>();
    	for (Integer projectId : projectIds)
        	response.add(getStatisticsByProjectId(projectId).getBody());
        return ResponseEntity.ok().body(response);
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @GetMapping("/admin")
    public ResponseEntity < Map<String, Integer> > geAdminStatistics() throws Exception {
    	Map<String, Integer> response = new LinkedHashMap<String, Integer>();
    	response.put("countUsers", userService.countUsers());
    	response.put("countProjects", projectService.countProjects());
    	response.put("countJuniors", userService.countUsersByRole(ERole.ROLE_JUNIOR));
    	response.put("countDevelopers", userService.countUsersByRole(ERole.ROLE_DEVELOPER));
    	response.put("countSeniors", userService.countUsersByRole(ERole.ROLE_SENIOR));
    	response.put("countTeamleaders", userService.countUsersByRole(ERole.ROLE_TEAMLEADER));
    	response.put("countSupervisors", userService.countUsersByRole(ERole.ROLE_SUPERVISOR));
    	response.put("countAdmins", userService.countUsersByRole(ERole.ROLE_ADMIN));
        return ResponseEntity.ok().body(response);
    }
    
    
}