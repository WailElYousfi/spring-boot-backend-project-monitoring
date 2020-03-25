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
public class RoleController {

    @Autowired
    FeatureService featureService;
    
    @Autowired
    RoleService roleService;

    @GetMapping("/roles/all")
    public ResponseEntity < List<Role> > getAllRoles() {
        return ResponseEntity.ok().body(roleService.getAllRoles());
    }

    @PostMapping("/roles")
    public ResponseEntity <Role> createRole(@RequestBody Role role, @RequestParam List<Integer> featureIds) {
    	List<Feature> features = new ArrayList<Feature>();
    	for (Integer featureId : featureIds)
			features.add(featureService.getFeatureById(featureId));
    	role.setFeatures(features);
        return ResponseEntity.ok().body(this.roleService.createRole(role));
    }

    @PostMapping("/roles/{id}/features")
    public ResponseEntity <Role> updateFeatures(@PathVariable Integer id, @RequestParam List<Integer> featureIds) {
    	Role role = roleService.getRoleById(id);
    	List<Feature> features = featureService.getAllFeaturesByIds(featureIds);
    	role.setFeatures(features);
        return ResponseEntity.ok().body(this.roleService.updateRole(role));
    }

    /*@DeleteMapping("/features/{id}")
    public HttpStatus deleteProject(@PathVariable Integer id) {
        this.projectService.deleteProject(id);
        return HttpStatus.OK;
    }*/
}