package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.demo.models.Incidence;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dao.IncidenceRepository;

@Service
public class IncidenceService {
     
    @Autowired
    IncidenceRepository repository;
    
    @Autowired
    TypeService typeRepository;
    
    public Incidence createIncidence(Incidence incidence) {
        return repository.save(incidence);
    }

    public Incidence updateIncidence(Incidence incidence) {
        Optional <Incidence> incidenceDb = this.repository.findByKeyAndSummary(incidence.getKey(), incidence.getSummary());

        if (incidenceDb.isPresent()) {
        	Incidence incidenceUpdate = incidenceDb.get();
        	incidenceUpdate.setAssignedUser(incidence.getAssignedUser());
        	incidenceUpdate.setCausedUser(incidence.getCausedUser());
        	incidenceUpdate.setFileType(incidence.getFileType());
        	incidenceUpdate.setIncidenceType(incidence.getIncidenceType());
        	incidenceUpdate.setJiraSas(incidence.getJiraSas());
        	incidenceUpdate.setKey(incidence.getKey());
        	incidenceUpdate.setLinkedIssues(incidence.getLinkedIssues());
        	incidenceUpdate.setUpdated(incidence.getUpdated());
        	incidenceUpdate.setCreated(incidence.getCreated());
        	incidenceUpdate.setOriginalEstimate(incidence.getOriginalEstimate());
        	incidenceUpdate.setPlannedEnd(incidence.getPlannedEnd());
        	incidenceUpdate.setProject(incidence.getProject());
        	incidenceUpdate.setResolved(incidence.getResolved());
        	incidenceUpdate.setStatus(incidence.getStatus());
        	incidenceUpdate.setSummary(incidence.getSummary());
        	incidenceUpdate.setTimeSpent(incidence.getTimeSpent());
        	incidenceUpdate.setUpdated(incidence.getUpdated());
        	// incidenceUpdate.setDate(incidence.getDate());
        	
            return repository.save(incidenceUpdate);            
        } else {
        	return repository.save(incidence);
        }
    }

    
    public List <Incidence> getAllIncidence() {
        return this.repository.findAll();
    }
    

    public Incidence getIncidenceById(Integer incidenceId) {

        Optional <Incidence> incidenceDb = this.repository.findById(incidenceId);

        if (incidenceDb.isPresent()) {
            return incidenceDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found");
        }
    }
    
    public Incidence getIncidenceByKey(String key) {

        Optional <Incidence> incidenceDb = this.repository.findByKey(key);

        if (incidenceDb.isPresent()) {
            return incidenceDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found");
        }
    }
    

    public void deleteIncidence(Integer incidenceId) {
        Optional <Incidence> incidenceDb = this.repository.findById(incidenceId);

        if (incidenceDb.isPresent()) {
            this.repository.delete(incidenceDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found");
        }

    }
}