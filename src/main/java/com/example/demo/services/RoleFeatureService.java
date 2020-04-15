package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.demo.models.Feature;
import com.example.demo.models.Project;
import com.example.demo.models.Role;
import com.example.demo.models.RoleFeature;
import com.example.demo.models.RoleFeatureId;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dao.FeatureRepository;
import com.example.demo.dao.RoleFeatureRepository;

@Service
public class RoleFeatureService {
     
    @Autowired
    RoleFeatureRepository repository;
    
    @Autowired
    RoleService roleService;
    
    public RoleFeature createRoleFeature(RoleFeature roleFeature) {
        return repository.save(roleFeature);
    }

//    public Feature updateFeature(Feature feature) {
//        Feature featureDb = getFeatureById(feature.getFeatureId());
//        Feature featureUpdate = featureDb;
//        featureUpdate.setUrl(feature.getUrl());
//        featureUpdate.setFeatureName(feature.getFeatureName());
//        return repository.save(featureUpdate);
//    }
//    
//    public List <Feature> getAllFeatures() {
//        return this.repository.findAll();
//    }
//    
//
//    public Feature getFeatureById(Integer featureId) {
//        Optional <Feature> featureDb = this.repository.findById(featureId);
//        if (featureDb.isPresent()) {
//            return featureDb.get();
//        } else {
//            throw new ResourceNotFoundException("feature not found");
//        }
//    }
//    
//    public List<Feature> getAllFeaturesByIds(List<Integer> ids){
//        List<Feature> featuresDb = this.repository.findAllById(ids);
//        return featuresDb;
//    }
//    
//
    public void deleteRoleFeature(RoleFeatureId rfid) {
        Optional <RoleFeature> rfdb = this.repository.findById(rfid);
        if (rfdb.isPresent()) {
            this.repository.delete(rfdb.get());
        }

    }
    
    public void deleteListRoleFeatures(List<Feature> features, Integer roleId) {
    	Role role = roleService.getRoleById(roleId);
    	for (Feature f : features) {
    		RoleFeatureId rfid = new RoleFeatureId();
    		rfid.setFeature(f);
    		rfid.setRole(role);
            deleteRoleFeature(rfid);
		}
        
    }
    
}