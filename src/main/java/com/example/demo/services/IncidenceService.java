package com.example.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.models.Incidence;
import com.example.demo.models.Task;
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
    
    public List<Incidence> getIncidencesByDates(Date startDate, Date endDate){
    	Optional < List<Incidence> > incidences = this.repository.findByDate(startDate, endDate);
    	if (incidences.isPresent()) {
            return incidences.get();
        } else {
            throw new ResourceNotFoundException("Record not found");
        }
    }
    
    public List<Incidence> getIncidencesByUserIdOrProjectIdAndStatusAndDatesAndType(Long userId, Integer projectId, String status, int month, String typeName) {
    	Optional< List<Incidence> > incidencesDb; 
    	if(userId == null)
    		incidencesDb = this.repository.findByProjectIdAndStatusAndDatesAndType(projectId, status, month, typeName);
    	else
    		incidencesDb = this.repository.findByUserIdAndStatusAndDatesAndType(userId, status, month, typeName);

        if (incidencesDb.isPresent()) {
            return incidencesDb.get();
        } else {
            return null;
        }
    }
    
    public Integer getCountOfIncidencesByUserIdOrProjectIdAndStatusAndDatesAndType(Long userId, Integer projectId, String status, int month, String typeName) {
    	Integer count = 0;
    	if(userId == null)
    		count = this.repository.countIncidencesByProjectIdAndStatusAndDatesAndType(projectId, status, month, typeName);
    	else
    		count = this.repository.countIncidencesByUserIdAndStatusAndDatesAndType(userId, status, month, typeName);
    	
    	return count;
    }
    
    
	public List<String> getUsernamesOfIncidencesByDates(Date startDate, Date endDate){		
		List<Incidence> incidences = getIncidencesByDates(startDate, endDate);
		Set<String> usernames = new HashSet<String>(); //without duplicates
		for (Incidence incidence : incidences)
			usernames.add(incidence.getCausedUser().getJiraUsername());
		return usernames.stream().distinct().collect(Collectors.toList()); // Remove duplicates
		
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