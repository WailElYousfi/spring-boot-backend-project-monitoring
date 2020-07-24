package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.demo.models.Equivalence;
import com.example.demo.models.Project;
import com.example.demo.models.User;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dao.EquivalenceRepository;
import com.example.demo.dao.ProjectRepository;

@Service
public class EquivalenceService {
     
    @Autowired
    EquivalenceRepository repository;
     
  /*  public Project createProject(Project project) {
    	project.setIsClosed(false);
        return repository.save(project);
    }*/

    public Equivalence updateEquivalence(Equivalence equivalence) {
        Optional <Equivalence> equivalenceDb = this.repository.findById(equivalence.getEquivalenceId());

        if (equivalenceDb.isPresent()) {
            Equivalence equivalenceUpdate = equivalenceDb.get();
            equivalenceUpdate.setFenixEquivalence(equivalence.getFenixEquivalence());
            equivalenceUpdate.setJiraEquivalence(equivalence.getJiraEquivalence());
            return repository.save(equivalenceUpdate);                     
        } else {
        	return repository.save(equivalence);
        }
    }
    
    public List<Equivalence> updateListEquivalence(List<Equivalence> equivalences) {
    	List <Equivalence> newList = new ArrayList<Equivalence>();
    	for (Equivalence equivalence : equivalences)
			newList.add(updateEquivalence(equivalence));
    	return newList;
    }

}