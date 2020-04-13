package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.example.demo.dao.RoleFeatureRepository;
import com.example.demo.dto.FeaturesDto;
import com.example.demo.models.Accesslevel;
import com.example.demo.models.Feature;
import com.example.demo.models.Role;
import com.example.demo.models.RoleFeature;
import com.example.demo.services.AccesslevelService;
import com.example.demo.services.FeatureService;
import com.example.demo.services.RoleService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController {

    @Autowired
    FeatureService featureService;
    
    @Autowired
    AccesslevelService accesslevelService;
    
    @Autowired
    RoleService roleService;
    
    @Autowired
    RoleFeatureRepository rolefeaturerepository;

    @GetMapping("/roles/all")
    public ResponseEntity < List<Role> > getAllRoles() {
        return ResponseEntity.ok().body(roleService.getAllRoles());
    }

   /* @PostMapping("/roles")
    public ResponseEntity <Role> createRole(@RequestBody Role role, @RequestParam Map<Integer, Integer> featuresAndAccesslevels) {
    	Set<RoleFeature> rolefeatures = new HashSet<RoleFeature>();    	
    	featuresAndAccesslevels.forEach((featureId, accesslevelId) -> {
    		Feature feature = featureService.getFeatureById(featureId);
    		Accesslevel accesslevel = accesslevelService.getAccesslevelById(accesslevelId);
    		rolefeatures.add(new RoleFeature(feature, accesslevel));
    	});
    	role.setRoleFeatures(rolefeatures);
        return ResponseEntity.ok().body(this.roleService.createRole(role));
    }*/

    @PostMapping("/roles/{id}/features")
    public ResponseEntity <Role> updateFeatures(@PathVariable Integer id, @RequestBody FeaturesDto features) {
    	Role role = roleService.getRoleById(id);
    	features.getFeaturesAndAccesslevels().forEach((featureId, accesslevelId) -> {
    		Feature feature = featureService.getFeatureById(featureId);
    		Accesslevel accesslevel = accesslevelService.getAccesslevelById(accesslevelId);
    		RoleFeature roleFeature= new RoleFeature();
    		roleFeature.setAccessLevel(accesslevel);
    		roleFeature.setFeature(feature);
    		roleFeature.setRole(role);
    		rolefeaturerepository.save(roleFeature);
    	});
    	
        return ResponseEntity.ok().body(role);
    }

    /*@DeleteMapping("/features/{id}")
    public HttpStatus deleteProject(@PathVariable Integer id) {
        this.projectService.deleteProject(id);
        return HttpStatus.OK;
    }*/
}






