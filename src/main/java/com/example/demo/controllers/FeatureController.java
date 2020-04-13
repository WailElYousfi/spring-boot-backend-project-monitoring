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

import com.example.demo.models.Feature;
import com.example.demo.models.Role;
import com.example.demo.services.FeatureService;
import com.example.demo.services.RoleService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FeatureController {

    @Autowired
    FeatureService featureService;
    
    @Autowired
    RoleService roleService;

    @GetMapping("/features/all")
    public ResponseEntity < List<Feature> > getAllFeature() {
        return ResponseEntity.ok().body(featureService.getAllFeatures());
    }

    @PostMapping("/features")
    public ResponseEntity <Feature> createFeature(@RequestBody Feature feature) {
        return ResponseEntity.ok().body(this.featureService.createFeature(feature));
    }

    /*@PutMapping("/features/{id}")
    public ResponseEntity <Project> updateProject(@PathVariable Integer id, @RequestBody Project project) {
        project.setProjectId(id);
        return ResponseEntity.ok().body(this.projectService.updateProject(project));
    }

    @DeleteMapping("/features/{id}")
    public HttpStatus deleteProject(@PathVariable Integer id) {
        this.projectService.deleteProject(id);
        return HttpStatus.OK;
    }*/
}