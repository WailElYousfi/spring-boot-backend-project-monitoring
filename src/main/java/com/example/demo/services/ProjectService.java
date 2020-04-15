package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.demo.models.Project;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dao.ProjectRepository;

@Service
public class ProjectService {
     
    @Autowired
    ProjectRepository repository;
     
    public Project createProject(Project project) {
    	project.setIsClosed(false);
        return repository.save(project);
    }

    public Project updateProject(Project project) {
        Optional <Project> projectDb = this.repository.findById(project.getProjectId());

        if (projectDb.isPresent()) {
            Project projectUpdate = projectDb.get();
            projectUpdate.setProjectId(project.getProjectId());
            projectUpdate.setProjectName(project.getProjectName());
            projectUpdate.setDescription(project.getDescription());
            projectUpdate.setIsClosed(project.getIsClosed());
            repository.save(projectUpdate);            
            return projectUpdate;
        } else {
        	return repository.save(project);
        }
    }

    
    public List <Project> getAllProject() {
        return this.repository.findAll();
    }
    

    public Project getProjectById(Integer projectId) {

        Optional <Project> projectDb = this.repository.findById(projectId);

        if (projectDb.isPresent()) {
            return projectDb.get();
        } else {
            throw new ResourceNotFoundException("project not found");
        }
    }
    
    public Project getProjectByName(String projectName) {

        Optional <Project> projectDb = this.repository.findByProjectName(projectName);

        if (projectDb.isPresent()) {
            return projectDb.get();
        } else {
            throw new ResourceNotFoundException("project not found");
        }
    }
    

    public void deleteProject(Integer projectId) {
        Optional <Project> projectDb = this.repository.findById(projectId);

        if (projectDb.isPresent()) {
            this.repository.delete(projectDb.get());
        } else {
            throw new ResourceNotFoundException("project not found");
        }

    }
}