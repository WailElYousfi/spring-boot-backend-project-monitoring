package com.example.demo.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Project;
import com.example.demo.services.ProjectService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/projects/all")
    public ResponseEntity < List<Project> > getAllProject() {
        return ResponseEntity.ok().body(projectService.getAllProject());
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity <Project> getProjectById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(projectService.getProjectById(id));
    }

    @PostMapping("/projects/all")
    public ResponseEntity <Project> createProject(@RequestBody Project project) {
        return ResponseEntity.ok().body(this.projectService.createProject(project));
    }

    @PostMapping("/projects/{id}")
    public ResponseEntity <Project> updateProject(@PathVariable Integer id, @RequestBody Project project) {
        project.setProjectId(id);
        return ResponseEntity.ok().body(this.projectService.updateProject(project));
    }

    @DeleteMapping("/projects/{id}")
    public HttpStatus deleteProject(@PathVariable Integer id) {
        this.projectService.deleteProject(id);
        return HttpStatus.OK;
    }
}