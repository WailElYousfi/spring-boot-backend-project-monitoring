package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.example.demo.models.Task;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dao.TaskRepository;

@Service
public class TaskService {
     
    @Autowired
    TaskRepository repository;
    
    @Autowired
    TypeService typeRepository;
    
    public Task createTask(Task task) {
        return repository.save(task);
    }

    public Task updateTask(Task task) {
        Optional <Task> taskDb = this.repository.findByKeyAndTitle(task.getKey(), task.getTitle());

        if (taskDb.isPresent()) {
            Task taskUpdate = taskDb.get();
            taskUpdate.setProject(task.getProject());
            taskUpdate.setKey(task.getKey());
            taskUpdate.setTitle(task.getTitle());
            taskUpdate.setDescription(task.getDescription());
            taskUpdate.setFixVersion(task.getFixVersion());
            taskUpdate.setOriginalEstimate(task.getOriginalEstimate());
            taskUpdate.setRemainingEstimate(task.getRemainingEstimate());
			taskUpdate.setStatus(task.getStatus());
			taskUpdate.setTimeSpent(task.getTimeSpent());
			taskUpdate.setTaskType(task.getTaskType());
		//	taskUpdate.setDate(task.getDate());
			taskUpdate.setUser(task.getUser());
			taskUpdate.setComment(task.getComment());
			taskUpdate.setFileType(task.getFileType());
            repository.save(taskUpdate);            
            return taskUpdate;
        } else {
        	return repository.save(task);
        }
    }

    
    public List <Task> getAllTask() {
        return this.repository.findAll();
    }
    

    public Task getTaskById(Integer taskId) {

        Optional <Task> taskDb = this.repository.findById(taskId);

        if (taskDb.isPresent()) {
            return taskDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found");
        }
    }
    
    public Task getTaskByKey(String key) {

        Optional <Task> taskDb = this.repository.findByKey(key);

        if (taskDb.isPresent()) {
            return taskDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found");
        }
    }
    

    public void deleteTask(Integer taskId) {
        Optional <Task> taskDb = this.repository.findById(taskId);

        if (taskDb.isPresent()) {
            this.repository.delete(taskDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found");
        }

    }
}