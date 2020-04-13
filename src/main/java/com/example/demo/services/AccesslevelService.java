package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.demo.models.Accesslevel;
import com.example.demo.models.Feature;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dao.AccesslevelRepository;
import com.example.demo.dao.FeatureRepository;

@Service
public class AccesslevelService {
     
    @Autowired
    AccesslevelRepository repository;
     
    public Accesslevel createAccesslevel(Accesslevel accesslevel) {
        return repository.save(accesslevel);
    }

    public Accesslevel getAccesslevelById(Integer accesslevelId) {
        Optional <Accesslevel> AccesslevelDb = this.repository.findById(accesslevelId);
        if (AccesslevelDb.isPresent()) {
            return AccesslevelDb.get();
        } else {
            throw new ResourceNotFoundException("Access level not found");
        }
    }
    /*
    public List<Feature> getAllFeaturesByIds(List<Integer> ids){
        List<Feature> featuresDb = this.repository.findAllById(ids);
        return featuresDb;
    }
    

    public void deleteFeature(Integer featureId) {
        Feature featureDb = getFeatureById(featureId);
        this.repository.delete(featureDb);        
    }*/
}