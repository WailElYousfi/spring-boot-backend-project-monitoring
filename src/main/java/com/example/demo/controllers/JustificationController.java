package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Justification;
import com.example.demo.models.Project;
import com.example.demo.models.User;
import com.example.demo.services.JustificationService;
import com.example.demo.services.ProjectService;
import com.example.demo.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JustificationController {

    @Autowired
    JustificationService justificationService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    ProjectService projectService;

    @GetMapping("/justifications/descendants/project/{projectid}/user/{userid}")
    public ResponseEntity < List<Justification> > getAllDescendantsJustification(@PathVariable Integer projectid, @PathVariable Long userid) {
        return ResponseEntity.ok().body(justificationService.getDescendantsJustifications(projectid, userid));
    }
    
    @GetMapping("/justifications/justificator/{justificatorid}")
    public ResponseEntity < List<Justification> > getMyJustifications(@PathVariable Long justificatorid) {
        return ResponseEntity.ok().body(justificationService.getJustificationsByJustificatorId(justificatorid));
    }

    @PostMapping("/justifications/justificator/{justificatorid}/project/{projectid}")
    public ResponseEntity <Justification> createJustification(@RequestBody Justification justification, @PathVariable Long justificatorid, @PathVariable Integer projectid) {
    	User justificator = userService.getUserById(justificatorid);
    	Project project = projectService.getProjectById(projectid);
    	justification.setJustificator(justificator);
    	justification.setProject(project);
        return ResponseEntity.ok().body(this.justificationService.createJustification(justification));
    }

    @PutMapping("/justifications/{id}")
    public ResponseEntity <Justification> updateJustification(@PathVariable Integer id, @RequestBody Justification justification) {
    	justification.setJustificationId(id);
        return ResponseEntity.ok().body(this.justificationService.updateJustification(justification));
    }
    
    @GetMapping("/justifications/{justificationid}/validate/{validatorid}")
    public ResponseEntity <Justification> validateJustification(@PathVariable Integer justificationid, @PathVariable Long validatorid) {
        return ResponseEntity.ok().body(this.justificationService.validateJustification(justificationid, validatorid));
    }
    
    
    @GetMapping("/justifications/{id}/archive")
    public ResponseEntity <Justification> archiveJustification(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.justificationService.archiveJustification(id));
    }
}