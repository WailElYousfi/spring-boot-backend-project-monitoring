package com.example.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.demo.models.Feature;
import com.example.demo.models.Justification;
import com.example.demo.models.Project;
import com.example.demo.models.Role;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dao.FeatureRepository;
import com.example.demo.dao.JustificationRepository;
import com.example.demo.dao.UserRepository;

@Service
public class JustificationService {
     
    @Autowired
    JustificationRepository repository;
    
    @Autowired
    UserService userService;
     
    public Justification createJustification(Justification justification) {
        return repository.save(justification);
    }

    public Justification updateJustification(Justification justification) {
        Justification justificationDb = getJustificationById(justification.getJustificationId());
        Justification justificationUpdate = justificationDb;
        justificationUpdate.setContent(justification.getContent());
        justificationUpdate.setSubject(justification.getSubject());
        justificationUpdate.setConcernedMonth(justification.getConcernedMonth());
        return repository.save(justificationUpdate);
    }
    
    public Justification validateJustification(Integer justificationId, Long validatorId) {
        Justification justificationDb = getJustificationById(justificationId);
        Justification justificationUpdate = justificationDb;
        User validator = userService.getUserById(validatorId);
        justificationUpdate.setValidator(validator);
        justificationUpdate.setIsValidated(true);
        justificationUpdate.setValidationDate(new Date());
        return repository.save(justificationUpdate);
    }
    
    public Justification getJustificationById(Integer justificationId) {
        Optional <Justification> justificationDb = this.repository.findById(justificationId);
        if (justificationDb.isPresent()) {
            return justificationDb.get();
        } else {
            throw new ResourceNotFoundException("justification not found");
        }
    }
    
    public List<Justification> getDescendantsJustifications(Integer projectId, Long userId) {
    	Role descendantRole = this.userService.getUserById(userId).getRole();
    	if(descendantRole.getChildRole() != null) {
    		Integer descendantRoleId = descendantRole.getChildRole().getRoleId();
        	Optional <List<Justification>> justificationsDb = this.repository.findJustificationsByProjectIdAndRoleId(projectId, descendantRoleId);
        	if (justificationsDb.isPresent())
                return justificationsDb.get();
            else
                return null;
    	} else 
    		return null;   
    }

	public List<Justification> getJustificationsByJustificatorId(Long justificatorId) {
    	Optional <List<Justification>> justificationsDb = this.repository.findJustificationsByJustificatorId(justificatorId);
        if (justificationsDb.isPresent()) {
            return justificationsDb.get();
        } else {
            return null;
        }
    }
    
    public Justification archiveJustification(Integer justificationId) {
        Optional <Justification> justificationDb = this.repository.findById(justificationId);
        Justification justificationUpdate ;
        if (justificationDb.isPresent()) {
        	justificationUpdate = justificationDb.get();
        	justificationUpdate.setIsArchived(true);
            return repository.save(justificationUpdate);
        }else
            throw new ResourceNotFoundException("JustificationDb not found");
    }

}