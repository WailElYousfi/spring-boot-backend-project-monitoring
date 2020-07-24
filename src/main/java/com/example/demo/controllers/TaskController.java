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

import com.example.demo.models.Task;
import com.example.demo.services.TaskService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    @Autowired
    TaskService taskService;


    @GetMapping("/tasks/all")
    public ResponseEntity < List<Task> > getAllTask() {
        return ResponseEntity.ok().body(taskService.getAllTask());
    }
    
    @GetMapping("/tasks/user/{userId}")
    public ResponseEntity < List<Task> > getAllTaskByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(taskService.getTasksByUserId(userId));
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity <Task> getTaskById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(taskService.getTaskById(id));
    }

    @DeleteMapping("/tasks/{id}")
    public HttpStatus deleteTask(@PathVariable Integer id) {
        this.taskService.deleteTask(id);
        return HttpStatus.OK;
    }
}