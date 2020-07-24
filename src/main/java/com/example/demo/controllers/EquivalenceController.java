package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import com.example.demo.models.Equivalence;
import com.example.demo.models.Feature;
import com.example.demo.models.Project;
import com.example.demo.models.Role;
import com.example.demo.models.Type;
import com.example.demo.services.EquivalenceService;
import com.example.demo.services.FeatureService;
import com.example.demo.services.RoleService;
import com.example.demo.services.TypeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EquivalenceController {

    @Autowired
    TypeService typeService;
    
    @Autowired
    EquivalenceService equivalenceService;
    

    @GetMapping("/type/{typeName}/parameters")
    public ResponseEntity< List<Equivalence> > getAllColumnsByType(@PathVariable String typeName) {
    	Type type = typeService.getTypeByName(typeName);
        return ResponseEntity.ok().body(type.getColonnes());
    }
    
    @GetMapping("/parameters")
    public ResponseEntity< Map<String, List<Equivalence>> > getAllColumns() {
    	List<Type> types = typeService.getAllType();
		Map<String, List<Equivalence>> response = new LinkedHashMap<String, List<Equivalence>>();
    	for (Type type : types) {
			response.put(type.getTypeName(), type.getColonnes());
		}
        return ResponseEntity.ok().body(response);
    }
    
    @PostMapping("/parameters/{id}")
    public ResponseEntity <Equivalence> updateEquivalence(@PathVariable Integer id, @RequestBody Equivalence equivalence) {
        equivalence.setEquivalenceId(id);
        return ResponseEntity.ok().body(this.equivalenceService.updateEquivalence(equivalence));
    }
    
    @PostMapping("/parameters")
    public ResponseEntity <List<Equivalence>> updateListEquivalence(@RequestBody List<Equivalence> equivalences) {
        return ResponseEntity.ok().body(this.equivalenceService.updateListEquivalence(equivalences));
    }

    
}