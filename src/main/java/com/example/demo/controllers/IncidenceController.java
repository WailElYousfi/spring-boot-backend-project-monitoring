package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Incidence;
import com.example.demo.models.Task;
import com.example.demo.services.IncidenceService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class IncidenceController {

    @Autowired
    IncidenceService incicendeService;


    @GetMapping("/incidences/all")
    public ResponseEntity < List<Incidence> > getAllIncidence() {
        return ResponseEntity.ok().body(incicendeService.getAllIncidence());
    }

    @GetMapping("/incidences/{id}")
    public ResponseEntity <Incidence> getIncidenceById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(incicendeService.getIncidenceById(id));
    }

    @GetMapping("/incidences/user/{userId}")
    public ResponseEntity < List<Incidence> > getAllIncidenceByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(incicendeService.getIncidencesByUserId(userId));
    }
    
}